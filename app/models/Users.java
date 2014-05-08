package models;

import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by saumil on 2014/05/08.
 */
@Entity
public class Users {
    @Id
    @GeneratedValue
    public Long id;

    @NotNull
    public String userName;

    @NotNull
    @Constraints.Email
    public String email;

    public Boolean isActive;
}
