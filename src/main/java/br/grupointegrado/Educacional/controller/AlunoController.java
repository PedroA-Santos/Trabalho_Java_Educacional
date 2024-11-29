package br.grupointegrado.Educacional.controller;

import br.grupointegrado.Educacional.dto.AlunoRequestDTO;
import br.grupointegrado.Educacional.exceptions.ValidationException;
import br.grupointegrado.Educacional.model.Aluno;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.grupointegrado.Educacional.repository.AlunoRepository;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
    @Autowired
    private AlunoRepository repository;


    @GetMapping
    public List<Aluno> findAll (){

        return this.repository.findAll();

    }




    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id){

        Aluno aluno =  this.repository.findById(id)
                .orElseThrow(()-> new  ValidationException("Aluno com ID" + id + "não encontrado"));

        return ResponseEntity.ok(aluno);
    }



    @PostMapping
    public ResponseEntity<Aluno> save (@RequestBody @Valid AlunoRequestDTO dto){
        if (dto.nome() == null || dto.nome().isEmpty()) {
            throw new ValidationException("O nome do aluno é obrigatório.");
        }
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());

         return ResponseEntity.ok(this.repository.save(aluno));
    }




    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update (@PathVariable  Integer id,
                         @RequestBody @Valid AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(()-> new  ValidationException("Aluno não encontrado para atualização"));
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        aluno.setData_nascimento(dto.data_nascimento());
        return ResponseEntity.ok(this.repository.save(aluno));

    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(()-> new  ValidationException("Aluno não encontrado"));
        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }

}
