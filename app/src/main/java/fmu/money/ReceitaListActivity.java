package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fmu.money.db.ReceitaFakeDAO;
import fmu.money.db.UserFakeDAO;

public class ReceitaListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView receitaRecView;
    private ReceitaViewAdapter receitaAdapter;
    private FloatingActionButton fabAddReceita;
    private ReceitaFakeDAO receitaDAO;
    private ArrayList<Receita> receitaList;
    private UserFakeDAO userDAO;
    private User user;

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent reopenMainActivity;
        Receita receita;

        if (viewId == R.id.fabAddReceita){
            // Versão teste, substitua pela implementação do banco
            // Adiciona uma receita ao storage, atualiza o Adapter com a lista nova, incrementa o saldo total

            AddReceitaDialogFragment addModal = new AddReceitaDialogFragment();
            addModal.show(getSupportFragmentManager(), "addReceita");
            
            receita = new Receita(1000);

            receitaDAO.addReceita(receita);
            receitaAdapter.updateDataSet(receitaDAO.listReceitas());

            userDAO.updateUserSaldo(receita.getValor());

            String nvvalor = "R$ " + receitaDAO.getTotal();
            txtSomaReceitas.setText(nvvalor);

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

        receitaDAO = new ReceitaFakeDAO();
        userDAO = new UserFakeDAO();
        user = new User();

        receitaAdapter = new ReceitaViewAdapter(this);
        receitaAdapter.updateDataSet(receitaDAO.listReceitas());

        receitaRecView = findViewById(R.id.cardsReceitaRecView);
        receitaRecView.setAdapter(receitaAdapter);
        receitaRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fabAddReceita = findViewById(R.id.fabAddReceita);
        fabAddReceita.setOnClickListener(this);
    }
}