package com.if4a.unsurkimia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.if4a.unsurkimia.model.ModelUnsur;
import com.if4a.unsurkimia.R;

import java.util.List;

public class AdapterUnsur extends RecyclerView.Adapter<AdapterUnsur.VHUnsur> {
    private Context ctx;
    private List<ModelUnsur> listunsur;

    public AdapterUnsur(Context ctx, List<ModelUnsur> listunsur) {
        this.ctx = ctx;
        this.listunsur = listunsur;
    }

    @NonNull
    @Override
    public VHUnsur onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_kimia, parent, false);
        return new VHUnsur(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHUnsur holder, int position) {
        ModelUnsur MU = listunsur.get(position);
        holder.id.setText(MU.getId());
        holder.SimbolAtom.setText(MU.getSimbolAtom());
        holder.NamaAtom.setText(MU.getNamaAtom());
        holder.MassaAtom.setText(MU.getMassaAtom());
        holder.NomorAtom.setText(MU.getNomorAtom());
        holder.Keterangan.setText(MU.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return listunsur.size();
    }

    public class VHUnsur extends RecyclerView.ViewHolder {
        TextView id, SimbolAtom, NamaAtom, MassaAtom, NomorAtom, Keterangan;
        public VHUnsur(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.tv_id);
            SimbolAtom = itemView.findViewById(R.id.tv_SimbolAtom);
            NamaAtom = itemView.findViewById(R.id.tv_NamaAtom);
            MassaAtom = itemView.findViewById(R.id.tv_MassaAtom);
            NomorAtom = itemView.findViewById(R.id.tv_NomorAtom);
            Keterangan = itemView.findViewById(R.id.tv_Keterangan);
        }
    }
}
