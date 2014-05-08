package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Timer;
import play.Logger;
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

/**
 * Created by saumil on 2014/05/08.
 */
public class TimerApplication extends Controller {
    public static Result index() {
        return ok();
    }

    @Transactional
    public static Result addTimer() {
        Form<Timer> form = Form.form(Timer.class).bindFromRequest();
        Timer timer = form.get();
        JPA.em().persist(timer);
        if ( Logger.isDebugEnabled() ){
            Logger.debug("Timer[" + timer.id + "] added to database.");
        }
        return redirect(controllers.routes.Application.index());
    }

    @Transactional(readOnly = true)
    public static Result getAllTimers(){
        long startTime = System.currentTimeMillis();

        CriteriaBuilder builder = JPA.em().getCriteriaBuilder();
        CriteriaQuery<Timer> query = builder.createQuery(Timer.class);
        Root<Timer> root = query.from(Timer.class);

        CriteriaQuery<Timer> allTimers = query.select(root);
        TypedQuery<Timer> allQuery = JPA.em().createQuery(allTimers);

        JsonNode jsonNodes = toJson(allQuery.getResultList());
        Logger.info("getAllTimers " + (System.currentTimeMillis() - startTime) + " ms" );
        return ok(jsonNodes);
    }
}
