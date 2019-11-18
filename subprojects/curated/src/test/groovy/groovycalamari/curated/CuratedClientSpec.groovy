/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019 Sergio del Amo.
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
import spock.lang.AutoCleanup
import spock.lang.Requires
import spock.lang.Shared
import spock.lang.Specification

@Requires({sys['curated.publicationkey']})
class CuratedClientSpec extends Specification {

    Map<String, Object> getConfiguration() {
        [
                'curated.publication-key': System.getProperty('curated.publicationkey'),
                'curated.api-key': System.getProperty('curated.apikey'),
        ]
    }

    @AutoCleanup
    @Shared
    ApplicationContext applicationContext = ApplicationContext.run(ApplicationContext, configuration)

    void "is it possible to fetch every issue"() {
        given:
        CuratedApi curatedApi = applicationContext.getBean(CuratedApi)

        when:
        CuratedIssuesResponse issues = curatedApi.issues().blockingGet()

        then:
        issues.total_results > 0

    }
}
