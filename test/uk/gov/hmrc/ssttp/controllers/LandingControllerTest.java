package uk.gov.hmrc.ssttp.controllers;

import org.junit.Test;
import play.twirl.api.Content;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static play.test.Helpers.contentAsString;

public class LandingControllerTest {

    @Test
    public void renderTemplate() {
        Content html = views.html.landing.render("Welcome to Self Service Time To Pay");
        assertThat(html.contentType(), is("text/html"));
        assertThat(contentAsString(html), containsString("Welcome to Self Service Time To Pay"));
    }
}
