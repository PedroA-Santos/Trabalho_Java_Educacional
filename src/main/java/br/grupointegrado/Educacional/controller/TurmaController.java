package br.grupointegrado.Educacional.controller;

import br.grupointegrado.Educacional.dto.TurmaRequestDTO;
import br.grupointegrado.Educacional.exceptions.ValidationException;
import br.grupointegrado.Educacional.model.Turma;
import br.grupointegrado.Educacional.repository.TurmaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {


    // REPOSITORY DA TURMA
    @Autowired
    private TurmaRepository repository;



    // BUSCAR TODAS AS TURMAS COM UM LIST DAS TURMAS
    @GetMapping
    public List<Turma> findAll() {
        return this.repository.findAll();
    }





    // BUSCAR UMA TURMA PELO SEU ID
    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Turma turma =  this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Turma com o id " + id + " não encontrada"));

         return ResponseEntity.ok(turma);
    }





    // CRIAR UMA NOVA TURMA COM O MÉTODO POST
    @PostMapping
    public ResponseEntity<Turma> save(@RequestBody @Valid TurmaRequestDTO dto) {
        if (dto.ano() == null ){
            throw new ValidationException("O ano do curso é obrigatório.");
        }
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        return ResponseEntity.ok(this.repository.save(turma));
    }




    // ATUALIZAR UMA TURMA PELO SEU ID
    @PutMapping("/{id}")
    public ResponseEntity<Turma>  update(@PathVariable Integer id, @RequestBody @Valid TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Turma com o id " + id + " não encontrada"));
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        return ResponseEntity.ok(this.repository.save(turma));
    }




    // DELETAR UMA TURMA PELO SEU ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Turma com id " + id + " não encontrada"));
        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
