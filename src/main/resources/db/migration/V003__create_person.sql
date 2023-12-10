CREATE TABLE
    person (
        id BIGINT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(255) NOT NULL,
        active BOOLEAN NOT NULL,
        street VARCHAR(255),
        number VARCHAR(10),
        complement VARCHAR(100),
        neighborhood VARCHAR(100),
        city VARCHAR(100),
        state VARCHAR(2),
        pobox VARCHAR(10)
    ) engine = InnoDB default charset = utf8;

-- Active: 1702092970880@@127.0.0.1@3306@bill-api

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "José da Silva",
        true,
        "Alameda Lugar Bonito",
        "123",
        "Casa",
        "Boné Azul",
        "Macapá",
        "AP",
        "68909-607"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Maria Antonieta",
        true,
        "Rua Doutor Manoel Brasil",
        "45",
        "Fundos",
        "Congós",
        "Macapá",
        "AP",
        "68904-345"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Francisco Morato",
        true,
        "Rua Acrísio Corrêa",
        "5",
        "Loja",
        "Jardim São Conrado",
        "Campo Grande",
        "MS",
        "79093-310"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Wesley Willians",
        true,
        "Avenida Saíra-prateada",
        "85",
        "Quadra 3",
        "Golden Garden Residence Condomínio",
        "Arapongas",
        "PR",
        "86701-865"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Antonio Sergio",
        true,
        "Rua João Lins Carneiro Albuquerque Filho",
        "51",
        "",
        "Jardins",
        "Aracaju",
        "SE",
        "49025-745"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Wesinton Teodoro da Silva",
        true,
        "Rua Boa Esperança",
        "586",
        "Fundos",
        "Toneto",
        "Nova Xavantina",
        "MT",
        "78690-000"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "João Neris da Silva",
        true,
        "Rua São Conrado",
        "153",
        "",
        "Praia",
        "Nova Xavantina",
        "MT",
        "78690-000"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Juventivo Oliveira Santos",
        true,
        "Alameda Belém",
        "13",
        "",
        "Catanduvas",
        "Varginha",
        "MG",
        "37010-070"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Emanuel Alves Rezende",
        true,
        "Estrada da Lagoa",
        "S/N",
        "Fazenda",
        "Rural",
        "Piumhi",
        "MG",
        "35015-071"
    );

insert into
    person (
        name,
        active,
        street,
        number,
        complement,
        neighborhood,
        city,
        state,
        pobox
    )
values (
        "Maria Rezende de Oliveira",
        true,
        "Estrada da Lagoa",
        "S/N",
        "Fazenda",
        "Rural",
        "Piumhi",
        "MG",
        "35015-071"
    );