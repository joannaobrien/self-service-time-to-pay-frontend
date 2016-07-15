/*
 * Copyright 2016 HM Revenue & Customs
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

package uk.gov.hmrc.ssttp.config;

import com.typesafe.config.ConfigFactory;

public class AppConfig {
    private static final String contactHost = "contact-frontend.host";
    private static final String contactFormServiceIdentifier = "self-service-time-to-pay";

    public String assetsPrefix = loadConfig("assets.url") + loadConfig("assets.version");
    public String analyticsToken = loadConfig("google-analytics.token");
    public String analyticsHost = loadConfig("google-analytics.host");
    public String reportAProblemPartialUrl = loadConfig(contactHost) + "/contact/problem_reports_ajax?service="+ contactFormServiceIdentifier;
    public String reportAProblemNonJSUrl = loadConfig(contactHost) + "/contact/problem_reports_nonjs?service="+contactFormServiceIdentifier;

    private String loadConfig(String key) {return ConfigFactory.load().getString(key);}
}