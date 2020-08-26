package groovycalamari.curated;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotBlank;
import java.util.stream.Collectors;

@Introspected
public class Text extends Item {

    /**
     * The footer text of the item. All Markdown will have been converted to HTML.
     */
    @NonNull
    @NotBlank
    private String footer;

    public Text() {
    }

    /**
     *
     * @return The footer text of the item. All Markdown will have been converted to HTML.
     */
    @NonNull
    public String getFooter() {
        return footer;
    }

    /**
     *
     * @param footer The footer text of the item. All Markdown will have been converted to HTML.
     */
    public void setFooter(@NonNull String footer) {
        this.footer = footer;
    }

    @Override
    public String toString() {
        return "Link{" +
                "type=" + getType() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", embeddedLinks=" + getEmbeddedLinks().stream().map(EmbeddedLink::toString).collect(Collectors.joining()) +
                "footer='" + footer + '\'' +
                '}';
    }
}
