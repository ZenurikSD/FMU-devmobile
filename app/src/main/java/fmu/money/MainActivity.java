package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    private FloatingActionButton fabAdd, fabInfo, fabReceitas;
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
        user = new User();

        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.updateDataSet(despesaDAO.listDespesas());

        cardsRecView = findViewById(R.id.cardsRecViewLayout);
        despesaAdapter = new DespesaRecViewAdapter(this);
        despesaAdapter.updateDataSet(despesaDAO.listDespesas());

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