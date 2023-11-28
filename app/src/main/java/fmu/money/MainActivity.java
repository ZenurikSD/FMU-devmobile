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

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RelativeLayout parent;
    private Snackbar snackbar;
    private RecyclerView cardsRecView;
    private ArrayList<Despesa> despesas;
    private DespesaRecViewAdapter despesaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Cards
         * Instancia uma lista de itens e o Adapter que eu criei
         * Passa a lista para o adapter e define ele na RecyclerView dessa atividade
         * Define um gerenciador de layout linear para a RecyclerView usar
         */
        despesas = new ArrayList<>();
        despesas.add(new Despesa(1, 250, "Mercado", "26/11/2023", 1));
        despesas.add(new Despesa(2, 780, "Aluguel", "26/11/2023", 1));
        despesas.add(new Despesa(3, 1250, "Viagem à Buenos Aires", "26/11/2023", 1));

        cardsRecView = findViewById(R.id.cardsRecView);
        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.setDespesas(despesas);

        cardsRecView.setAdapter(despesaAdapter);
        cardsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        // FAB
        parent = findViewById(R.id.parent);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //apenas um teste...
                despesas.add(new Despesa(4, 4500, "Conserto do carro", "26/11/2023", 1));
                despesaAdapter.setDespesas(despesas);

                /*
                Snackbar.make(parent, "Floating action button clicked", BaseTransientBottomBar.LENGTH_INDEFINITE)
                        .setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Snackbar dismissed", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                 */
            }
        });
    }
}