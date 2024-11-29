package br.grupointegrado.Educacional.controller;


import br.grupointegrado.Educacional.dto.ProfessorRequestDTO;
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


    //FUNÇAO PARA TRAZER TODOS OS PROFESSORES COM O LIST
    @GetMapping
    public List<Professor> findALl(){

        return this.repository.findAll();
    }


    //FUNÇÃO PARA TRAZER AS INFORMAÇÕES DO PROFESSOR PELO SEU ID
    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById (@PathVariable Integer id){

        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Professor com o id " + id + "não encontrado"));

        return ResponseEntity.ok(professor);
    }



    //FUNÇÃO POST PARA CRIAR UM NOVO PROFESSOR
    @PostMapping
    public ResponseEntity<Professor>  save (@RequestBody @Valid ProfessorRequestDTO dto){
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ValidationException("O nome do Professor é obrigatório.");
        }

        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());

        return ResponseEntity.ok(this.repository.save(professor));
    }



    //ATUALIZAR AS INFORMAÇÕES DE UM PROFESSOR PELO SEU ID
    @PutMapping("/{id}")
    public ResponseEntity<Professor> update (@PathVariable Integer id,
                             @RequestBody @Valid ProfessorRequestDTO dto){

        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Professor com id " + id  + "não encontrado"));
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTelefone(dto.telefone());
        professor.setEspecialidade(dto.especialidade());
        return ResponseEntity.ok(this.repository.save(professor));


    }


    //FUNÇÃO PARA DELETAR O PROFESSOR PELO SEU ID
    @DeleteMapping("/{id}")

    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Professor professor = this.repository.findById(id).
                orElseThrow(() -> new  ValidationException("Professor com o id " + id + "não encontrado"));
        this.repository.delete(professor);

        return ResponseEntity.noContent().build();
    }


}
