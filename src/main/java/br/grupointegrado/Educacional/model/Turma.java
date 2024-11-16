package br.grupointegrado.Educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer ano;
    @Column
    private Integer semestre;
    @ManyToOne
    @JoinColumn(name ="curso_id" , referencedColumnName = "id")
    @JsonIgnoreProperties({"disciplinas","turmas","curso"})//Json para organizar a visualização da resposta JSON
    private Curso curso;



    public void setId(Integer id) {
        this.id = id;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAno() {
        return ano;
    }

    public Integer getSemestre() {
        return semestre;
    }
    public Curso getCurso() {
        return curso;
    }


}
