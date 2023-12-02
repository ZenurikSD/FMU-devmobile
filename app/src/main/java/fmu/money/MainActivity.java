package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

import fmu.money.db.DespesaFakeDAO;
import fmu.money.db.modelos.*;
import fmu.money.db.ReceitaFakeDAO;
import fmu.money.db.UserFakeDAO;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView cardsRecView;
    private DespesaRecViewAdapter despesaAdapter;
    private ReceitaFakeDAO receitaDAO;
    private DespesaFakeDAO despesaDAO;
    private UserFakeDAO userDAO;
    private FloatingActionButton fabAdd, fabInfo, fabReceitas;
    private TextView txtSaldo;
    private Intent intent;

    //OnClickListeners vão aqui
    @Override
    public void onClick(View view){
        int viewId = view.getId();
        Intent intent;

        if (viewId == R.id.fabAdd){
            AddDialogFragment addModal = new AddDialogFragment();
            addModal.show(getSupportFragmentManager(), "add");

            //Teste de atualização de card e saldo
            Despesa despesa = new Despesa("Lazer", 2500,"Viagem à Recife", Calendar.getInstance(), 1);
            despesaDAO.addDespesa(despesa);
            despesaAdapter.updateDataSet(despesaDAO.listDespesas());

            //Incrementa o saldo ao final da operação
            userDAO.updateUserSaldo(despesa.getValor());
            String novoSaldo = "R$ " + userDAO.getUserSaldo();
            txtSaldo.setText(novoSaldo);

        } else if (viewId == R.id.fabInfo){
            intent = new Intent(this, InfoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.fabReceitas){
            intent = new Intent(this, ReceitaListActivity.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DAOs =====================================================================================
        despesaDAO = new DespesaFakeDAO();
        receitaDAO = new ReceitaFakeDAO();

        userDAO = new UserFakeDAO();
        userDAO.createUser("Zenurik", 0);


        //Atualiza o saldo do usuário
        txtSaldo = findViewById(R.id.txtSaldo);
        txtSaldo.setText("R$ " + userDAO.getUserSaldo());


        /* Cards ===================================================================================
         * Adiciona uma lista mock de itens ao "banco"
         * Instancia o adapter
         * Retorna a lista para o adapter e define ele na RecyclerView dessa atividade
         * Define um gerenciador de layout linear para a RecyclerView usar
         * Temporário, tem que mover pra implementação no banco
         */
        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.updateDataSet(despesaDAO.listDespesas());

        cardsRecView = findViewById(R.id.cardsRecViewLayout);
        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.updateDataSet(despesaDAO.listDespesas());

        cardsRecView = findViewById(R.id.cardsRecViewLayout);
        cardsRecView.setAdapter(despesaAdapter);
        cardsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));


        // Bottom FABs onClicks ====================================================================
        fabInfo = findViewById(R.id.fabInfo);
        fabInfo.setOnClickListener(this);

        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(this);

        fabReceitas = findViewById(R.id.fabReceitas);
        fabReceitas.setOnClickListener(this);

    }
}