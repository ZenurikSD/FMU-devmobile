package fmu.money;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fmu.money.db.UserTempStorage;
import fmu.money.utils.CalendarUtils;

public class ReceitaViewAdapter extends RecyclerView.Adapter<ReceitaViewAdapter.ViewHolder>{
    UserTempStorage user = UserTempStorage.getInstancia();

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView recNome, recData, recValorBrl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.recNome = itemView.findViewById(R.id.recNome);
            this.recData = itemView.findViewById(R.id.recData);
            this.recValorBrl = itemView.findViewById(R.id.recValorBrl);
        }
    }

    @NonNull
    @Override
    public ReceitaViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View parentView = new View(parent.getContext());
        return new ViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewAdapter.ViewHolder holder, int position) {
        String dataString = CalendarUtils.getDataString(user.getReceitaList().get(position).getData());
        holder.recData.setText(dataString);

        String valorString = String.valueOf(user.getReceitaList().get(position).getValor());
        holder.recValorBrl.setText(valorString);
    }

    @Override
    public int getItemCount() {
        return user.getDespesaList().size();
    }
}

