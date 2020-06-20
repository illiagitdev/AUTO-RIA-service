-- schema

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

CREATE TABLE categories
(
    id serial,
    value int UNIQUE,
    name  varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE bodystyles (
    id serial,
    value int not null ,
    name varchar NOT NULL,
    category_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id)
                        REFERENCES categories (value)
);

CREATE TABLE states (
      id serial,
      value int unique ,
      name varchar NOT NULL,
      PRIMARY KEY (id)
);

CREATE TABLE state_cities (
      id serial,
      value int not null ,
      name varchar NOT NULL,
      state_id int NOT NULL,
      PRIMARY KEY (id),
      FOREIGN KEY (state_id)
          REFERENCES states (value)
);

CREATE TABLE auto_colors (
     id serial,
     value int unique ,
     name varchar NOT NULL,
     PRIMARY KEY (id)
);

CREATE TABLE auto_marks (
     id serial,
     value int not null ,
     name varchar NOT NULL,
     category_id int NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (category_id)
         REFERENCES categories (value)
);

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

CREATE TABLE currencies (
    id serial,
    name varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ticket_submission (
    id serial,
    value int unique ,
    name varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE gearboxes (
    id serial,
    value int,
    name varchar NOT NULL,
    category_id int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id)
        REFERENCES categories (value)
);

CREATE TABLE fuel_type (
    id serial,
    value int,
    name varchar NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE drive_types (
     id serial,
     value int,
     name varchar NOT NULL,
     category_id int NOT NULL,
     PRIMARY KEY (id),
     FOREIGN KEY (category_id)
         REFERENCES categories (value)
);

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

CREATE TABLE search_history_gearbox_id (
    id serial,
    gearbox_id int not null,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (search_history_id)
                        REFERENCES search_history (id)
);

CREATE TABLE search_history_fueltype_id (
    id serial,
    fueltype_id int not null,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (search_history_id)
                        REFERENCES search_history (id)
);

CREATE TABLE search_history_drive_type_id (
    id serial,
    drivetype_id int not null,
    search_history_id int,
    PRIMARY KEY (id),
    FOREIGN KEY (search_history_id)
                        REFERENCES search_history (id)
);

ALTER TABLE search_history
    ADD CONSTRAINT search_history_user_if_fkey FOREIGN KEY (user_id) REFERENCES users (id);
