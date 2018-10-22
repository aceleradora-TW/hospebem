
alter table acompanhante
add constraint fk_solicitacao
foreign key (solicitacao_id)
references solicitacao(id);