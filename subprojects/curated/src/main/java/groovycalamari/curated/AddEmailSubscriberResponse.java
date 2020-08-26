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
import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Introspected
public class AddEmailSubscriberResponse {
    @NonNull
    @NotNull
    private Boolean success;

    @JsonProperty("error_message")
    @Nullable
    private String errorMessage;

    @Nullable
    private List<String> errors = new ArrayList<>();


    public AddEmailSubscriberResponse() {
    }

    @NonNull
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(@NonNull Boolean success) {
        this.success = success;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(@Nullable String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Nullable
    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(@Nullable List<String> errors) {
        this.errors = errors;
    }
}
