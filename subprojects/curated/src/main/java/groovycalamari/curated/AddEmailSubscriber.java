package groovycalamari.curated;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;

@Introspected
public class AddEmailSubscriber {

    /**
     * The email address to subscribe to your list.
     */
    @NonNull
    @NotBlank
    private String email;

    public AddEmailSubscriber() {
    }

    public AddEmailSubscriber(@NonNull @NotBlank String email) {
        this.email = email;
    }

    /**
     *
     * @return The email address to subscribe to your list.
     */
    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }
}
