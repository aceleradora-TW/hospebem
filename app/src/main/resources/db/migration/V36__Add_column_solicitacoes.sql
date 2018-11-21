ALTER TABLE solicitacoes ADD COLUMN quarto_id integer;

ALTER TABLE solicitacoes
ADD CONSTRAINT fk_quarto
FOREIGN KEY (quarto_id)
REFERENCES quartos(id);