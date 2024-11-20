create table notas(

    id int primary key not null auto_increment,
    matricula_id int,
    disciplina_id int,
    nota decimal(5,2),
    data_lancamento date,
    foreign key (matricula_id) references matriculas(id),
    foreign key (disciplina_id) references disciplinas(id)

);