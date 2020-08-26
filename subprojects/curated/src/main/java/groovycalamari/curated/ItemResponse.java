package groovycalamari.curated;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
public class ItemResponse {

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

    /**
     * The short URL destination’s domain name.
     */
    @Nullable
    @JsonProperty("url_domain")
    private String urlDomain;

    /**
     * The short URL for this embedded link.
     */
    @Nullable
    private String url;

    /**
     *
     * @return The short URL for this embedded link.
     */
    @Nullable
    public String getUrlDomain() {
        return urlDomain;
    }

    /**
     *
     * @param urlDomain The short URL for this embedded link.
     */
    public void setUrlDomain(@Nullable String urlDomain) {
        this.urlDomain = urlDomain;
    }

    /**
     *
     * @return The short URL for this embedded link.
     */
    @Nullable
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url The short URL for this embedded link.
     */
    public void setUrl(@Nullable String url) {
        this.url = url;
    }

    /**
     * The footer text of the item. All Markdown will have been converted to HTML.
     */
    @Nullable
    private String footer;

    public ItemResponse() {
    }

    public Item toItem() {
        switch (type) {
            case TEXT:
                return toText();
            default:
            case LINK:
                return toLink();
        }
    }
    public Text toText() {
        Text text = new Text();
        text.setType(getType());
        text.setTitle(getTitle());
        text.setDescription(getDescription());
        text.setFooter(getFooter());
        text.setEmbeddedLinks(getEmbeddedLinks());
        return text;

    }

    public Link toLink() {
        Link link = new Link();
        link.setType(getType());
        link.setTitle(getTitle());
        link.setDescription(getDescription());
        link.setUrl(getUrl());
        link.setUrlDomain(getUrlDomain());
        link.setEmbeddedLinks(getEmbeddedLinks());
        return link;
    }


    /**
     *
     * @return The footer text of the item. All Markdown will have been converted to HTML.
     */
    @Nullable
    public String getFooter() {
        return footer;
    }

    /**
     *
     * @param footer The footer text of the item. All Markdown will have been converted to HTML.
     */
    public void setFooter(@Nullable String footer) {
        this.footer = footer;
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
