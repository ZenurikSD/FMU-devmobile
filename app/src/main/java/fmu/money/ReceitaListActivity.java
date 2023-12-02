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

        /*
        if (viewId == R.id.){

        }
         */
    }

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