package controllers;

import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login.login;

import static play.data.Form.form;

/**
 * Created by saumil on 2014/05/08.
 */
public class AccountApplication extends Controller {

    // -- Authentication

    public static class Login {
        public String email;
        public String password;

        public String validate(){
            if ( User.authenticate(email, password) == null ){
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static Result login() {
        return ok(
                login.render(form(Login.class))
        );
    }

    public static Result authenticate(){
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if ( loginForm.hasErrors() ){
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(routes.TimerApplication.getAllTimers() );
        }
    }

    public static Result logout(){
        session().clear();
        flash("success", "You've been logged out");
        return redirect( routes.AccountApplication.login() );
    }
}
