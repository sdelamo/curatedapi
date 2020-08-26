package groovycalamari.curated

import spock.lang.Specification

import javax.validation.Validator

class TextSpec extends ApplicationContextSpecification {
    void "valid Summary does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Text text = validText()

        then:
        validator.validate(text).isEmpty()
    }

    void "embeddedLinks is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Text text = validText()
        text.embeddedLinks = null

        then:
        !validator.validate(text).isEmpty()
    }


    void "type is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Text text = validText()
        text.type = null

        then:
        !validator.validate(text).isEmpty()
    }

    void "title is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Text text = validText()
        text.title = null

        then:
        !validator.validate(text).isEmpty()

        when:
        text.title = ''

        then:
        !validator.validate(text).isEmpty()
    }

    void "description is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Text text = validText()
        text.description = null

        then:
        !validator.validate(text).isEmpty()

        when:
        text.description = ''

        then:
        !validator.validate(text).isEmpty()
    }

    void "footer is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Text text = validText()
        text.footer = null

        then:
        !validator.validate(text).isEmpty()

        when:
        text.footer = ''

        then:
        !validator.validate(text).isEmpty()
    }

    Text validText() {
        Text text = new Text()
        text.type = Type.TEXT
        text.title = "Item title"
        text.description = '<p>This is the description for a text item in the issue, it will passed as HTML with <strong>formatting</strong> already applied and <a href="https://cur.at/5dsYv2s">embedded links</a> inline.</p>'
        text.footer = "Footer text"
        EmbeddedLink embeddedLink = new EmbeddedLink()
        embeddedLink.identifier = "5dsYv2s"
        embeddedLink.title =  "Embedded link title"
        embeddedLink.url = "https://cur.at/5dsYv2s"
        embeddedLink.urlDomain = "example.com"
        text.embeddedLinks = Collections.singletonList(embeddedLink)
        text
    }
}
