package database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Aluno {
    @PrimaryKey
    private int ra;
    private String nome;
    private Date matricula;
    private float mensalidade;

    public Aluno(int ra, String nome, Date matricula, float mensalidade) {
        this.ra = ra;
        this.nome = nome;
        this.matricula = matricula;
        this.mensalidade = mensalidade;
    }

    public int getRa() {
        return ra;
    }

    public void setRa(int ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getMatricula() {
        return matricula;
    }

    public void setMatricula(Date matricula) {
        this.matricula = matricula;
    }

    public float getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(float mensalidade) {
        this.mensalidade = mensalidade;
    }
}
