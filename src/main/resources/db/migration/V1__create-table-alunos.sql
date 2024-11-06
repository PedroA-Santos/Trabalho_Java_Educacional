create table alunos (
    id int not null primary key auto_increment,
    nome varchar(100),
    email varchar(100),
    matricula varchar(20),
    data_nascimento date
);
