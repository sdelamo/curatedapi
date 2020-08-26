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
