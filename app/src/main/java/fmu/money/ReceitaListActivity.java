package fmu.money;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fmu.money.db.UserTempStorage;

public class ReceitaListActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView receitaRecView;
    private ReceitaViewAdapter receitaAdapter;
    private FloatingActionButton fabAddReceita;
    private UserTempStorage user;

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.fabAddReceita){
            user.getReceitaList().add(new Receita(99999.99));
            receitaAdapter.updateDataSet(user.getReceitaList());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_list);

        user = UserTempStorage.getInstancia();
        user.getReceitaList().add(new Receita(2500.00));
        user.getReceitaList().add(new Receita(1250.10));
        user.getReceitaList().add(new Receita(30999.95));

        receitaAdapter = new ReceitaViewAdapter(this);
        receitaAdapter.updateDataSet(user.getReceitaList());

        receitaRecView = findViewById(R.id.cardsReceitaRecView);
        receitaRecView.setAdapter(receitaAdapter);
        receitaRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        fabAddReceita = findViewById(R.id.fabAddReceita);
        fabAddReceita.setOnClickListener(this);


    }
}