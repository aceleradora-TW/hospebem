create table acompanhantes (
  id serial primary key,
  solicitacao_id int4 not null references solicitacao (id),
  nome varchar(255) not null,
  genero varchar(1) not null
);