package fmu.money;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fmu.money.utils.CalendarUtils;

public class ReceitaViewAdapter extends RecyclerView.Adapter<ReceitaViewAdapter.ViewHolder>{
    private ArrayList<Receita> receitas;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView recNome, recData, recValorBrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recNome = itemView.findViewById(R.id.recNome);
            this.recData = itemView.findViewById(R.id.recData);
            this.recValorBrl = itemView.findViewById(R.id.recValorBrl);
        }
    }

    public ReceitaViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ReceitaViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receita_card_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewAdapter.ViewHolder holder, int position) {
        String dataString = CalendarUtils.getDataString(receitas.get(position).getData());
        holder.recData.setText(dataString);

        String valorString = String.valueOf(receitas.get(position).getValor());
        holder.recValorBrl.setText(valorString);

        // Diálogo de remoção
        holder.dialog = new MaterialAlertDialogBuilder(context)
                .setTitle("Remover receita?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Decrementa o saldo e remove a receita
                        int i = holder.getAdapterPosition();

                        onDialogPositiveCallback.onDialogPositiveListener(i);
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
            }).setBackground(new ColorDrawable(Color.parseColor("#d3d3d3")));;

        AlertDialog cardDialog = holder.dialog.create();
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDialog.show();
            }
        });
    };

    @Override
    public int getItemCount() {
        return receitas.size();
    }

    public void updateDataSet(ArrayList<Receita> receitas){
        this.receitas = receitas;
        notifyDataSetChanged();
    }
}

