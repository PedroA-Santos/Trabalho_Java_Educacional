package br.grupointegrado.Educacional.controller;


import br.grupointegrado.Educacional.dto.ProfessorRequestDTO;
import br.grupointegrado.Educacional.exceptions.ProfessorNotFoundException;
import br.grupointegrado.Educacional.exceptions.ValidationException;
import br.grupointegrado.Educacional.model.Professor;
import br.grupointegrado.Educacional.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;


    @GetMapping
    public List<Professor> findALl(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Professor findById (@PathVariable Integer id){

        return  this.repository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor com o id " + id + "não encontrado"));
    }

    @PostMapping
    public Professor save (@RequestBody @Valid ProfessorRequestDTO dto){
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ValidationException("O nome do Professor é obrigatório.");
        }

        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return this.repository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor update (@PathVariable Integer id,
                             @RequestBody @Valid ProfessorRequestDTO dto){

        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new ProfessorNotFoundException("Professor com id " + id  + "não encontrado"));
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());
        return this.repository.save(professor);

    }


    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Professor professor = this.repository.findById(id).
                orElseThrow(() -> new ProfessorNotFoundException("Professor com o id " + id + "não encontrado"));

        return ResponseEntity.noContent().build();
    }


}
