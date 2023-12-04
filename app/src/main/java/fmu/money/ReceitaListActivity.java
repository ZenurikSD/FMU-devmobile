package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import fmu.money.db.modelos.Receita;
import fmu.money.db.ReceitaFakeDAO;
import fmu.money.db.UserFakeDAO;

public class ReceitaListActivity extends AppCompatActivity implements View.OnClickListener, RemoveDialogListener, AddReceitaDialogFragment.ReceitaDialogListener {
    private TextView txtSomaReceitas;
    private FloatingActionButton fabReturnMain, fabAddReceita;
    private RecyclerView cardsRecView;
    private ReceitaViewAdapter receitaAdapter;
    private ReceitaFakeDAO receitaDAO;
    private UserFakeDAO userDAO;

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent reopenMainActivity;
        Receita receita;

        if (viewId == R.id.fabAddReceita){
            AddReceitaDialogFragment addModal = new AddReceitaDialogFragment();
            addModal.show(getSupportFragmentManager(), "addReceita");

        } else if (viewId == R.id.fabReturnMain){
            reopenMainActivity = new Intent(this, MainActivity.class);

            //Coloca a MainActivity no topo se já estiver iniciada (não chama o onCreate() dela repetidamente sem motivo)
            reopenMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(reopenMainActivity, 0);
        }
    }

    //⚠️ Reserve para criar instâncias e associar dados à variáveis/objetos, não faça operações de UI aqui
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_list);

        // DAOs ====================================================================================
        receitaDAO = new ReceitaFakeDAO();
        userDAO = new UserFakeDAO();

        // Componentes simples =====================================================================
        txtSomaReceitas = findViewById(R.id.txtSomaReceitas);

        // Cards RecyclerView ======================================================================
        cardsRecView = findViewById(R.id.cardsRecView);
        receitaAdapter = new ReceitaViewAdapter(this);

        cardsRecView.setAdapter(receitaAdapter);
        cardsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Bottom FABs onClicks ====================================================================
        fabReturnMain = findViewById(R.id.fabReturnMain);
        fabReturnMain.setOnClickListener(this);

        fabAddReceita = findViewById(R.id.fabAddReceita);
        fabAddReceita.setOnClickListener(this);
    }

    //Após onCreate, operações que atualizam a UI
    @Override
    protected void onStart() {
        super.onStart();

        String nvvalor = "R$ " + receitaDAO.getTotal();
        txtSomaReceitas.setText(nvvalor);

        receitaAdapter.updateDataSet(receitaDAO.listReceitas());
    }

    @Override
    public void onDialogPositiveClick(int indice) {
        Receita r = receitaDAO.getReceita(indice);

        userDAO.updateUserSaldo( - r.getValor());
        receitaDAO.removeReceita(indice);
        receitaAdapter.updateDataSet(receitaDAO.listReceitas());

        txtSomaReceitas.setText("R$ " + receitaDAO.getTotal());
    }

    @Override
    public void onReceitaDialogPositiveClick(Receita receita) {
        userDAO.updateUserSaldo(receita.getValor());
        receitaDAO.addReceita(receita);
        receitaAdapter.updateDataSet(receitaDAO.listReceitas());

        txtSomaReceitas.setText("R$ " + receitaDAO.getTotal());
    }
}