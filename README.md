# Hospebem

Uma aplicação mínima em Spring Boot.

[![CircleCI](https://circleci.com/gh/aceleradora-TW/esqueleto-ambulante/tree/master.svg?style=svg)](https://circleci.com/gh/aceleradora-TW/esqueleto-ambulante/tree/master)

## Dependências

- Java 8
- Postgres
- Node/npm


## Configuração inicial

Cria um container com postgres

```sh
./banco run
```

Cria um banco

```sh
./banco create
```

Acessa o banco

```sh
./banco console
```

Para adicionar tabelas ao B.D. a medida que a aplicação vai evoluindo utilizamos o Flyway que realiza migrações de versões de tabelas
a partir do Intellij. As migrações são escritas em SQL, dentro do pacote "migrates" no Intellij.
DOCUMENTAÇÃO COMPLETA FLYWAY ->https://flywaydb.org/documentation/migrations#repeatable-migrations

RESUMO:
Nomeação do arquivo SQL :
Prefixo : V para migrações com versão, U para desfazer migrações, R para migrações repetíveis

EXMPLO:
                R__My_view; 

                U1_1__Fix_indexes;

                V1__Inicial_versão;

                V1_1__Fix_indexes
             
Para adicionar as tabelas ao B.D. execute as migrações do banco de dados:

```bash
./gradlew flywayMigrate -i
```

Para executar a aplicação:

```sh
./gradlew bootRun
```

Executando os testes:

```sh
./gradlew test
```

Demais comandos disponíveis:

```sh
./gradlew tasks
```


## Ferramentas

- [Circle CI](https://circleci.com/gh/aceleradora-TW/esqueleto-ambulante)


## Ambientes

__Integração__

Endereço: https://esqueleto-integracao.herokuapp.com

Este ambiente é atualizado toda vez que uma alteração é introduzida no projeto e as verificações automatizados do Circle CI executam
exitosamente. Ou seja é o ambiente mais instável e que recebe as alterações mais recentes.
