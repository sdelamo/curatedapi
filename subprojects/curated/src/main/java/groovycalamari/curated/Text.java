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
