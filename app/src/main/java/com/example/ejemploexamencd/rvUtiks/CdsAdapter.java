package com.example.ejemploexamencd.rvUtiks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejemploexamencd.R;
import com.example.ejemploexamencd.data.Cd;

import java.util.ArrayList;

public class CdsAdapter extends RecyclerView.Adapter<CdsAdapter.CdVH>
        implements View.OnClickListener{

    ArrayList<Cd> lista;
    View.OnClickListener listener;


    public  CdsAdapter(ArrayList<Cd> lista){
        this.lista = lista;
    }
    @NonNull
    @Override
    public CdVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cd, parent, false);
        v.setOnClickListener(listener);
        return new CdVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CdVH holder, int position) {
        holder.bindCd(lista.get(position));


    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    @Override
    public void onClick(View v) {
        if(listener !=null){
            listener.onClick(v);
        }

    }

    public class CdVH extends RecyclerView.ViewHolder {
        TextView tvTitulo;
        TextView TvAutor;

        public CdVH(@NonNull View i) {
            super(i);
            tvTitulo = i.findViewById(R.id.TvTituli);
            TvAutor = i.findViewById(R.id.TvAutor);
        }

        public void bindCd(Cd cd) {
            tvTitulo.setText(cd.getTitle());
            TvAutor.setText(cd.getArtist());
        }
    }
    public  void setListener(View.OnClickListener listener){
        this.listener = listener;

    }
}
