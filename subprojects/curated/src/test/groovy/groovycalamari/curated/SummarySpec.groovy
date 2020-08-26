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

class SummarySpec extends ApplicationContextSpecification {

    void "valid Summary does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Summary summary = validSummary()

        then:
        validator.validate(summary).isEmpty()
    }

    void "number is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Summary summary = validSummary()
        summary.number = null

        then:
        !validator.validate(summary).isEmpty()
    }

    void "title is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Summary summary = validSummary()
        summary.title = null

        then:
        !validator.validate(summary).isEmpty()
    }

    void "summary is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Summary summary = validSummary()
        summary.summary = null

        then:
        !validator.validate(summary).isEmpty()
    }

    void "url is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Summary summary = validSummary()
        summary.url = null

        then:
        !validator.validate(summary).isEmpty()
    }

    void "publishedAt is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Summary summary = validSummary()
        summary.publishedAt = null

        then:
        !validator.validate(summary).isEmpty()
    }

    void "updatedAt is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Summary summary = validSummary()
        summary.updatedAt = null

        then:
        !validator.validate(summary).isEmpty()
    }

    static Summary validSummary() {
        Summary summary = new Summary()
        summary.number = 177
        summary.title = "Issue 177"
        summary.summary = "lockdown productivity"
        summary.url = "http://groovycalamari.com/issues/177"
        summary.publishedAt = "2020-06-01T20:08:38.280+01:00"
        summary.updatedAt = "2020-06-01T20:08:41.865+01:00"
        summary
    }
}
