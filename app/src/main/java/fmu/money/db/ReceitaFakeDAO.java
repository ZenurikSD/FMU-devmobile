package fmu.money.db;

import java.util.ArrayList;

import fmu.money.Receita;
import fmu.money.db.interfaces.ReceitaDAOInterface;

/** Implementação de teste, substitua pela conexão ao banco
 * Utiliza um singleton de armazenamento temporário para simular as interações com o banco
 */
public class ReceitaFakeDAO implements ReceitaDAOInterface{
    private UserTempStorage user = UserTempStorage.getInstancia();

    @Override
    public ArrayList<Receita> listReceitas() {
        return user.getReceitaList();
    }

    @Override
    public Receita getReceita(int id) {
        return user.getReceitaList().get(id);
    }

    @Override
    public boolean addReceita(Receita receita) {
        return user.getReceitaList().add(receita);
    }

    /** Não faz nada por enquanto. Não use. */
    @Override
    public boolean updateReceita(Receita receita) {
        return false;
    }

    @Override
    public boolean removeReceita(int id) {
        Receita temp = user.getReceitaList().get(id);
        return user.getReceitaList().remove(temp);
    }
}
