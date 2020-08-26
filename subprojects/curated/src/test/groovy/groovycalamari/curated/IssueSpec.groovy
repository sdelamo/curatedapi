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

import javax.validation.Validator

class IssueSpec extends ApplicationContextSpecification {

    void "valid Issue does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()

        then:
        validator.validate(issue).isEmpty()
    }

    void "number is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()
        issue.number = null

        then:
        !validator.validate(issue).isEmpty()
    }

    void "title is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()
        issue.title = null

        then:
        !validator.validate(issue).isEmpty()
    }

    void "summary is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()
        issue.summary = null

        then:
        !validator.validate(issue).isEmpty()
    }

    void "url is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()
        issue.url = null

        then:
        !validator.validate(issue).isEmpty()
    }

    void "publishedAt is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()
        issue.publishedAt = null

        then:
        !validator.validate(issue).isEmpty()
    }

    void "updatedAt is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()
        issue.updatedAt = null

        then:
        !validator.validate(issue).isEmpty()
    }

    void "categories is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issue issue = validIssue()
        issue.categories = null

        then:
        !validator.validate(issue).isEmpty()
    }

    static Issue validIssue() {
        Issue issue = new Issue()
        issue.number = 177
        issue.title = "Issue 177"
        issue.summary = "lockdown productivity"
        issue.url = "http://groovycalamari.com/issues/177"
        issue.publishedAt = "2020-06-01T20:08:38.280+01:00"
        issue.updatedAt = "2020-06-01T20:08:41.865+01:00"
        issue
    }
}
