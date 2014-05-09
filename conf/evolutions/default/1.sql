# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table timer (
  id                        bigint not null,
  task_name                 varchar(255),
  constraint pk_timer primary key (id))
;

create table account (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_account primary key (email))
;

create sequence timer_seq;

create sequence account_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists timer;

drop table if exists account;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists timer_seq;

drop sequence if exists account_seq;

