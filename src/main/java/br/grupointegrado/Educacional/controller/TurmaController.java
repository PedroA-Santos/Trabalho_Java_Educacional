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

    @Autowired
    private TurmaRepository repository;

    // Buscar todas as turmas
    @GetMapping
    public List<Turma> findAll() {
        return this.repository.findAll();
    }

    // Buscar turma por id
    @GetMapping("/{id}")
    public Turma findById(@PathVariable Integer id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Turma com o id " + id + " não encontrada"));
    }

    // Criar uma nova turma
    @PostMapping
    public Turma save(@RequestBody @Valid TurmaRequestDTO dto) {
        if (dto.ano() == null ){
            throw new ValidationException("O ano do curso é obrigatório.");
        }
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        return this.repository.save(turma);
    }

    // Atualizar uma turma
    @PutMapping("/{id}")
    public Turma update(@PathVariable Integer id, @RequestBody @Valid TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Turma com o id " + id + " não encontrada"));
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());
        return this.repository.save(turma);
    }

    // Deletar uma turma
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new  ValidationException("Turma com id " + id + " não encontrada"));
        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}
