package groovycalamari.curated


import javax.validation.Validator

class LinkSpec extends ApplicationContextSpecification {
    void "valid Summary does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Link link = validLink()

        then:
        validator.validate(link).isEmpty()
    }

    void "embeddedLinks is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Link link = validLink()
        link.embeddedLinks = null

        then:
        !validator.validate(link).isEmpty()
    }
    
    void "type is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Link link = validLink()
        link.type = null

        then:
        !validator.validate(link).isEmpty()
    }

    void "title is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Link link = validLink()
        link.title = null

        then:
        !validator.validate(link).isEmpty()

        when:
        link.title = ''

        then:
        !validator.validate(link).isEmpty()
    }

    void "description is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Link link = validLink()
        link.description = null

        then:
        !validator.validate(link).isEmpty()

        when:
        link.description = ''

        then:
        !validator.validate(link).isEmpty()
    }

    void "url is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Link link = validLink()
        link.url = null

        then:
        !validator.validate(link).isEmpty()

        when:
        link.url = ''

        then:
        !validator.validate(link).isEmpty()
    }

    void "urlDomain is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Link link = validLink()
        link.urlDomain = null

        then:
        !validator.validate(link).isEmpty()

        when:
        link.urlDomain = ''

        then:
        !validator.validate(link).isEmpty()
    }

    Link validLink() {
        Link link = new Link()
        link.type = Type.TEXT
        link.title = "Item title"
        link.description = '<p>This is the description for a link item in the issue, it will passed as HTML with <strong>formatting</strong> already applied and <a href="https://cur.at/5dsYv2s">embedded links</a> inline.</p>'
        link.url = "https://cur.at/8wnuSy6"
        link.urlDomain = "example.com"
        link.embeddedLinks = Collections.emptyList()
        link
    }
}
