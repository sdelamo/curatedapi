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

class AddEmailSubscriberResponseSpec extends ApplicationContextSpecification {
    void "valid AddEmailSubscriberResponse does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        AddEmailSubscriberResponse subscriber = validAddEmailSubscriberResponse()

        then:
        validator.validate(subscriber).isEmpty()
    }

    void "success is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        AddEmailSubscriberResponse subscriber = validAddEmailSubscriberResponse()
        subscriber.success = null

        then:
        !validator.validate(subscriber).isEmpty()
    }

    void "errorMessage is optional"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        AddEmailSubscriberResponse subscriber = validAddEmailSubscriberResponse()
        subscriber.errorMessage = null

        then:
        validator.validate(subscriber).isEmpty()
    }

    void "errors is optional"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        AddEmailSubscriberResponse subscriber = validAddEmailSubscriberResponse()
        subscriber.errors = null

        then:
        validator.validate(subscriber).isEmpty()
    }

    AddEmailSubscriberResponse validAddEmailSubscriberResponse() {
        AddEmailSubscriberResponse response = new AddEmailSubscriberResponse()
        response.success = true
        response.errorMessage = 'Unable to subscribe steve@apple.com.'
        response.errors = ["Email address is already subscribed."]
        response
    }
}
