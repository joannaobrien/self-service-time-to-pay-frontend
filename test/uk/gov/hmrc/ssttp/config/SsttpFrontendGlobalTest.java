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

import org.fest.assertions.Assertions;
import org.junit.Test;
import play.i18n.Messages;
import play.mvc.Result;

import static play.test.Helpers.*;

public class SsttpFrontendGlobalTest extends SsttpFrontendGlobal {

    @Test
    public void renderInternalServerError() {
        running(fakeApplication(), () -> {
            Exception exception = new Exception("Runtime exception");
            Result result = resolveError(exception);
            Assertions.assertThat(status(result)).isEqualTo(INTERNAL_SERVER_ERROR);
        });
    }

    @Test
    public void renderNotFound() {
        running(testServer(3333, fakeApplication(new SsttpFrontendGlobal())), HTMLUNIT, browser -> {
            browser.goTo("http://localhost:3333");
            Assertions.assertThat(browser.pageSource()).contains(Messages.get("global.error.pageNotFound404.title"));
        });
    }
}

