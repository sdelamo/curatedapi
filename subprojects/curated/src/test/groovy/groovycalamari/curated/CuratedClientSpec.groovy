/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 Sergio del Amo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovycalamari.curated

import io.micronaut.context.ApplicationContext
import io.micronaut.context.annotation.Requires
import io.micronaut.core.io.socket.SocketUtils
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Specification

class CuratedClientSpec extends Specification {

    void "is it possible to fetch every issue"() {
        given:
        int curatedPort = SocketUtils.findAvailableTcpPort()
        EmbeddedServer curatedServer = ApplicationContext.run(EmbeddedServer, [
                'spec.name': 'curated',
                'micronaut.server.port': curatedPort,
        ])
        ApplicationContext applicationContext = ApplicationContext.run([
                'curated.publication-key': 'PUBLICATION_KEY',
                'curated.api-key': 'API_KEY',
                'curated.url': "http://localhost:${curatedPort}"
        ])
        CuratedApi curatedApi = applicationContext.getBean(CuratedApi)

        when:
        String email = 'steve@apple.com'
        AddEmailSubscriberResponse subscriberResponse = curatedApi.addEmailSubscriber(new AddEmailSubscriber(email)).blockingGet()

        then:
        subscriberResponse
        subscriberResponse.success
        !subscriberResponse.errorMessage
        !subscriberResponse.errors

        when:
        subscriberResponse = curatedApi.addEmailSubscriber(new AddEmailSubscriber(email)).blockingGet()

        then:
        subscriberResponse
        subscriberResponse.success == Boolean.FALSE
        subscriberResponse.errorMessage == "Unable to subscribe ${email}.".toString()
        subscriberResponse.errors
        subscriberResponse.errors.size() == 1
        subscriberResponse.errors.first() == "Email address is already subscribed."

        when:
        List<Summary> summaries = fetchAll(curatedApi)

        then:
        summaries.size() == 1

        when:
        Summary summary = summaries.first()

        then:
        summary.number == 1
        summary.title == "Issue 1"
        summary.summary == "This is the issue summary"
        summary.url == "https://your-publication.curated.co/issues/1"
        summary.publishedAt == "2014-08-16T01:21:58.390+01:00"
        summary.updatedAt == "2014-08-24T12:00:03.535+01:00"

        Issue issue = curatedApi.issue(1).blockingGet()

        then:
        issue
        issue.number == 1
        issue.title == 'Issue 1'
        issue.summary == 'Issue summary'
        issue.url == "https://your-publication.curated.co/issues/1"
        issue.publishedAt == "2014-08-16T01:21:58.390+01:00"
        issue.updatedAt == "2014-08-24T12:00:03.535+01:00"
        issue.categories
        issue.categories.size() == 1
        issue.categories.first().name == 'News'
        issue.categories.first().code == 'news'
        issue.categories.first().items
        issue.categories.first().items.size() == 2
        issue.categories.first().items.first().type == Type.TEXT
        issue.categories.first().items.first().title == "Item title"
        issue.categories.first().items.first().description == '<p>This is the description for a text item in the issue, it will passed as HTML with <strong>formatting</strong> already applied and <a href="https://cur.at/5dsYv2s">embedded links</a> inline.</p>'
        issue.categories.first().items.first().footer == "Footer text"
        issue.categories.first().items.first().embeddedLinks
        issue.categories.first().items.first().embeddedLinks.size() == 1
        issue.categories.first().items.first().embeddedLinks.first().identifier == "5dsYv2s"
        issue.categories.first().items.first().embeddedLinks.first().title ==  "Embedded link title"
        issue.categories.first().items.first().embeddedLinks.first().url == "https://cur.at/5dsYv2s"
        issue.categories.first().items.first().embeddedLinks.first().urlDomain == "example.com"

        issue.categories.first().items.last().type == Type.LINK
        issue.categories.first().items.last().title == "Item title"
        issue.categories.first().items.last().description ==  "<p>This is the description for a link item in the issue, it will passed as HTML with <strong>formatting</strong> already applied.</p>"
        issue.categories.first().items.last().urlDomain == "example.com"
        issue.categories.first().items.last().url == "https://cur.at/8wnuSy6"
        issue.categories.first().items.last().embeddedLinks != null
        issue.categories.first().items.last().embeddedLinks.isEmpty()


        cleanup:
        curatedServer.close()
        applicationContext.close()
    }

    static List<Summary> fetchAll(CuratedApi curatedApi) {
        List<Summary> summaries = []
        final Integer perPages = 50
        Integer page = 1
        for (;;) {
            Issues issues = curatedApi.issues(perPages, page).blockingGet()
            summaries.addAll(issues.issues)
            if (issues.page == issues.totalPages)  {
                break
            }
            page++
        }
        summaries
    }

    @Requires(property = "spec.name", value = "curated")
    @Controller
    static class CuratedController {

        static boolean emailInvoked = false

        @Get('/PUBLICATION_KEY/api/v1/issues')
        String issues() {
            '{"page": 1, "total_pages": 1, "total_results": 1, "issues": [{"number": 1,"title": "Issue 1","summary": "This is the issue summary","url": "https://your-publication.curated.co/issues/1","published_at": "2014-08-16T01:21:58.390+01:00","updated_at": "2014-08-24T12:00:03.535+01:00"}]}'
        }

        @Get('/PUBLICATION_KEY/api/v1/issues/{number}')
        String issue(Integer number) {
            '{"number": 1,"title": "Issue 1","summary": "Issue summary","url": "https://your-publication.curated.co/issues/1","published_at": "2014-08-16T01:21:58.390+01:00","updated_at": "2014-08-24T12:00:03.535+01:00","categories": [{"name": "News","code": "news","items": [{"type": "Text","title": "Item title","description": "<p>This is the description for a text item in the issue, it will passed as HTML with <strong>formatting</strong> already applied and <a href=\\"https://cur.at/5dsYv2s\\">embedded links</a> inline.</p>","footer": "Footer text","embedded_links": [{"identifier": "5dsYv2s","title": "Embedded link title","url": "https://cur.at/5dsYv2s","url_domain": "example.com"}]},{"type": "Link","title": "Item title","description": "<p>This is the description for a link item in the issue, it will passed as HTML with <strong>formatting</strong> already applied.</p>","url_domain": "example.com","url": "https://cur.at/8wnuSy6","embedded_links": []}] }]}'
        }

        @Post('/PUBLICATION_KEY/api/v1/email_subscribers')
        String subscribe(@Body AddEmailSubscriber addEmailSubscriber) {
            if (!emailInvoked) {
                emailInvoked = true
                return '{"success":true}'
            }
            '{"success":false,"error_message":"Unable to subscribe steve@apple.com.","errors":["Email address is already subscribed."]}'
        }

    }
}
