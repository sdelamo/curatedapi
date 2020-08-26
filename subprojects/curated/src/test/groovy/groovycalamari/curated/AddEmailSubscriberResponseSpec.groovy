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
