alter table solicitacao add column endereco_id integer;

alter table solicitacao
add constraint fk_endereco
foreign key (endereco_id)
references endereco(id);