package groovycalamari.curated

import javax.validation.Validator

class CategorySpec extends ApplicationContextSpecification {
    void "valid Category does not trigger any constraint exception"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Category category = validCategory()

        then:
        validator.validate(category).isEmpty()
    }

    void "name is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Category category = validCategory()
        category.name = null

        then:
        !validator.validate(category).isEmpty()
    }

    void "code is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Category category = validCategory()
        category.code = null

        then:
        !validator.validate(category).isEmpty()
    }

    void "items is required"() {
        given:
        Validator validator = applicationContext.getBean(Validator)

        when:
        Category category = validCategory()
        category.items = null

        then:
        !validator.validate(category).isEmpty()
    }

    Category validCategory() {
        Category category = new Category()
        category.name = 'News'
        category.code = 'news'
        category.items = []
        return category
    }
}
