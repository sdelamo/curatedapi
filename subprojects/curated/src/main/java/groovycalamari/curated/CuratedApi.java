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

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Single;
import edu.umd.cs.findbugs.annotations.NonNull;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @see <a href="http://support.curated.co/integrations/getting-started-with-the-curated-api/">Getting started with the Curated API</a>.
 * @see <a href="http://support.curated.co/integrations/fetching-issue-data-with-the-api/">Fetching issue data with the API</a>
 * @author Sergio del Amo
 */
public interface CuratedApi {
    String PATH_ISSUES = "/issues";
    String PATH_EMAIL_SUBSCRIBERS = "/email_subscribers";

    Single<Issue> issue(@PathVariable @NonNull @NotNull @Positive Integer number);

    /**
     * @param perPage How many issues to include in the results. The default value for this is 10 and the maximum value is 250.
     * @param page Which page of data to retrieve. This will be affected by the per_page parameter above.
     * @return List of issues
     */
    Single<Issues> issues(@QueryValue("per_page") @Nullable @Positive @Max(250) Integer perPage,
                          @QueryValue @Nullable @Positive Integer page);


    /**
     * @param page Which page of data to retrieve. This will be affected by the per_page parameter above.
     * @return List of issues
     */
    default Single<Issues> issues(@QueryValue @Nullable @Positive Integer page) {
        return issues(null, page);

    }

    /**
     * Fetches the list of issues
     * @return List of issues
     */
    default Single<Issues> issues() {
        return issues(null, null);
    }

    Single<AddEmailSubscriberResponse> addEmailSubscriber(@Body @Valid @NotNull @NonNull AddEmailSubscriber subscriber);
}