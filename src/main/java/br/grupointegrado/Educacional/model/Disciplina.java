package br.grupointegrado.Educacional.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JoinColumn(name = "professor_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"disciplinas","notas","matriculas"})//JSONS IGNORES PARA MELHOR FORMATAÇÃO NO RETORNO DO JSON
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnoreProperties({"turmas", "disciplinas"})//JSONS IGNORES PARA MELHOR FORMATAÇÃO NO RETORNO DO JSON
    private Curso curso;

    @OneToMany(mappedBy = "disciplina")
    @JsonManagedReference
    @JsonIgnoreProperties({"matricula", "turma"}) //JSONS IGNORES PARA MELHOR FORMATAÇÃO NO RETORNO DO JSON
    @JsonIgnore
    private List<Notas> notas;






    //GETTERS E SETTERS


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

    public List<Notas> getNotas() {
        return notas;
    }

    public void setNotas(List<Notas> notas) {
        this.notas = notas;
    }
}
