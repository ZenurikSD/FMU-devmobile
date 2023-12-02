package fmu.money.db.interfaces;

import fmu.money.User;

public interface UserDAOInterface {
    /** Adiciona o usuário ao banco, faça apenas uma vez.
     * @param user
     * @return TRUE se sucesso
     */
    boolean createUser(User user);

    /** Retorna o usuário existente no banco
     * @return
     */
    User getUser();

    /** Atualiza o saldo do usuário no banco
     * @param valor
     * @return TRUE se sucesso
     */
    boolean updateUserSaldo(double valor);
}
