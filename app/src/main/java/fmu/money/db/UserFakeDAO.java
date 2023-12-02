package fmu.money.db;

import fmu.money.User;
import fmu.money.db.interfaces.UserDAOInterface;

public class UserFakeDAO implements UserDAOInterface {
    TempStorage storage = TempStorage.getInstancia();

    @Override
    public boolean createUser(User user) {
        storage.setSavedUser(user);
        return true;
    }

    @Override
    public User getUser() {
        return storage.getSavedUser();
    }

    @Override
    public boolean updateUserSaldo(double valor) {
        storage.getSavedUser().updateSaldo(valor);
        return true;
    }
}
