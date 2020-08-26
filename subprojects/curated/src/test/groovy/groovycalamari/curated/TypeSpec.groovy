package groovycalamari.curated

import spock.lang.Specification
import spock.lang.Unroll

class TypeSpec extends Specification {

    @Unroll("Type #type toString() returns #expected")
    void "Type::toString returns capitalized string"(Type type, String expected) {
        expect:
        expected == type.toString()

        where:
        type       | expected
        Type.LINK  | 'Link'
        Type.TEXT  | 'Text'
    }
}
