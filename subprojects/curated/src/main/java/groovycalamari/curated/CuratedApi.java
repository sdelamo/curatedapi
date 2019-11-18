/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2019 Sergio del Amo.
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

import io.micronaut.http.annotation.PathVariable;
import io.reactivex.Single;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;

/**
 * @see <a href="http://support.curated.co/integrations/getting-started-with-the-curated-api/">Getting started with the Curated API</a>.
 */
public interface CuratedApi {
    Single<CuratedIssueResponse> issueByNumber(@PathVariable @Nonnull @NotNull Integer issueNumber);
    Single<CuratedIssuesResponse> issues();
}