create schema if not exists public;

create database ria_dataset with owner postgres;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

CREATE TABLE users
(
    id            serial,
    first_name    VARCHAR(100) NOT NULL,
    last_name     VARCHAR(100),
    age           int CHECK (age > 0),
    nickname      VARCHAR(20)  NOT NULL,
    email         VARCHAR(100) NOT NULL,
    password      VARCHAR(60)  NOT NULL,
    register_time bigint,
    user_role     VARCHAR(25),
    user_status   VARCHAR(25),
    PRIMARY KEY (id)
);

alter table users
    owner to postgres;

CREATE TABLE categories
(
    id serial,
    value int UNIQUE,
    name  varchar NOT NULL,
    PRIMARY KEY (id)
);

alter table categories
    owner to postgres;

CREATE TABLE bodystyles (
    id serial,
    value int not null ,
    name varchar NOT NULL,
    category_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id)
                        REFERENCES categories (value)
);

alter table bodystyles
    owner to postgres;

CREATE TABLE states (
      id serial,
      value int unique ,
      name varchar NOT NULL,
      PRIMARY KEY (id)
);

alter table states
    owner to postgres;

CREATE TABLE state_cities (
      id serial,
      value int not null ,
      name varchar NOT NULL,
      state_id int NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (state_id)
          REFERENCES states (value)
);

alter table state_cities
    owner to postgres;

CREATE TABLE auto_colors (
     id serial,
     value int unique ,
     name varchar NOT NULL,
     PRIMARY KEY (id)
);

alter table auto_colors
    owner to postgres;

CREATE TABLE auto_marks (
     id serial,
     value int not null ,
     name varchar NOT NULL,
     category_id int NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (category_id)
         REFERENCES categories (value)
);

alter table auto_marks
    owner to postgres;

CREATE TABLE auto_models (
     id serial,
     value int not null ,
     name varchar NOT NULL,
     category_id int,
     mark_id int,
     PRIMARY KEY (id),
     FOREIGN KEY (category_id)
         REFERENCES categories (value)
);

alter table auto_models
    owner to postgres;

CREATE TABLE currencies (
    id serial,
    name varchar NOT NULL,
    PRIMARY KEY (id)
);

alter table currencies
    owner to postgres;

CREATE TABLE ticket_submission (
    id serial,
    value int unique ,
    name varchar NOT NULL,
    PRIMARY KEY (id)
);

alter table ticket_submission
    owner to postgres;

CREATE TABLE gearboxes (
    id serial,
    value int,
    name varchar NOT NULL,
    category_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id)
        REFERENCES categories (value)
);

alter table gearboxes
    owner to postgres;

CREATE TABLE fuel_type (
    id serial,
    value int,
    name varchar NOT NULL,
    PRIMARY KEY (id)
);

alter table fuel_type
    owner to postgres;

CREATE TABLE drive_types (
     id serial,
     value int,
     name varchar NOT NULL,
     category_id int NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (category_id)
         REFERENCES categories (value)
);

alter table drive_types
    owner to postgres;

-- search history tables: search_history & search_history_car_data
CREATE TABLE search_history (
    id serial,
    user_id int not null,
    category_id int NOT NULL,
    currency int,
    price_ot int,
    price_do int,
    color_id int,
    countpage int,
    top int,
    time_created bigint,
    subscription boolean,
    PRIMARY KEY (id)
);

-- alter table search_history add column countpage int default 0;

alter table search_history
    owner to postgres;

CREATE TABLE search_history_car_data (
    id serial,
    body_id int not null,
    marka_id int NOT NULL,
    model_id int NOT NULL,
    s_years int,
    to_years int,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (search_history_id)
            REFERENCES search_history (id)
);

alter table search_history_car_data
    owner to postgres;

CREATE TABLE search_history_location_id (
    id serial,
    state_id int not null,
    city_id int NOT NULL,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (state_id)
                        REFERENCES states (value),
    FOREIGN KEY (search_history_id)
                        REFERENCES search_history (id)
);

alter table search_history_location_id
    owner to postgres;

CREATE TABLE search_history_gearbox_id (
    id serial,
    gearbox_id int not null,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (search_history_id)
                        REFERENCES search_history (id)
);

alter table search_history_gearbox_id
    owner to postgres;

CREATE TABLE search_history_fueltype_id (
    id serial,
    fueltype_id int not null,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (search_history_id)
                        REFERENCES search_history (id)
);

alter table search_history_fueltype_id
    owner to postgres;

CREATE TABLE search_history_drive_type_id (
    id serial,
    drivetype_id int not null,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (search_history_id)
                        REFERENCES search_history (id)
);

alter table search_history_drive_type_id
    owner to postgres;

ALTER TABLE search_history
    ADD CONSTRAINT search_history_user_if_fkey FOREIGN KEY (user_id) REFERENCES users (id);
