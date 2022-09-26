create table usuarios (
id_usuario bigint auto_increment,
desc_nome varchar(255) not null ,
num_idade integer not null,
primary key (id_usuario)
);

INSERT INTO usuarios (desc_nome, num_idade) VALUES ('João', 20);
