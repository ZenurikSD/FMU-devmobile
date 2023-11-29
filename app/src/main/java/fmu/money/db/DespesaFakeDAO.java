package fmu.money.db;

import java.util.ArrayList;

import fmu.money.Despesa;
import fmu.money.db.interfaces.DespesaDAOInterface;

/** Implementação de teste, substitua pela conexão ao banco
 * Utiliza um singleton de armazenamento temporário para simular as interações com o banco
 */
public class DespesaFakeDAO implements DespesaDAOInterface{
    private UserTempStorage user = UserTempStorage.getInstancia();

    @Override
    public ArrayList<Despesa> listDespesas() {
        return user.getDespesaList();
    }

    @Override
    public Despesa getDespesa(int id) {
        return user.getDespesaList().get(id);
    }

    @Override
    public boolean addDespesa(Despesa despesa) {
        return user.getDespesaList().add(despesa);
    }

    /** Não faz nada por enquanto. Não use. */
    @Override
    public boolean updateDespesa(Despesa despesa) {
        return false;
    }

    @Override
    public boolean removeDespesa(int id) {
        Despesa temp = user.getDespesaList().get(id);
        return user.getDespesaList().remove(temp);
    }
}
