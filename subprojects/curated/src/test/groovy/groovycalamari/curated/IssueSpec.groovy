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
