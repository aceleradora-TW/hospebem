CREATE TABLE usuarios(
  id serial primary key,
  usuario varchar(255) not null unique,
  senha varchar(255) not null
);