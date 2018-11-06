# Hospebem

Sistema de reserva de leitos a ONG Via Vida.

[![CircleCI](https://circleci.com/gh/aceleradora-TW/hospebem/tree/master.svg?style=svg)](https://circleci.com/gh/aceleradora-TW/hospebem/tree/master)

## Dependências

- Java 8
- Docker
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
             

Para adicionar as tabelas ao B.D. execute as migrações do banco de dados:


```sh
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

- [Circle CI](https://circleci.com/gh/aceleradora-TW/hospebem)


## Ambientes

__Integração__

Endereço: https://hospebem-staging.herokuapp.com

Este ambiente é atualizado toda vez que uma alteração é introduzida no projeto e as verificações automatizados do Circle CI executam
exitosamente. Ou seja é o ambiente mais instável e que recebe as alterações mais recentes.

__Produção__

Endereço: https://hospebem-prod.herokuapp.com

