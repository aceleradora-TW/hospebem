alter table solicitacao add column acompanhante_id integer;

alter table solicitacao
add constraint fk_acompanhante
foreign key (acompanhante_id)
references acompanhante(id);