create table endereco(
  id serial primary key,
  rua varchar(100),
  numero varchar(10),
  cidade varchar(100),
  bairro varchar(100),
  uf varchar(15)
);