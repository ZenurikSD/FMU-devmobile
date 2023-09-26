package org.fmu.money;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity2 extends AppCompatActivity {

    private EditText edit_valor;
    private TextView view_valor, view_gorjeta, view_total, view_pct;
    private SeekBar seekbar;
    private Double valor, pct;
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private NumberFormat percentFormat = NumberFormat.getPercentInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edit_valor   = findViewById(R.id.editTextValor);
        seekbar      = findViewById(R.id.seekBar);
        view_valor   = findViewById(R.id.textViewValor);
        view_pct     = findViewById(R.id.textViewPct);
        view_gorjeta = findViewById(R.id.textViewGorjeta);
        view_total   = findViewById(R.id.textViewTotal);

        //Evento "onChange" para o campo de valor
        edit_valor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int val_int;

                try {
                    val_int = Integer.parseInt(edit_valor.getText().toString());

                } catch (NumberFormatException nfe) {
                    val_int = 0;
                }

                valor = val_int / 100.0;
                updateValues();
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    private void updateValues(){
        view_valor.setText(currencyFormat.format(valor));
    }
}