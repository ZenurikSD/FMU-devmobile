package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fmu.money.db.ReceitaFakeDAO;
import fmu.money.db.UserFakeDAO;
import fmu.money.db.modelos.Receita;
import fmu.money.db.modelos.User;

public class ReceitaListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView receitaRecView;
    private ReceitaViewAdapter receitaAdapter;
    private FloatingActionButton fabReturnMain;
    private ReceitaFakeDAO receitaDAO;
    private ArrayList<Receita> receitaList;
    private UserFakeDAO userDAO;
    private User user;

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent reopenMainActivity;

        if (viewId == R.id.fabReturnMain){
            reopenMainActivity = new Intent(this, MainActivity.class);

            //Coloca a MainActivity no topo se já estiver iniciada (não chama o onCreate() dela repetidamente sem motivo)
            reopenMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivityIfNeeded(reopenMainActivity, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_list);

        receitaDAO = new ReceitaFakeDAO();
        userDAO = new UserFakeDAO();

        receitaAdapter = new ReceitaViewAdapter(this);
        receitaAdapter.updateDataSet(receitaDAO.listReceitas());

        receitaRecView = findViewById(R.id.cardsReceitaRecView);
        receitaRecView.setAdapter(receitaAdapter);
        receitaRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fabReturnMain = findViewById(R.id.fabReturnMain);
        fabReturnMain.setOnClickListener(this);
    }
}