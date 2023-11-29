package fmu.money.db;

import java.util.ArrayList;

import fmu.money.Receita;
import fmu.money.Despesa;

/** Classe Singleton que define um único usuário para todo o app.
 * <code>receitaList</code> e <code>despesaList</code> são temporários, substitua pela implementação do banco mais tarde
 */
public class UserTempStorage{
    private String nome;
    private double saldo;
    private ArrayList<Receita> receitaList;
    private ArrayList<Despesa> despesaList;
    private static UserTempStorage instancia;

    /** Retorna a instância do usuário, definido 0 como saldo
     * @return Instância única e global do usuário
     */
    public static synchronized UserTempStorage getInstancia(){
        if (instancia == null){
            instancia = new UserTempStorage();
        }

        return instancia;
    }

    private UserTempStorage(){
        this.nome = "Zenurik";
        this.saldo = 0;
        this.receitaList = new ArrayList<>();
        this.despesaList = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }
    /** Atualiza o saldo do usuário com um certo valor
     * @param incremento
     */
    public void updateSaldo(double incremento){
        this.saldo += incremento;
    }

    public ArrayList<Receita> getReceitaList() {
        return receitaList;
    }
    public ArrayList<Despesa> getDespesaList() {
        return despesaList;
    }
}
