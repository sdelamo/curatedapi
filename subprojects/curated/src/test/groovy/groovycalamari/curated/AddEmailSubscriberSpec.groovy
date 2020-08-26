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

class AddEmailSubscriberSpec extends ApplicationContextSpecification {
    void "valid AddEmailSubscriber does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        AddEmailSubscriber subscriber = validAddEmailSubscriber()

        then:
        validator.validate(subscriber).isEmpty()
    }

    void "email is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        AddEmailSubscriber subscriber = validAddEmailSubscriber()
        subscriber.email = null

        then:
        !validator.validate(subscriber).isEmpty()

        when:
        subscriber.email = ''

        then:
        !validator.validate(subscriber).isEmpty()
    }

    AddEmailSubscriber validAddEmailSubscriber() {
        AddEmailSubscriber subscriber = new AddEmailSubscriber()
        subscriber.email = 'steve@apple.com'
        subscriber
    }
}
