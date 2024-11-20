package br.grupointegrado.Educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(length = 20)
    private String codigo;

    @Column
    private Integer carga_horaria;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"curso"}) // Evita a serialização de informações desnecessárias
    private List<Turma> turmas;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"curso","notas"}) // Evita a serialização de informações desnecessárias
    private List<Disciplina> disciplinas;

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public Integer getId() {
        return id;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public String getNome() {
        return nome;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getCarga_horaria() {
        return carga_horaria;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCarga_horaria(Integer carga_horaria) {
        this.carga_horaria = carga_horaria;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
