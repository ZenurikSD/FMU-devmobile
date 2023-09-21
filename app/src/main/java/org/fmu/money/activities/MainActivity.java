package org.fmu.money.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.fmu.money.R;

public class MainActivity extends AppCompatActivity {
    private EditText n1, n2, result;
    private Button confirm_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        n1 = findViewById(R.id.val1);
        n2 = findViewById(R.id.val2);
        result = findViewById(R.id.result);
    }

    public void calculateResult(View view){
        int x = Integer.parseInt(n1.getText().toString());
        int y = Integer.parseInt(n2.getText().toString());

        result.setText(Integer.toString(x + y));
    }

    public void openScreen2(View view){
        Intent intent = new Intent(this, Screen2.class);
        startActivity(intent);
    }
}