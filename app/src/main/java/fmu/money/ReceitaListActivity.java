package fmu.money;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReceitaListActivity extends AppCompatActivity implements View.OnClickListener {

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

        //TO-DO: ReceitaDAO, Adapter
    }
}