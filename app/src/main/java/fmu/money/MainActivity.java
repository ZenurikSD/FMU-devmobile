package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import fmu.money.db.DespesaFakeDAO;
import fmu.money.db.ReceitaFakeDAO;
import fmu.money.db.UserFakeDAO;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnReceitaList;
    private FloatingActionButton fab;
    private RelativeLayout parent;
    private Snackbar snackbar;
    private TextView txtSaldo;
    private RecyclerView cardsRecView;
    private DespesaRecViewAdapter despesaAdapter;
    private DespesaFakeDAO despesaDAO;
    private ReceitaFakeDAO receitaDAO;
    private UserFakeDAO userDAO;
    private User user;

    private Intent intent;

    //OnClickListeners vão aqui
    @Override
    public void onClick(View view){
        int viewId = view.getId();

        if (viewId == R.id.fab){
            //TODO: Dialog com opção de escolher entre receita ou despesa
            //Por enquanto adiciona de uma vez para testes
            despesaDAO.addDespesa(new Despesa("Alimentação", 250, "Mercado Carrefour", Calendar.getInstance(), 1));
            despesaDAO.addDespesa(new Despesa("Moradia", 780, "Aluguel", Calendar.getInstance(), 1));
            despesaDAO.addDespesa(new Despesa("Lazer", 1250, "Viagem à Buenos Aires", Calendar.getInstance(), 1));

            receitaDAO.addReceita(new Receita(2500.00));

            despesaAdapter.updateDataSet(despesaDAO.listDespesas());


            // nem sei mais o que to fazendo
            for (Receita r : receitaDAO.listReceitas()){
                user.updateSaldo(r.getValor());
            }
            for (Despesa d : despesaDAO.listDespesas()){
                user.updateSaldo(d.getValor());
            }

            userDAO.updateUser(user);

            String concat = "R$ " + userDAO.getUser().getSaldo();
            txtSaldo.setText(concat);

        } else if (viewId == R.id.btnReceitaList){
            intent = new Intent(this, ReceitaListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSaldo = findViewById(R.id.txtSaldo);

        /* Cards
         * Adiciona uma lista mock de itens ao "banco"
         * Instancia o adapter
         * Retorna a lista para o adapter e define ele na RecyclerView dessa atividade
         * Define um gerenciador de layout linear para a RecyclerView usar
         * Temporário, tem que mover pra implementação no banco
         */
        despesaDAO = new DespesaFakeDAO();
        receitaDAO = new ReceitaFakeDAO();
        userDAO = new UserFakeDAO();
        user = new User("Alisson");

        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.updateDataSet(despesaDAO.listDespesas());

        cardsRecView = findViewById(R.id.cardsRecView);
        cardsRecView.setAdapter(despesaAdapter);
        cardsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        // FAB
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        //ReceitaActivity
        btnReceitaList = findViewById(R.id.btnReceitaList);
        btnReceitaList.setOnClickListener(this);

    }
}