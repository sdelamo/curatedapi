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
