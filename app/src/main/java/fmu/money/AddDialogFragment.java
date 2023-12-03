package fmu.money;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class AddDialogFragment extends AppCompatDialogFragment {

    private DespesaDialogListener listener;

    /** Interface que serve de ponte entre o DialogFragment e a MainActivity  */
    public interface DespesaDialogListener{
        void onDespesaDialogPositiveClick(View modalView);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            this.listener = (DespesaDialogListener) context;
        } catch (ClassCastException c){
            throw new ClassCastException(getActivity().toString()
                    + "deve implementar a interface DespesaDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.modal_add , null);

        builder.setView(view)
                .setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDespesaDialogPositiveClick(view);
                    }
                })
                .setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

}
