package fmu.money;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ReceitaListActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        /*
        if (viewId == something){

        } else if (viewId == other){

        }
        */
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_list);

        //TO-DO: ReceitaDAO, Adapter
    }
}