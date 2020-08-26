package groovycalamari.curated;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
public abstract class Item {

    /**
     * Either Text or Link, this determines whether this is a Text Item or a Link Item (not usually displayed publicly).
     */
    @NonNull
    @NotNull
    private Type type;

    /**
     * The title of the item.
     */
    @NonNull
    @NotBlank
    private String title;

    /**
     * The body text of the item. All Markdown will have been converted to HTML.
     */
    @NonNull
    @NotBlank
    private String description;

    /**
     * An array of Embedded Link dictionaries.
     */
    @JsonProperty("embedded_links")
    @NonNull
    @NotNull
    private List<@Valid EmbeddedLink> embeddedLinks = new ArrayList<>();

    public Item() {
    }

    /**
     *
     * @return Either {@link Type#TEXT} or {@link Type#LINK}, this determines whether this is a Text Item or a Link Item (not usually displayed publicly).
     */
    @NonNull
    public Type getType() {
        return type;
    }

    /**
     *
     * @param type Either {@link Type#TEXT} or {@link Type#LINK}, this determines whether this is a Text Item or a Link Item (not usually displayed publicly).
     */
    public void setType(@NonNull Type type) {
        this.type = type;
    }

    /**
     *
     * @return The title of the item.
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title The title of the item.
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     *
     * @return The body text of the item. All Markdown will have been converted to HTML.
     */
    @NonNull
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description The body text of the item. All Markdown will have been converted to HTML.
     */
    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    /**
     *
     * @return An array of {@link EmbeddedLink}
     */
    @NonNull
    public List<EmbeddedLink> getEmbeddedLinks() {
        return embeddedLinks;
    }

    /**
     *
     * @param embeddedLinks An array of {@link EmbeddedLink}
     */
    public void setEmbeddedLinks(@NonNull List<EmbeddedLink> embeddedLinks) {
        this.embeddedLinks = embeddedLinks;
    }
}
