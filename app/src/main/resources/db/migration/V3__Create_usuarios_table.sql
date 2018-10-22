 create table usuario(
  id serial primary key,
  nome varchar(100),
  tipo varchar(100),
  senha varchar(100),
  hospital_ref varchar(100)
);