package uk.gov.hmrc.ssttp.config;

import play.GlobalSettings;
import play.Logger;
import play.i18n.Messages;
import play.libs.F.Promise;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.twirl.api.Html;

import static play.mvc.Results.*;

public class SsttpFrontendGlobal extends GlobalSettings {

    public Html standardErrorTemplate(String pageTitle, String heading, String message){
        return views.html.error_template.render(pageTitle, heading, message);
    }

    public Result badRequestTemplate(RequestHeader rh) {
        return badRequest(standardErrorTemplate(
            Messages.get("global.error.badRequest400.title"),
            Messages.get("global.error.badRequest400.heading"),
            Messages.get("global.error.badRequest400.message")));
    }

    public Result notFoundTemplate(RequestHeader rh) {
        return notFound(standardErrorTemplate(
            Messages.get("global.error.pageNotFound404.title"),
            Messages.get("global.error.pageNotFound404.heading"),
            Messages.get("global.error.pageNotFound404.message")));
    }

    public Result internalServerErrorTemplate() {
        return internalServerError(standardErrorTemplate(
            Messages.get("global.error.InternalServerError500.title"),
            Messages.get("global.error.InternalServerError500.heading"),
            Messages.get("global.error.InternalServerError500.message")));
    }

    public Promise<Result> onBadRequest(RequestHeader rh, String error) {
        return Promise.pure(badRequestTemplate(rh));
    }

    public Promise<Result> onHandlerNotFound(RequestHeader rh) {
        return Promise.pure(notFoundTemplate(rh));
    }

    public Promise<Result> onError(RequestHeader rh, Throwable t) {

        return Promise.pure(resolveError(t));
    }

    public Result resolveError(Throwable t) {
        Logger.error(t.getMessage()+t.getCause());
        return internalServerErrorTemplate();
    }
}