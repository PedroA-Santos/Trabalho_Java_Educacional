package br.grupointegrado.Educacional.controller;


import br.grupointegrado.Educacional.dto.MatriculaRequestDTO;
import br.grupointegrado.Educacional.exceptions.ValidationException;
import br.grupointegrado.Educacional.model.Aluno;
import br.grupointegrado.Educacional.model.Matricula;
import br.grupointegrado.Educacional.model.Turma;
import br.grupointegrado.Educacional.repository.AlunoRepository;
import br.grupointegrado.Educacional.repository.MatriculaRepository;
import br.grupointegrado.Educacional.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public List<Matricula> findAll(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id){
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(()-> new  ValidationException ("Matricula com o id" + id + "não encontrada"));

        return ResponseEntity.ok(matricula);

    }


    @PostMapping
    public ResponseEntity<Matricula> save(@RequestBody @Valid MatriculaRequestDTO dto){
        if(dto.aluno_id() == null || dto.turma_id() == null){
            throw new ValidationException("Aluno e Turma são obrigatórios para a criação da matrícula");
        }

        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(() -> new ValidationException("Aluno com id " + dto.aluno_id() + " não encontrado"));

        Turma turma = turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new ValidationException("Turma com id " + dto.turma_id() + " não encontrada"));

        // Criando a matrícula
        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setTurma(turma);



        // Salvando a turma e a matrícula
        turmaRepository.save(turma);
        return ResponseEntity.ok(this.repository.save(matricula));

    }



    @PutMapping("/{id}")
    public ResponseEntity<Matricula> update (@PathVariable Integer id,
                             @RequestBody @Valid MatriculaRequestDTO dto){
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Matricula com o id" + id + "não encontrada"));

        Aluno aluno = alunoRepository.findById(dto.aluno_id())
                .orElseThrow(()-> new ValidationException("Aluno com id " + dto.aluno_id() + "não encontrado"));

        Turma turma = turmaRepository.findById(dto.turma_id())
                .orElseThrow(() -> new ValidationException("Turma com o id " + dto.turma_id() + "não encontrada"));

        matricula.setTurma(turma);
        matricula.setAluno(aluno);

        return ResponseEntity.ok(this.repository.save(matricula));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Matricula com id " + id + "não encontrada"));
        this.repository.delete(matricula);

        return ResponseEntity.noContent().build();
    }


}
