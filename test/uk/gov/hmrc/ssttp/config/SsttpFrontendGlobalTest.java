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

import org.junit.Test;
import play.i18n.Messages;
import play.mvc.Http;
import play.mvc.Result;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static play.test.Helpers.*;

public class SsttpFrontendGlobalTest extends SsttpFrontendGlobal {
    private Http.RequestHeader rh = mock(Http.RequestHeader.class);
    private int futureTimeout = 3000;

    @Test
    public void renderInternalServerError() {
        running(fakeApplication(), () -> {
            Exception exception = new Exception("Runtime exception");
            Result result = onError(rh, exception).get(futureTimeout);
            assertThat(status(result), is(INTERNAL_SERVER_ERROR));
            assertThat(contentAsString(result), containsString("Sorry, we’re experiencing technical difficulties"));
            assertThat(contentAsString(result), containsString("Please try again in a few minutes"));
        });
    }

    @Test
    public void renderPageNotFound() {
        running(fakeApplication(), () -> {
            Result result = onHandlerNotFound(rh).get(futureTimeout);
            assertThat(status(result), is(NOT_FOUND));
            assertThat(contentAsString(result), containsString("This page can’t be found"));
            assertThat(contentAsString(result), containsString("Please check that you have entered the correct web address"));
        });
    }

    @Test
    public void renderBadRequest() {
        running(fakeApplication(), () -> {
            Result result = onBadRequest(rh, "").get(futureTimeout);
            assertThat(status(result), is(BAD_REQUEST));
            assertThat(contentAsString(result), containsString("Bad request"));
            assertThat(contentAsString(result), containsString("Please check that you have entered the correct web address"));
        });
    }

    @Test
    public void renderNotFoundWithServer() {
        running(testServer(3333, fakeApplication(new SsttpFrontendGlobal())), HTMLUNIT, browser -> {
            browser.goTo("http://localhost:3333");
            assertThat(browser.pageSource(), containsString(Messages.get("global.error.404.title")));
        });
    }
}
