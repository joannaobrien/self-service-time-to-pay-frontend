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
