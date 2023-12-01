package fmu.money.db;

import fmu.money.User;
import fmu.money.db.interfaces.UserDAOInterface;

public class UserFakeDAO implements UserDAOInterface {
    TempStorage storage = TempStorage.getInstancia();

    @Override
    public boolean createUser(User user) {
        storage.setUser(user);
        return true;
    }

    @Override
    public User getUser() {
        return storage.getUser();
    }

    @Override
    public boolean updateUser(User user) {
        storage.setUser(user);
        return true;
    }
}
