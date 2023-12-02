package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import fmu.money.db.DespesaFakeDAO;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fabAdd, fabInfo, fabReceitas;
    private ConstraintLayout parent;
    private Snackbar snackbar;
    private RecyclerView cardsRecView;
    private DespesaRecViewAdapter despesaAdapter;
    private DespesaFakeDAO despesaDAO;

    //OnClickListeners vão aqui
    @Override
    public void onClick(View view){
        int viewId = view.getId();

        if (viewId == R.id.fabAdd){
            AddDialogFragment addModal = new AddDialogFragment();
            addModal.show(getSupportFragmentManager(), "add");

        } else if (viewId == R.id.fabInfo){
            Intent in = new Intent(this, InfoActivity.class);
            startActivity(in);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Cards
         * Adiciona uma lista mock de itens ao "banco"
         * Instancia o adapter
         * Retorna a lista para o adapter e define ele na RecyclerView dessa atividade
         * Define um gerenciador de layout linear para a RecyclerView usar
         * Temporário, tem que mover pra implementação no banco
         */
        despesaDAO = new DespesaFakeDAO();

        despesaDAO.addDespesa(new Despesa("Alimentação", 250, "Mercado Carrefour", Calendar.getInstance(), 1));
        despesaDAO.addDespesa(new Despesa("Moradia", 780, "Aluguel", Calendar.getInstance(), 1));
        despesaDAO.addDespesa(new Despesa("Lazer", 1250, "Viagem à Buenos Aires", Calendar.getInstance(), 1));

        cardsRecView = findViewById(R.id.cardsRecViewLayout);
        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.setDespesas(despesaDAO.listDespesas());

        cardsRecView = findViewById(R.id.cardsRecViewLayout);
        cardsRecView.setAdapter(despesaAdapter);
        cardsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        // FAB
        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(this);

        fabInfo = findViewById(R.id.fabInfo);
        fabInfo.setOnClickListener(this);
    }
}