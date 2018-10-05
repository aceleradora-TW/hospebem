 create table solicitacao(
  id serial primary key,
  nome varchar(100),
  rua varchar(100),
  numero varchar(10),
  cidade varchar(100),
  bairro varchar(100),
  uf varchar(4),
  nome_acompanhante1 varchar(100),
  nome_acompanhante2 varchar(100)
);

