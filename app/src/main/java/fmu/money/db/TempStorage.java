package fmu.money.db;

import java.util.ArrayList;

import fmu.money.Receita;
import fmu.money.Despesa;
import fmu.money.User;

/** Classe Singleton que armazena um usuário com nome e saldo e suas listas de despesas e receitas
 * <code>receitaList</code> e <code>despesaList</code> são temporários, substitua pela implementação do banco mais tarde
 */
public class TempStorage {
    private User user;
    private ArrayList<Receita> receitaList;
    private ArrayList<Despesa> despesaList;
    private static TempStorage instancia;

    /** Retorna a instância do usuário, definido 0 como saldo
     * @return Instância única e global do usuário
     */
    public static synchronized TempStorage getInstancia(){
        if (instancia == null){
            instancia = new TempStorage();
        }

        return instancia;
    }

    private TempStorage(){
        this.user = new User();
        this.receitaList = new ArrayList<>();
        this.despesaList = new ArrayList<>();
    }

    public User getSavedUser() {
        return user;
    }

    public void setSavedUser(User user) {
        this.user = user;
    }

    public ArrayList<Receita> getReceitaList() {
        return receitaList;
    }
    public ArrayList<Despesa> getDespesaList() {
        return despesaList;
    }
}
