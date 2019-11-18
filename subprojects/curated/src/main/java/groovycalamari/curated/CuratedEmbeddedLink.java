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

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CuratedEmbeddedLink {
    private String identifier;
    private String title;
    private String url_domain;
    private String url;

    public CuratedEmbeddedLink() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl_domain() {
        return url_domain;
    }

    public void setUrl_domain(String url_domain) {
        this.url_domain = url_domain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
