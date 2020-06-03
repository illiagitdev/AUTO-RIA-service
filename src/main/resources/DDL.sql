create schema if not exists public;

create database ria_dataset with owner postgres;

comment on schema public is 'standard public schema';

alter schema public owner to postgres;

CREATE TABLE users (
  id serial,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100),
  age int CHECK(age > 0),
  nickname VARCHAR(20) NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(60) NOT NULL,
  register_time bigint,
  user_role VARCHAR(25),
  user_status VARCHAR(25),
  PRIMARY KEY (id)
);

alter table users owner to postgres;

insert INTO users(id, first_name, last_name, age, nickname, email, password, register_time, user_role, user_status)
 values (1, 'Al', 'Pacino', 80, 'alPacino', 'alPacino@mail.com', '$2y$12$r2TbvuL69DictaL0nBGPBe2c4v/LRHzKrwtcP4bEjRAQuNFCjSLle', 0, 'ROLE_USER', 'NEW');
insert INTO users(id, first_name, last_name, age, nickname, email, password, register_time, user_role, user_status)
 values (2, 'Tom', 'Hanks', 64, 'tomHanks', 'tomHanks@mail.com', '$2y$12$V10R6B.vUJ8q.GCyYrCyjezzsKzyHqEjfPiB1yVtjLez2XXkE8GBi', 0, 'ROLE_USER', 'ACTIVE');
insert INTO users(id, first_name, last_name, age, nickname, email, password, register_time, user_role, user_status)
 values (3, 'Morgan', 'Freeman', 83, 'morganFreeman', 'morganFreeman@mail.com', '$2y$12$jeba0QMembwuR0sWIPpAXOj4sF47g3X5Om3pwWOkgo5QbDzJjizQG', 0, 'ROLE_ADMIN', 'ACTIVE');
insert INTO users(id, first_name, last_name, age, nickname, email, password, register_time, user_role, user_status)
 values (4, 'Brad', 'Pitt', 57, 'bradPitt', 'bradPitt@mail.com', '$2y$12$wF3zx1VkaydwGJjjpJdzAuh9O0MuwJa4T330Y2qJ5RY2A0VIs9QMi', 0, 'ROLE_ADMIN', 'DISABLED');

