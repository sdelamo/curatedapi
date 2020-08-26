/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2020 Sergio del Amo.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package groovycalamari.curated;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.core.annotation.Introspected;
import javax.validation.constraints.NotBlank;
import java.util.stream.Collectors;

@Introspected
public class Link extends Item {
    /**
     * The short URL destination’s domain name.
     */
    @NonNull
    @NotBlank
    @JsonProperty("url_domain")
    private String urlDomain;

    /**
     * The short URL for this embedded link.
     */
    @NonNull
    @NotBlank
    private String url;

    public Link() {
    }

    /**
     *
     * @return The short URL for this embedded link.
     */
    @NonNull
    public String getUrlDomain() {
        return urlDomain;
    }

    /**
     *
     * @param urlDomain The short URL for this embedded link.
     */
    public void setUrlDomain(@NonNull String urlDomain) {
        this.urlDomain = urlDomain;
    }

    /**
     *
     * @return The short URL for this embedded link.
     */
    @NonNull
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url The short URL for this embedded link.
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link{" +
                "type=" + getType() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", embeddedLinks=" + getEmbeddedLinks().stream().map(EmbeddedLink::toString).collect(Collectors.joining()) +
                "urlDomain='" + urlDomain + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
