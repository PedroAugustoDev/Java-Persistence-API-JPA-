package model.oneforone;

import javax.persistence.*;

@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, length = 80)
    private String cidade;

    @Column( length = 15, nullable = false)
    private String telefone;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Aluno aluno;

    public Contato(){

    }

    public Contato(String cidade, String telefone, Aluno aluno) {
        this.cidade = cidade;
        this.telefone = telefone;
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

}
