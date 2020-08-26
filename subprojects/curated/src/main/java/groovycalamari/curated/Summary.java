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
import javax.validation.constraints.NotNull;

@Introspected
public class Summary {

    /**
     * The issue number, this should be used as the parameter when requesting the full set of data for a specific issue (see below).
     */
    @NonNull
    @NotNull
    private Integer number;

    /**
     * The title of the issue.
     */
    @NonNull
    @NotBlank
    private String title;

    /**
     * The issue summary.
     */
    @NonNull
    @NotBlank
    private String summary;

    /**
     * The public URL where this issue is available.
     */
    @NonNull
    @NotBlank
    private String url;

    /**
     * The date that the issue was published in ISO 8601 format.
     */
    @NonNull
    @NotBlank
    @JsonProperty("published_at")
    private String publishedAt;

    /**
     * The date that the issue was last updated (may be after publishing) in ISO 8601 format.
     */
    @NonNull
    @NotBlank
    @JsonProperty("updated_at")
    private String updatedAt;

    /**
     * Constructor.
     */
    public Summary() {
    }

    /**
     *
     * @return The issue number, this should be used as the parameter when requesting the full set of data for  specific issue
     */
    @NonNull
    public Integer getNumber() {
        return number;
    }

    /**
     *
     * @param number The issue number, this should be used as the parameter when requesting the full set of data for  specific issue
     */
    public void setNumber(@NonNull Integer number) {
        this.number = number;
    }

    /**
     *
     * @return The title of the issue.
     */
    @NonNull
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title The title of the issue.
     */
    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    /**
     *
     * @return The issue summary.
     */
    @NonNull
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary The issue summary.
     */
    public void setSummary(@NonNull String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return The public URL where this issue is available
     */
    @NonNull
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url The public URL where this issue is available
     */
    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    /**
     *
     * @return The date that the issue was published in ISO 8601 format.
     */
    @NonNull
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     *
     * @param publishedAt @return The date that the issue was published in ISO 8601 format.
     */
    public void setPublishedAt(@NonNull String publishedAt) {
        this.publishedAt = publishedAt;
    }

    /**
     *
     * @return The date that the issue was last updated (may be after publishing) in ISO 8601 format.
     */
    @NonNull
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt The date that the issue was last updated (may be after publishing) in ISO 8601 format.
     */
    public void setUpdatedAt(@NonNull String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Summary{" +
                "number=" + number +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", url='" + url + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
