create table turmas (
    id int primary key not null auto_increment,
    ano int,
    semestre int,
    curso_id int,
    foreign key (curso_id) references cursos(id)
);