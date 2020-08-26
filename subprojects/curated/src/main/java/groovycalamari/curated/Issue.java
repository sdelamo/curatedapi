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

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Introspected
public class Issue extends Summary {
    /**
     *  An array of Category dictionaries. This data will be returned in the order of categories displayed on the public site.
     */
    @NonNull
    @NotNull
    List<Category> categories = new ArrayList<>();

    /**
     *
     * @return  An array of Category dictionaries. This data will be returned in the order of categories displayed on the public site.
     */
    @NonNull
    public List<Category> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories  An array of Category dictionaries. This data will be returned in the order of categories displayed on the public site.
     */
    public void setCategories(@NonNull List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "number=" + getNumber() +
                ", title='" + getTitle() + '\'' +
                ", summary='" + getSummary() + '\'' +
                ", url='" + getUrl() + '\'' +
                ", publishedAt='" + getPublishedAt() + '\'' +
                ", updatedAt='" + getUpdatedAt() + '\'' +
                "categories=" + categories.stream().map(Category::toString).collect(Collectors.joining()) +
                '}';
    }
}

