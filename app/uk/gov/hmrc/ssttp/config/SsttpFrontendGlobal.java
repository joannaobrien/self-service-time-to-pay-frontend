package uk.gov.hmrc.ssttp.config;

import play.GlobalSettings;
import play.Logger;
import play.i18n.Messages;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.twirl.api.Html;

import static java.lang.String.format;
import static play.libs.F.Promise.pure;
import static play.mvc.Http.Status.*;
import static play.mvc.Results.*;

public class SsttpFrontendGlobal extends GlobalSettings {

    private Html standardErrorTemplate(int errorCode){
        return views.html.error_template.render(
                Messages.get(format("global.error.%d.title", errorCode)),
                Messages.get(format("global.error.%d.heading", errorCode)),
                Messages.get(format("global.error.%d.message", errorCode)));
    }

    public Promise<Result> onBadRequest(RequestHeader rh, String error) {
        return pure(badRequest(standardErrorTemplate(BAD_REQUEST)));
    }

    public Promise<Result> onHandlerNotFound(RequestHeader rh) {
        return pure(notFound(standardErrorTemplate(NOT_FOUND)));
    }

    public Promise<Result> onError(RequestHeader rh, Throwable t) {
        Logger.error(t.getMessage(), t);
        return pure(internalServerError(standardErrorTemplate(INTERNAL_SERVER_ERROR)));
    }
}