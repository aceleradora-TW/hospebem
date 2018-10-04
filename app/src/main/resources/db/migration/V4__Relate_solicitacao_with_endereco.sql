ALTER TABLE solicitacao ADD COLUMN endereco_id INTEGER;

ALTER TABLE solicitacao
  ADD CONSTRAINT fk_endereco
  FOREIGN KEY (endereco_id)
  REFERENCES endereco(id);
