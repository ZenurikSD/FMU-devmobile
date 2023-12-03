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

import fmu.money.db.ReceitaFakeDAO;
import fmu.money.db.modelos.Despesa;
import fmu.money.db.DespesaFakeDAO;
import fmu.money.db.UserFakeDAO;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnDialogPositiveCallback {
    private RecyclerView cardsRecView;
    private DespesaRecViewAdapter despesaAdapter;
    private ReceitaFakeDAO receitaDAO;
    private DespesaFakeDAO despesaDAO;
    private UserFakeDAO userDAO;
    private FloatingActionButton fabAdd, fabInfo, fabReceitas;
    private TextView txtValorSaldo, txtValorDespesas, txtValorReceitas;
    private Intent intent;
    private static final String[] CATEGORIAS = {
            "Alimentação",
            "Assinatura",
            "Beleza",
            "Educação",
            "Emergência",
            "Investimento",
            "Lazer",
            "Moradia",
            "Outro",
            "Saúde",
            "Transporte"
    };

    //OnClickListeners vão aqui
    @Override
    public void onClick(View view){
        int viewId = view.getId();
        Intent intent;

        if (viewId == R.id.fabAdd){
            AddDialogFragment addModal = new AddDialogFragment();
            addModal.show(getSupportFragmentManager(), "add");

            // Versão teste, substitua pela implementação do banco
            // Adiciona uma despesa ao storage, atualiza o Adapter com a lista nova, decrementa o saldo total

            Despesa despesa = new Despesa(CATEGORIAS[6], 2500,"Viagem à Buenos Aires", Calendar.getInstance(), 1);
            despesaDAO.addDespesa(despesa);
            despesaAdapter.updateDataSet(despesaDAO.listDespesas());

            //Decrementa o saldo
            userDAO.updateUserSaldo( - despesa.getValor());
            String nvSaldo = "R$ " + userDAO.getUserSaldo();
            txtValorSaldo.setText(nvSaldo);

            //Atualiza total de despesas e receitas
            txtValorDespesas.setText("R$ " + despesaDAO.getTotal());
            txtValorReceitas.setText("R$ " + receitaDAO.getTotal());

        } else if (viewId == R.id.fabInfo){
            intent = new Intent(this, InfoActivity.class);
            startActivity(intent);

        } else if (viewId == R.id.fabReceitas){
            intent = new Intent(this, ReceitaListActivity.class);
            startActivity(intent);
        }

    }

    //⚠️ Reserve para criar instâncias e associar dados à variáveis/objetos, não faça operações de UI aqui
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DAOs =====================================================================================
        despesaDAO = new DespesaFakeDAO();
        receitaDAO = new ReceitaFakeDAO();
        userDAO = new UserFakeDAO();
        userDAO.createUser("Zenurik", 0);

        //Componentes simples ======================================================================
        txtValorDespesas = findViewById(R.id.txtValorDespesas);
        txtValorSaldo = findViewById(R.id.txtValorSaldo);
        txtValorReceitas = findViewById(R.id.txtValorReceitas);


        /* Cards RecyclerView ======================================================================
         * Adiciona uma lista mock de itens ao "banco"
         * Instancia o adapter
         * Retorna a lista para o adapter e define ele na RecyclerView dessa atividade
         * Define um gerenciador de layout linear para a RecyclerView usar
         * Temporário, tem que mover pra implementação no banco
         */
        cardsRecView = findViewById(R.id.cardsRecViewLayout);
        despesaAdapter = new DespesaRecViewAdapter(this);

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

    //Após onCreate, operações que atualizam a UI
    @Override
    protected void onStart() {
        super.onStart();
        txtValorDespesas.setText("R$ " + despesaDAO.getTotal());
        txtValorSaldo.setText("R$ " + userDAO.getUserSaldo());
        txtValorReceitas.setText("R$ " + receitaDAO.getTotal());

        despesaAdapter.updateDataSet(despesaDAO.listDespesas());
    }

    // Implementação do método da interface OnDialogPositiveCallback que é chamado dentro do Adapter
    // Usado para alterar dados nessa Activity quando um evento ocorre no Adapter
    @Override
    public void onDialogPositiveListener(int indice) {
        Despesa d = despesaDAO.getDespesa(indice);

        userDAO.updateUserSaldo(d.getValor());
        txtValorSaldo.setText("R$ " + userDAO.getUserSaldo());

        despesaDAO.removeDespesa(indice);
        despesaAdapter.updateDataSet(despesaDAO.listDespesas());
    }
}