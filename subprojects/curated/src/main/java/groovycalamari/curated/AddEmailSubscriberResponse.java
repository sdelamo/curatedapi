package groovycalamari.curated;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
public class AddEmailSubscriberResponse {
    @NonNull
    @NotNull
    private Boolean success;

    @JsonProperty("error_message")
    @Nullable
    private String errorMessage;

    @Nullable
    private List<String> errors = new ArrayList<>();


    public AddEmailSubscriberResponse() {
    }

    @NonNull
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(@NonNull Boolean success) {
        this.success = success;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(@Nullable String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Nullable
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(@Nullable List<String> errors) {
        this.errors = errors;
    }
}
