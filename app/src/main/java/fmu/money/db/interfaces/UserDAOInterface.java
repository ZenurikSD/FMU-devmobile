package fmu.money.db.interfaces;

import fmu.money.User;

public interface UserDAOInterface {
    /** Adiciona o usuário ao banco
     * @param user
     * @return TRUE se sucesso
     */
    boolean createUser(User user);

    /** Retorna o usuário existente no banco
     * @return
     */
    User getUser();

    boolean updateUser(User user);
}
