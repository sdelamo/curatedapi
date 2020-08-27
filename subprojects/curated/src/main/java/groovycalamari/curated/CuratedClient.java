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
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.Retryable;
import io.micronaut.validation.Validated;
import io.reactivex.Single;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Validated
@Client(
        value = "${" + CuratedConfigurationProperties.PREFIX + ".url:`" + CuratedConfigurationProperties.HOST_LIVE + "`}",
        configuration = CuratedConfigurationProperties.class
)
@Retryable(
        attempts = "${" + CuratedConfigurationProperties.PREFIX + ".retry.attempts:0}",
        delay = "${" + CuratedConfigurationProperties.PREFIX + ".retry.delay:5s}")
@Header(name = HttpHeaders.AUTHORIZATION, value = "Token token=\"" + "${" + CuratedConfigurationProperties.PREFIX + ".api-key}" + "\"")
@Header(name = HttpHeaders.ACCEPT, value = MediaType.APPLICATION_JSON)
@Header(name = HttpHeaders.CONTENT_TYPE, value = MediaType.APPLICATION_JSON)
public interface CuratedClient extends CuratedApi {

    @Override
    @Get("/"+ "${" + CuratedConfigurationProperties.PREFIX + ".publication-key:`" + CuratedConfigurationProperties.V1 + "`}" + "/api/" + "${" + CuratedConfigurationProperties.PREFIX + ".api-version:`" + CuratedConfigurationProperties.V1 + "`}" + PATH_ISSUES + "/{number}")
    Single<Issue> issue(@PathVariable @Nonnull @NotNull @Positive Integer number);

    @Override
    @Get("/"+ "${" + CuratedConfigurationProperties.PREFIX + ".publication-key:`" + CuratedConfigurationProperties.V1 + "`}" + "/api/" + "${" + CuratedConfigurationProperties.PREFIX + ".api-version:`" + CuratedConfigurationProperties.V1 + "`}" + PATH_ISSUES)
    Single<Issues> issues(@QueryValue("per_page") @Nullable @Positive  @Max(250) Integer perPage,
                          @QueryValue @Nullable @Positive Integer page);

    @Post("/"+ "${" + CuratedConfigurationProperties.PREFIX + ".publication-key:`" + CuratedConfigurationProperties.V1 + "`}" + "/api/" + "${" + CuratedConfigurationProperties.PREFIX + ".api-version:`" + CuratedConfigurationProperties.V1 + "`}" + PATH_EMAIL_SUBSCRIBERS)
    Single<AddEmailSubscriberResponse> addEmailSubscriber(@Body @Valid @NotNull @NonNull AddEmailSubscriber subscriber);
}
