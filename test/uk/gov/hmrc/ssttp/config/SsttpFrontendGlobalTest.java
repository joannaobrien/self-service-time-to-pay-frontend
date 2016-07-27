package uk.gov.hmrc.ssttp.config;

import org.junit.Test;
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
}

