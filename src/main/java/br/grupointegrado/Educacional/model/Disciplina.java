package br.grupointegrado.Educacional.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String nome;

    @Column(length = 10)
    private String codigo;
    @ManyToOne
    @JoinColumn(name = "professor_id",referencedColumnName = "id")
    @JsonIgnoreProperties("disciplinas")
   private Professor professor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties({"turmas","disciplinas"})//Json para organizar a visualização da resposta JSON
    private Curso curso;

    @OneToMany(mappedBy = "disciplina")
    private List<Notas> notas;

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Notas> getNota() {
        return notas;
    }

    public void setNota(List<Notas> nota) {
        this.notas = nota;
    }
}
