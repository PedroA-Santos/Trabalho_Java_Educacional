ALTER TABLE alunos ADD COLUMN turma_id INT;
ALTER TABLE alunos ADD CONSTRAINT FK_aluno_turma FOREIGN KEY (turma_id) REFERENCES turmas(id);
