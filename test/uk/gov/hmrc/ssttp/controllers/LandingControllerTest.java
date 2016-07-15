package uk.gov.hmrc.ssttp.controllers;

import org.junit.Test;
import play.twirl.api.Content;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;

public class LandingControllerTest {

    @Test
    public void renderTemplate() {
        Content html = views.html.landing.render("Welcome to Self Service Time To Pay");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Welcome to Self Service Time To Pay");
    }

}
