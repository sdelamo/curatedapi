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

class EmbeddedLinkSpec extends ApplicationContextSpecification {
    void "valid EmbeddedLink does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        EmbeddedLink embeddedLink = validEmbeddedLink()

        then:
        validator.validate(embeddedLink).isEmpty()
    }

    void "identifier is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        EmbeddedLink embeddedLink = validEmbeddedLink()
        embeddedLink.identifier = null

        then:
        !validator.validate(embeddedLink).isEmpty()
    }

    void "title is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        EmbeddedLink embeddedLink = validEmbeddedLink()
        embeddedLink.title = null

        then:
        !validator.validate(embeddedLink).isEmpty()
    }

    void "url is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        EmbeddedLink embeddedLink = validEmbeddedLink()
        embeddedLink.url = null

        then:
        !validator.validate(embeddedLink).isEmpty()
    }

    void "urlDomain is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        EmbeddedLink embeddedLink = validEmbeddedLink()
        embeddedLink.urlDomain = null

        then:
        !validator.validate(embeddedLink).isEmpty()
    }

    EmbeddedLink validEmbeddedLink() {
        EmbeddedLink embeddedLink = new EmbeddedLink()
        embeddedLink.identifier = "5dsYv2s"
        embeddedLink.title =  "Embedded link title"
        embeddedLink.url = "https://cur.at/5dsYv2s"
        embeddedLink.urlDomain = "example.com"
        embeddedLink
    }
}
