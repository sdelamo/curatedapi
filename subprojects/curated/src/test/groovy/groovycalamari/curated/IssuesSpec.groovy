package groovycalamari.curated

import javax.validation.Validator

class IssuesSpec extends ApplicationContextSpecification {

    void "valid Issues does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()

        then:
        validator.validate(valid).isEmpty()
    }

    void "page is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()
        valid.page = null

        then:
        !validator.validate(valid).isEmpty()
    }

    void "page should be positive"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()
        valid.page = -1

        then:
        !validator.validate(valid).isEmpty()
    }

    void "totalPages is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()
        valid.totalPages = null

        then:
        !validator.validate(valid).isEmpty()
    }

    void "totalPages should be positive"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()
        valid.totalPages = -1

        then:
        !validator.validate(valid).isEmpty()
    }

    void "totalResults is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()
        valid.totalResults = null

        then:
        !validator.validate(valid).isEmpty()
    }

    void "totalResults should be positive"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()
        valid.totalResults = -1

        then:
        !validator.validate(valid).isEmpty()
    }

    void "issues is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Issues valid = validIssues()
        valid.issues = null

        then:
        !validator.validate(valid).isEmpty()
    }

    static Issues validIssues() {
        Issues issues = new Issues()
        issues.page = 1
        issues.totalPages = 18
        issues.totalResults = 177
        Summary summary = new Summary()
        summary.number = 177
        summary.title = "Issue 177"
        summary.summary = "lockdown productivity"
        summary.url = "http://groovycalamari.com/issues/177"
        summary.publishedAt = "2020-06-01T20:08:38.280+01:00"
        summary.updatedAt = "2020-06-01T20:08:41.865+01:00"
        issues.setIssues(Collections.singletonList(summary))
        issues
    }
}
