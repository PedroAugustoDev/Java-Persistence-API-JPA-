package model.oneforone;

import javax.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String nome;

    @Column(name = "lastname", length = 40, nullable = false)
    private String sobrenome;

    @OneToOne(mappedBy = "aluno")
    private Contato contato;



    public Aluno(){

    }

    public Aluno(String nome, String sobrenome){
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public String getNomeCompleto(){
        return this.getNome() + " " + this.getSobrenome();
    }

}
