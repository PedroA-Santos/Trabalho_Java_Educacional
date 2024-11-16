package br.grupointegrado.Educacional.controller;

import br.grupointegrado.Educacional.dto.CursoRequestDTO;
import br.grupointegrado.Educacional.exceptions.CursoNotFoundException;
import br.grupointegrado.Educacional.exceptions.ValidationException;
import br.grupointegrado.Educacional.model.Curso;
import br.grupointegrado.Educacional.model.Disciplina;
import br.grupointegrado.Educacional.model.Turma;
import br.grupointegrado.Educacional.repository.CursoRepository;
import br.grupointegrado.Educacional.repository.DisciplinaRepository;
import br.grupointegrado.Educacional.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;
    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private DisciplinaRepository discipliaRepository;

    // Buscar todos os cursos
    @GetMapping
    public List<Curso> findAll() {
        return this.repository.findAll();
    }

    // Buscar curso por id
    @GetMapping("/{id}")
    public Curso findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + id + " não encontrado"));
    }

    // Criar um novo curso
    @PostMapping
    public Curso save(@RequestBody @Valid CursoRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ValidationException("O nome do Curso é obrigatório.");
        }
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());
        return this.repository.save(curso);
    }

    // Atualizar um curso
    @PutMapping("/{id}")
    public Curso update(@PathVariable Integer id, @RequestBody @Valid CursoRequestDTO dto) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + id + " não encontrado"));
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());
        return this.repository.save(curso);
    }

    // Deletar um curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + id + " não encontrado"));
        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }

    // Adicionar uma turma a um curso
    @PostMapping("/{id}/add-turma")
    public ResponseEntity<Curso> addTurma(@PathVariable Integer id, @RequestBody @Valid Turma turma) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + id + " não encontrado"));
        turma.setCurso(curso);
        this.turmaRepository.save(turma);
        return ResponseEntity.ok(curso);
    }

    @PostMapping("/{id}/add-disciplina")
    public ResponseEntity<Curso> addDisciplina (@PathVariable Integer id, @RequestBody @Valid Disciplina disciplina){
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new CursoNotFoundException("Curso com id " + id + "não encontrado"));
        disciplina.setCurso(curso);
        this.discipliaRepository.save(disciplina);
        return ResponseEntity.ok(curso);
    }



}
