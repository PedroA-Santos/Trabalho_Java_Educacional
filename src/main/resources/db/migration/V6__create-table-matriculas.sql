create table matriculas (
    id int primary key not null auto_increment,
    aluno_id int,
    turma_id int,
    foreign key (aluno_id) references alunos(id),
    foreign key (turma_id) references turmas(id)

);