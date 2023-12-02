package fmu.money.db;

import java.util.ArrayList;

import fmu.money.Receita;
import fmu.money.db.interfaces.ReceitaDAOInterface;

/** Implementação de teste, substitua pela conexão ao banco
 * Utiliza um singleton de armazenamento temporário para simular as interações com o banco
 */
public class ReceitaFakeDAO implements ReceitaDAOInterface{
    private TempStorage storage = TempStorage.getInstancia();

    @Override
    public ArrayList<Receita> listReceitas() {
        return storage.getReceitaList();
    }

    @Override
    public Receita getReceita(int id) {
        return storage.getReceitaList().get(id);
    }

    @Override
    public boolean addReceita(Receita receita) {
        return storage.getReceitaList().add(receita);
    }

    /** Não faz nada por enquanto. Não use. */
    @Override
    public boolean updateReceita(Receita receita) {
        return false;
    }

    @Override
    public boolean removeReceita(int id) {
        Receita temp = storage.getReceitaList().get(id);
        return storage.getReceitaList().remove(temp);
    }
}
