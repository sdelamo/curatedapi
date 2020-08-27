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
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.uri.UriTemplate;
import io.reactivex.Single;
import javax.annotation.Nullable;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ManualCuratedApi implements CuratedApi {

    private final CuratedConfiguration curatedConfiguration;
    private RxHttpClient rxHttpClient;

    public ManualCuratedApi(CuratedConfiguration curatedConfiguration) throws MalformedURLException {
        this.curatedConfiguration = curatedConfiguration;
        rxHttpClient = RxHttpClient.create(new URL(curatedConfiguration.getUrl()));
    }

    @Override
    public Single<Issue> issue(@NotNull @NotNull @Positive Integer number) {
        final String uri = uriPrefix() + PATH_ISSUES + "/" + number;
        return rxHttpClient.retrieve(getRequest(uri), Issue.class).firstOrError();
    }

    @Override
    public Single<Issues> issues(@Nullable @Positive @Max(250) Integer perPage,
                                 @Nullable @Positive Integer page) {
        final String uri = issuesUri(perPage, page);
        return rxHttpClient.retrieve(getRequest(uri), Issues.class).firstOrError();
    }

    @Override
    public Single<AddEmailSubscriberResponse> addEmailSubscriber(@Body @Valid @NotNull @NonNull AddEmailSubscriber subscriber) {
        final String uri = uriPrefix() + PATH_EMAIL_SUBSCRIBERS;
        return rxHttpClient.retrieve(addHeaders(HttpRequest.POST(uri, subscriber)), AddEmailSubscriberResponse.class).firstOrError();
    }

    private String uriPrefix() {
        return "/" + curatedConfiguration.getPublicationKey() + "/api/" + curatedConfiguration.getApiVersion();
    }

    private String issuesUri(@Nullable Integer perPage,
                             @Nullable Integer page) {
        String uri = uriPrefix() + PATH_ISSUES;
        Map<String, Object> parameters = new HashMap<>();
        if (perPage != null) {
            parameters.put("per_page", perPage);
        }
        if (page != null) {
            parameters.put("page", page);
        }
        if (!parameters.isEmpty()) {
            return UriTemplate.of(uri + "{?per_page,page}").expand(parameters);
        }
        return uri;
    }

    private String getAuthorizationValue() {
        return "Token token=\"" + curatedConfiguration.getApiKey() + "\"";
    }

    private HttpRequest<?> getRequest(String uri) {
        return addHeaders(HttpRequest.GET(uri));
    }

    private MutableHttpRequest<?> addHeaders(MutableHttpRequest<?> request) {
        return request
                .header(HttpHeaders.AUTHORIZATION, getAuthorizationValue())
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
    }
}
