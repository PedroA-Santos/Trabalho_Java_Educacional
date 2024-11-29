package br.grupointegrado.Educacional.controller;

import br.grupointegrado.Educacional.dto.*;
import br.grupointegrado.Educacional.exceptions.ValidationException;
import br.grupointegrado.Educacional.model.Aluno;
import br.grupointegrado.Educacional.model.Matricula;
import br.grupointegrado.Educacional.model.Notas;
import br.grupointegrado.Educacional.model.Turma;
import br.grupointegrado.Educacional.repository.AlunoRepository;
import br.grupointegrado.Educacional.repository.NotasRepository;
import br.grupointegrado.Educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/relatorios")
public class RelatoriosController {


    @Autowired
    private AlunoRepository alunoRepository;



    //FUNÇAO PARA TRAZER A NOTA DO ALUNO NAS SUAS RESPECTIVAS MATÉRIAS PELO SEU ID
    @GetMapping("/{aluno_id}/boletim")
    public ResponseEntity<BoletimResponseDTO> getNotas(@PathVariable Integer aluno_id) {
        Aluno aluno = this.alunoRepository.findById(aluno_id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado."));
        List<NotaResponseDTO> notas = new ArrayList<>();
        if (!aluno.getMatriculas().isEmpty()) {
            for (Matricula matricula : aluno.getMatriculas()) {
                for (Notas nota : matricula.getNotas()) {
                    notas.add(
                            new NotaResponseDTO(
                                    nota.getNota(),
                                    nota.getData_lancamento(),
                                    new DisciplinaResponseDTO(
                                            nota.getDisciplina().getNome(),
                                            nota.getDisciplina().getCodigo()
                                    )
                            )
                    );
                }
            }
        }
        return ResponseEntity.ok(new BoletimResponseDTO(notas));
    }

}

