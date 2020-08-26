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
