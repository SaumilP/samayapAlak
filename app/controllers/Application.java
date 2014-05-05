package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Timer;
import play.data.Form;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static play.libs.Json.toJson;

public class Application extends Controller {

    public static Result index() {
        return ok(views.html.index.render(Form.form(Timer.class)));
    }

    @Transactional
    public static Result addTimer() {
        Form<Timer> form = Form.form(Timer.class).bindFromRequest();
        Timer timer = form.get();
        JPA.em().persist(timer);
        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public static Result getAllTimers(){
        CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
        CriteriaQuery<Timer> query = builder.createQuery(Timer.class);
        Root<Timer> root = query.from(Timer.class);

        CriteriaQuery<Timer> allTimers = query.select(root);
        TypedQuery<Timer> allQuery = JPA.em().createQuery(allTimers);

        JsonNode jsonNodes = toJson(allQuery.getResultList());
        return ok(jsonNodes);
    }

}
