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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Introspected
public class Issues {
    /**
     * The page number for the data returned in this call.
     */
    @NonNull
    @NotNull
    @Positive
    private Integer page;

    /**
     * How many pages are available using this page size.
     */
    @JsonProperty("total_pages")
    @NonNull
    @NotNull
    @Positive
    private Integer totalPages;

    /**
     * The total number of issues which are available.
     */
    @JsonProperty("total_results")
    @NonNull
    @NotNull
    @Positive
    private Integer totalResults;

    /**
     * An array of {@link Summary} dictionaries.
     */
    @NonNull
    @NotNull
    private List<@Valid Summary> issues = new ArrayList<>();

    public Issues() {

    }

    /**
     *
     * @return The page number for the data returned in this call.
     */
    @NonNull
    public Integer getPage() {
        return page;
    }

    /**
     *
     * @param page The page number for the data returned in this call.
     */
    public void setPage(@NonNull Integer page) {
        this.page = page;
    }

    /**
     *
     * @return How many pages are available using this page size.
     */
    @NonNull
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     *
     * @param totalPages How many pages are available using this page size.
     */
    public void setTotalPages(@NonNull Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     *
     * @return The total number of issues which are available.
     */
    @NonNull
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     *
     * @param totalResults The total number of issues which are available.
     */
    public void setTotalResults(@NonNull Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
     *
     * @return An array of Issue Summary dictionaries
     */
    @NonNull
    public List<Summary> getIssues() {
        return issues;
    }

    /**
     *
     * @param issues An array of Issue Summary dictionaries
     */
    public void setIssues(@NonNull List<Summary> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "Issues{" +
                "page=" + page +
                ", totalPages=" + totalPages +
                ", totalResults=" + totalResults +
                ", issues=" + issues.stream().map(Summary::toString).collect(Collectors.joining()) +
                '}';
    }
}