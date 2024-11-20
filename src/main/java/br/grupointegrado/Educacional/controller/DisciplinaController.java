package br.grupointegrado.Educacional.controller;


import br.grupointegrado.Educacional.dto.DisciplinaRequestDTO;
import br.grupointegrado.Educacional.dto.ProfessorRequestDTO;
import br.grupointegrado.Educacional.exceptions.DisciplinaNotFoundException;
import br.grupointegrado.Educacional.exceptions.ProfessorNotFoundException;
import br.grupointegrado.Educacional.model.Disciplina;
import br.grupointegrado.Educacional.model.Professor;
import br.grupointegrado.Educacional.repository.DisciplinaRepository;
import br.grupointegrado.Educacional.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public List<Disciplina> findAll() {
        return this.repository.findAll();
    }


    @GetMapping("/{id}")
    public Disciplina findById (@PathVariable Integer id){
       return this.repository.findById(id)
                .orElseThrow(() -> new DisciplinaNotFoundException("Disciplina com id " + id + "não encontrada"));


    }

    @PostMapping
    public Disciplina save (@RequestBody @Valid DisciplinaRequestDTO dto){
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        return this.repository.save(disciplina);
    }

    @PutMapping("/{id}")
    public Disciplina update (@PathVariable Integer id,
                              @RequestBody @Valid DisciplinaRequestDTO dto){

        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new DisciplinaNotFoundException("Disciplina com o id " + id + "não encontrada"));
            disciplina.setNome(dto.nome());
            disciplina.setCodigo(dto.codigo());
            return this.repository.save(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new DisciplinaNotFoundException("Disciplina com id " + id + "não encontrada"));
        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/add-professor")
    public ResponseEntity<Disciplina> addProfessor(@PathVariable Integer id,
                                                   @RequestBody @Valid ProfessorRequestDTO professorDTO) {
        // Encontrar a disciplina pelo ID
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new DisciplinaNotFoundException("Disciplina com id " + id + " não encontrada"));

        // Encontrar o professor pelo ID do professor no DTO
        Professor professor = this.professorRepository.findById(professorDTO.id())
                .orElseThrow(() -> new ProfessorNotFoundException("Professor com id " + professorDTO.id() + " não encontrado"));

        // Associar o professor à disciplina
        disciplina.setProfessor(professor);

        // Salvar a disciplina com o professor associado
        this.repository.save(disciplina);

        return ResponseEntity.ok(disciplina);
    }





}