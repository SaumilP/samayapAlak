package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by saumil on 2014/05/05.
 */
@Entity
public class Timer {
    @Id
    @GeneratedValue
    public Long id;

    @Constraints.Required
    public String taskName;
}
