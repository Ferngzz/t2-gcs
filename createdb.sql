create table DEPARTAMENTO
(
    id_departamento int auto_increment
        primary key,
    limite          decimal(12, 2) not null,
    nome            varchar(50)    not null
);

create table FUNCIONARIO
(
    id_funcionario  int auto_increment
        primary key,
    nome            varchar(50)                           null,
    cargo           enum ('Funcionario', 'Administrador') not null,
    id_departamento int                                   null,
    constraint FK_FUNCIONARIO_0
        foreign key (id_departamento) references DEPARTAMENTO (id_departamento)
);

create table ITEM
(
    id_item        int auto_increment
        primary key,
    valor_unitario decimal(12, 2) null,
    nome           varchar(50)    not null
);

create table PEDIDO
(
    id_pedido       int auto_increment
        primary key,
    data_abertura   date                                                                 not null,
    data_fechamento date                                                                 null,
    id_funcionario  int                                                                  not null,
    status          enum ('ABERTO', 'FECHADO', 'APROVADO', 'REPROVADO') default 'ABERTO' not null,
    constraint FK_PEDIDO_0
        foreign key (id_funcionario) references FUNCIONARIO (id_funcionario)
);
CREATE TABLE ITEM_PEDIDO (
                             id_pedido INT NOT NULL,
                             id_item INT NOT NULL,
                             CONSTRAINT FK_ITEM_PEDIDO_0
                                 FOREIGN KEY (id_pedido) REFERENCES PEDIDO (id_pedido) ON DELETE CASCADE,
                             CONSTRAINT FK_ITEM_PEDIDO_1
                                 FOREIGN KEY (id_item) REFERENCES ITEM (id_item)
);