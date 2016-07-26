package uk.gov.hmrc.ssttp.config;

import org.junit.Test;
import play.mvc.Result;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.*;

public class SsttpFrontendGlobalTest extends SsttpFrontendGlobal {

    @Test
    public void renderInternalServerError() {
        running(fakeApplication(), () -> {
            Exception exception = new Exception("Runtime exception");
            Result result = resolveError(exception);
            assertThat(status(result), is(INTERNAL_SERVER_ERROR));
        });
    }
}

