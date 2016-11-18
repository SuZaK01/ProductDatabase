# product schema

# --- !Ups

create table "products" (
    "ean" bigint(20) not null auto_increment,
    "name" varchar(255) not null,
    "descr" varchar(250) not null
);

CREATE TABLE "users" (
  "id" bigint(20) not null auto_increment,
  "email" varchar(255) NOT NULL ,
  "first_name" varchar(255),
  "last_name" varchar(255)
);


CREATE TABLE "addresses" (
  "id" bigint(20) not null auto_increment,
  "customer_id" bigint(20) NOT NULL,
  "street" varchar(255),
  "house_nr" varchar(255),
  "apartament_nr" varchar(255),
  "zip" varchar(255),
  "city" varchar(255),
  "country" varchar(255)
);

CREATE TABLE "customers" (
  "id" bigint(20) not null auto_increment,
  "first_name" varchar(255) NOT NULL,
  "last_name" varchar(255) NOT NULL,
  "properties1" varchar(255),
  "properties2" VARCHAR(255),
  "properties3" VARCHAR(255),
  "properties4" VARCHAR(255),
  "properties5" VARCHAR(255),
  "properties6" VARCHAR(255),
  "properties7" VARCHAR(255),
  "properties8" VARCHAR(255),
  "properties9" VARCHAR(255),
  "properties10" VARCHAR(255),
  "properties11" VARCHAR(255),
  "properties12" VARCHAR(255),
  "properties13" VARCHAR(255),
  "properties14" VARCHAR(255),
  "properties15" VARCHAR(255),
  "properties16" VARCHAR(255),
  "properties17" VARCHAR(255),
  "properties18" VARCHAR(255),
  "properties19" VARCHAR(255)
);




# --- !Downs
drop table "products";
drop table "users";
drop table "addresses";
drop table "customers";