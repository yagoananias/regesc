package br.com.yagoananias.regesc.orm;

import javax.persistence.*;

@Entity
@Table(name = "disciplinas")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String nome;
    private Integer semestre;

    @Deprecated
    public Disciplina() {
    }

    public Disciplina(String nome, Integer semestre) {
        this.nome = nome;
        this.semestre = semestre;
    }

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = true)
    private Professor professor;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
}
