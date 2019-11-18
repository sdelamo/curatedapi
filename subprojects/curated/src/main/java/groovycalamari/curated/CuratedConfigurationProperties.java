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

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.http.client.HttpClientConfiguration;
import io.micronaut.runtime.ApplicationConfiguration;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotBlank;
import java.time.Duration;

/**
 * {@link ConfigurationProperties} for {@link CuratedClient}.
 */
@ConfigurationProperties(CuratedConfigurationProperties.PREFIX)
public class CuratedConfigurationProperties extends HttpClientConfiguration {
    public static final String PREFIX = "curated";
    public static final String HOST_LIVE = "https://api.curated.co";
    public static final String V1 = "v1";

    private final CuratedConnectionPoolConfiguration connectionPoolConfiguration;

    @Nonnull
    @NotBlank
    private String url = HOST_LIVE;

    @Nonnull
    @NotBlank
    private String apiVersion = V1;

    @Nonnull
    @NotBlank
    private String publicationKey;

    @Nonnull
    @NotBlank
    private String apiKey;

    public CuratedConfigurationProperties(final ApplicationConfiguration applicationConfiguration,
                                          final CuratedConnectionPoolConfiguration connectionPoolConfiguration) {
        super(applicationConfiguration);
        this.connectionPoolConfiguration = connectionPoolConfiguration;
    }

    @NotBlank
    @Nonnull
    public String getApiVersion() {
        return this.apiVersion;
    }

    public void setApiVersion(@Nonnull @NotBlank String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @NotBlank
    @Nonnull
    public String getPublicationKey() {
        return this.publicationKey;
    }

    public void setPublicationKey(@Nonnull @NotBlank String publicationKey) {
        this.publicationKey = publicationKey;
    }

    @NotBlank
    @Nonnull
    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(@Nonnull @NotBlank String apiKey) {
        this.apiKey = apiKey;
    }

    @NotBlank
    @Nonnull
    public String getUrl() {
        return this.url;
    }

    public void setUrl(@Nonnull @NotBlank String url) {
        this.url = url;
    }

    @Override
    public ConnectionPoolConfiguration getConnectionPoolConfiguration() {
        return this.connectionPoolConfiguration;
    }

    /**
     * {@link ConnectionPoolConfiguration} for {@link CuratedClient}.
     */
    @ConfigurationProperties(ConnectionPoolConfiguration.PREFIX)
    public static class CuratedConnectionPoolConfiguration extends ConnectionPoolConfiguration {
    }

    /**
     * Extra {@link ConfigurationProperties} to set the values for the {@link io.micronaut.retry.annotation.Retryable} annotation on {@link CuratedClient}.
     */
    @ConfigurationProperties(CuratedRetryConfiguration.PREFIX)
    public static class CuratedRetryConfiguration {

        public static final String PREFIX = "retry";

        private static final Duration DEFAULT_DELAY = Duration.ofSeconds(5);
        private static final int DEFAULT_ATTEMPTS = 0;

        /**
         * @return The delay between retry attempts
         */
        private Duration delay = DEFAULT_DELAY;

        /**
         * @return The maximum number of retry attempts
         */
        private int attempts = DEFAULT_ATTEMPTS;

        public Duration getDelay() {
            return delay;
        }

        public void setDelay(Duration delay) {
            this.delay = delay;
        }

        public int getAttempts() {
            return attempts;
        }

        public void setAttempts(int attempts) {
            this.attempts = attempts;
        }
    }
}
