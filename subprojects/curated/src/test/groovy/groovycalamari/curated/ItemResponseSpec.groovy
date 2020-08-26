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

class ItemResponseSpec extends ApplicationContextSpecification {
    void "valid Summary does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()

        then:
        validator.validate(itemResponse).isEmpty()
    }

    void "embeddedLinks is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()
        itemResponse.embeddedLinks = null

        then:
        !validator.validate(itemResponse).isEmpty()
    }

    void "type is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()
        itemResponse.type = null

        then:
        !validator.validate(itemResponse).isEmpty()
    }

    void "title is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()
        itemResponse.title = null

        then:
        !validator.validate(itemResponse).isEmpty()

        when:
        itemResponse.title = ''

        then:
        !validator.validate(itemResponse).isEmpty()
    }

    void "description is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()
        itemResponse.description = null

        then:
        !validator.validate(itemResponse).isEmpty()

        when:
        itemResponse.description = ''

        then:
        !validator.validate(itemResponse).isEmpty()
    }

    void "url is optional"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()
        itemResponse.url = null

        then:
        validator.validate(itemResponse).isEmpty()
    }

    void "urlDomain is optional"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()
        itemResponse.urlDomain = null

        then:
        validator.validate(itemResponse).isEmpty()
    }

    void "footer is optional"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        ItemResponse itemResponse = validItemResponse()
        itemResponse.footer = null

        then:
        validator.validate(itemResponse).isEmpty()
    }

    ItemResponse validItemResponse() {
        ItemResponse itemResponse = new ItemResponse()
        itemResponse.type = Type.TEXT
        itemResponse.title = "Item title"
        itemResponse.description = '<p>This is the description for a link item in the issue, it will passed as HTML with <strong>formatting</strong> already applied and <a href="https://cur.at/5dsYv2s">embedded links</a> inline.</p>'
        itemResponse.footer = "Footer text"
        itemResponse.url = "https://cur.at/8wnuSy6"
        itemResponse.urlDomain = "example.com"
        itemResponse.embeddedLinks = Collections.emptyList()
        itemResponse
    }
}
