 create table usuarios(
  id serial primary key,
  nome varchar(100) unique not null,
  tipo varchar(100),
  senha varchar(100) not null,
  hospital_ref varchar(100)
);