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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio del Amo
 */
@Introspected
public class Category {
    /**
     * The identifier for the category (not usually displayed publicly).
     */
    @NonNull
    @NotBlank
    private String code;

    /**
     * The display name of the category.
     */
    @NonNull
    @NotBlank
    private String name;

    /**
     * An array of either {@link Link} Item or {@link Text} Item
     */
    @NonNull
    @NotNull
    private List<ItemResponse> items = new ArrayList<>();

    public Category() {
    }

    /**
     *
     * @return The identifier for the category (not usually displayed publicly).
     */
    @NonNull
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code The identifier for the category (not usually displayed publicly).
     */
    public void setCode(@NonNull String code) {
        this.code = code;
    }

    /**
     *
     * @return The display name of the category.
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The display name of the category.
     */
    public void setName(@NonNull String name) {
        this.name = name;
    }

    /**
     *
     * @return An array of either {@link Link} Item or {@link Text} Item
     */
    @NonNull
    public List<ItemResponse> getItems() {
        return items;
    }

    /**
     *
     * @param items An array of either Text Item or Link Item
     */
    public void setItems(@NonNull List<ItemResponse> items) {
        this.items = items;
    }
}

