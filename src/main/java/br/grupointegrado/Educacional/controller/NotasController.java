package br.grupointegrado.Educacional.controller;


import br.grupointegrado.Educacional.dto.NotasRequestDTO;
import br.grupointegrado.Educacional.exceptions.ValidationException;
import br.grupointegrado.Educacional.model.Disciplina;
import br.grupointegrado.Educacional.model.Matricula;
import br.grupointegrado.Educacional.model.Notas;
import br.grupointegrado.Educacional.repository.DisciplinaRepository;
import br.grupointegrado.Educacional.repository.MatriculaRepository;
import br.grupointegrado.Educacional.repository.NotasRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
public class NotasController {

    @Autowired
    public NotasRepository repository;

    @Autowired
    public MatriculaRepository matriculaRepository;

    @Autowired
    public DisciplinaRepository disciplinaRepository;


    // FUNÇÃO PARA LISTAR TODAS AS NOTAS
    @GetMapping
    public List<Notas> findAll (){

        return this.repository.findAll();

    }


    //FUNÇÃI PARA MOSTRAR A NOTA PELO SEU ID
    @GetMapping("/{id}")
    public ResponseEntity<Notas> findById (@PathVariable Integer id){
        Notas notas =this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Nota com id" + id + "não encontrada"));

        return ResponseEntity.ok(notas);

    }


    //FUNÇÃO PARA CRIAR UMA NOVA NOTA E ATRIBUI-LA AO ALUNO EM SUA RESPECTIVA MATÉRIA
    // E VALIDAÇÃO SE ALUNO ESTÁ MATRICULADO NO CURSO DA DISCIPLINA
    @PostMapping
    public  ResponseEntity<Notas> save(@RequestBody @Valid NotasRequestDTO dto) {
        if (dto.nota() == null) {
            throw new ValidationException("O valor da nota é obrigatório");
        }
        if (dto.matriculaId() == null || dto.disciplinaId() == null) {
            throw new jakarta.validation.ValidationException("A matrícula e a disciplina são obrigatórias");
        }

        // Recupera a matrícula e a disciplina
        Matricula matricula = this.matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new ValidationException("Matrícula com o id " + dto.matriculaId() + " não encontrada"));

        Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new ValidationException("Disciplina com o id " + dto.disciplinaId() + " não encontrada"));

        // Valida se o curso da disciplina corresponde ao curso da matrícula
        if (!matricula.getTurma().getCurso().getId().equals(disciplina.getCurso().getId())) {
            throw new ValidationException("A disciplina não pertence ao curso em que o aluno está matriculado");
        }

        // Cria a nota e salva
        Notas nota = new Notas();
        nota.setNota(dto.nota());
        nota.setData_lancamento(dto.data_lancamento());
        nota.setMatricula(matricula);
        nota.setDisciplina(disciplina);

        return ResponseEntity.ok(this.repository.save(nota));
    }



    //FUNÇÃO PARA ATUALIZAR A NOTA DO ALUNO
    @PutMapping("/{id}")
        public  ResponseEntity<Notas>  update (@PathVariable Integer id,
                             @RequestBody @Valid NotasRequestDTO dto){
            Notas nota = this.repository.findById(id)
                    .orElseThrow(()-> new ValidationException("Nota com o id" + id +"não encontrada"));

            Matricula matricula = this.matriculaRepository.findById(dto.matriculaId())
                    .orElseThrow(() -> new ValidationException("Matricula com o id " + dto.matriculaId() + " não encontrada"));
            nota.setMatricula(matricula);

            Disciplina disciplina = this.disciplinaRepository.findById(dto.disciplinaId())
                    .orElseThrow(() -> new ValidationException("Disciplina com o id " + dto.disciplinaId() + "não encontrada"));
            nota.setDisciplina(disciplina);

            nota.setNota(dto.nota());
            nota.setData_lancamento(dto.data_lancamento());

        return ResponseEntity.ok(this.repository.save(nota));




        }


        //FUNÇÃO PARA DELETAR A NOTA DO ALUNO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){

        Notas nota = this.repository.findById(id)
                .orElseThrow(() -> new ValidationException("Nota com o id " + id + "não encontrada"));

       return ResponseEntity.noContent().build();

    }

}

