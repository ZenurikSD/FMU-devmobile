package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;

import fmu.money.db.DespesaFakeDAO;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private RelativeLayout parent;
    private Snackbar snackbar;
    private RecyclerView cardsRecView;
    private DespesaRecViewAdapter despesaAdapter;
    private DespesaFakeDAO despesaDAO;

    //OnClickListeners vão aqui
    @Override
    public void onClick(View view){
        int viewId = view.getId();

        if (viewId == R.id.fab){
            despesaDAO.addDespesa(new Despesa("Emergência", 4500, "Conserto do carro", Calendar.getInstance(), 1));
            despesaAdapter.setDespesas(despesaDAO.listDespesas());
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

        cardsRecView = findViewById(R.id.cardsRecView);
        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.setDespesas(despesaDAO.listDespesas());

        cardsRecView.setAdapter(despesaAdapter);
        cardsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        // FAB
        parent = findViewById(R.id.parent);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }
}