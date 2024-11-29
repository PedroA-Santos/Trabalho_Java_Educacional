package br.grupointegrado.Educacional.controller;

import br.grupointegrado.Educacional.dto.CursoRequestDTO;
import br.grupointegrado.Educacional.dto.DisciplinaRequestDTO;
import br.grupointegrado.Educacional.dto.TurmaRequestDTO;
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


    @GetMapping
    public List<Curso> findAll() {
        return this.repository.findAll();
    }




    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Curso com id " + id + " não encontrado"));

        return ResponseEntity.ok(curso);
    }




    @PostMapping
    public ResponseEntity<Curso>save(@RequestBody @Valid CursoRequestDTO dto) {
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ValidationException("O nome do Curso é obrigatório.");
        }
        Curso curso = new Curso();
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());
        return ResponseEntity.ok(this.repository.save(curso));
    }




    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Integer id, @RequestBody @Valid CursoRequestDTO dto) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Curso com id " + id + " não encontrado"));
        curso.setNome(dto.nome());
        curso.setCodigo(dto.codigo());
        curso.setCarga_horaria(dto.carga_horaria());

        return ResponseEntity.ok(this.repository.save(curso));
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Curso com id " + id + " não encontrado"));
        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }




    //SE A TURMA JÁ ESTIVER CRIADA, PASSAR APENAS O ID NO BODY
    @PostMapping("/{id}/add-turma")
    public ResponseEntity<Curso> addTurma(@PathVariable Integer id, @RequestBody @Valid TurmaRequestDTO turmaDTO) {

        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Curso com id " + id + " não encontrado"));


        Turma turma = this.turmaRepository.findById(turmaDTO.turmaId())
                .orElseThrow(() -> new  ValidationException("Turma com id " + turmaDTO.turmaId() + " não encontrada"));

        // Associar a turma ao curso
        turma.setCurso(curso);


        this.turmaRepository.save(turma);


        return ResponseEntity.ok(curso);
    }




    //SE A DISCIPLINA JÁ ESTIVER CRIADA, PASSAR APENAS O ID NO BODY
    @PostMapping("/{id}/add-disciplina")
    public ResponseEntity<Curso> addDisciplina(@PathVariable Integer id, @RequestBody @Valid DisciplinaRequestDTO dto) {
        // Encontrar o curso pelo id
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Curso com id " + id + " não encontrado"));

        // Encontrar a disciplina pelo id presente no DTO
        Disciplina disciplina = this.discipliaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new  ValidationException("Disciplina com id " + dto.disciplinaId() + " não encontrada"));

        // Associar o curso à disciplina
        disciplina.setCurso(curso);

        // Salvar a disciplina com o curso associado
        this.discipliaRepository.save(disciplina);

        return ResponseEntity.ok(curso);
    }


}
