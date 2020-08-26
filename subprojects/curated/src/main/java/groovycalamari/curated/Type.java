package groovycalamari.curated;

import com.fasterxml.jackson.annotation.JsonValue;
import io.micronaut.core.util.StringUtils;

public enum Type {
    TEXT, LINK;

    @JsonValue
    @Override
    public String toString() {
        return StringUtils.capitalize(this.name().toLowerCase());
    }
}
