package fmu.money;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

import fmu.money.utils.CalendarUtils;

// Adapter disponibiliza as Views que contém os itens do dataset
public class DespesaRecViewAdapter extends RecyclerView.Adapter<DespesaRecViewAdapter.ViewHolder>{
    ArrayList<Despesa> despesas = new ArrayList<>();
    private Context context;

    //Subclasse que extende a ViewHolder para instanciar os componentes da View
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView category, description, date, valueBrl;
        private MaterialCardView parent;
        private MaterialAlertDialogBuilder dialog;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            valueBrl = itemView.findViewById(R.id.valueBrl);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    //Construtor, passa o contexto da activity onde está a RecyclerView para poder user onClick Listeners
    public DespesaRecViewAdapter(Context context) {
        this.context = context;
    }

    //Métodos implementados do Adapter
    // Instancia um ViewHolder da classe acima, criando uma view com o LayoutInflater e passando-a para o construtor
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    // Manipulação dos atributos e elementos da View (card)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.category.setText( despesas.get(position).getCategoria() );
        holder.description.setText(despesas.get(position).getDescricao());
        holder.date.setText(CalendarUtils.getDataString(despesas.get(position).getData()));  //mds quanto método

        String formatvalue = "R$ " + despesas.get(position).getValor();
        holder.valueBrl.setText(formatvalue);

        holder.dialog = new MaterialAlertDialogBuilder(context)
            .setTitle("Remover card?")
            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    despesas.remove(holder.getAdapterPosition());
                    setDespesas(despesas);
                }
            })
            .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

        AlertDialog dialog = holder.dialog.create();

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return despesas.size();
    }

    public void setDespesas(ArrayList<Despesa> despesas){
        this.despesas = despesas;
        notifyDataSetChanged();
    }
}
