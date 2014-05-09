package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.landing.index;

public class HomeApplication extends Controller {
    public static Result index() {
        return ok(index.render());
    }
}
