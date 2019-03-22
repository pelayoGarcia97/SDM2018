package com.example.scar.gym.Vistas.Dietas;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scar.gym.Datos.DiaDieta;
import com.example.scar.gym.Datos.Dieta;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.MainMenuActivity;

import java.util.List;

public class DietaDetalleViewRecyclerView extends RecyclerView.Adapter<DietaDetalleViewRecyclerView.ViewHolder> {

    private List<DiaDieta> mData;
    private LayoutInflater mInflater;
    private DietaDetalleViewRecyclerView.ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public DietaDetalleViewRecyclerView(Context context, List<DiaDieta> data, boolean seMuestranReps) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public DietaDetalleViewRecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recview_dieta1, parent, false);
        return new DietaDetalleViewRecyclerView.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        DiaDieta dia = mData.get(pos);

        holder.txtDia.setText(dia.getNombre());
        holder.moreDesayuno.setText(dia.getDesayuno());
        holder.moreAlmuerzo.setText(dia.getAlmuerzo());
        holder.moreComida.setText(dia.getComida());
        holder.moreMerienda.setText(dia.getMerienda());
        holder.moreCena.setText(dia.getCena());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView moreDesayuno, moreAlmuerzo, moreComida, moreMerienda, moreCena;
        TextView txtDia;

        ViewHolder(View itemView) {
            super(itemView);
            txtDia = itemView.findViewById(R.id.txtDia);
            moreDesayuno = itemView.findViewById(R.id.moreDesayuno);
            moreAlmuerzo = itemView.findViewById(R.id.moreAlmuerzo);
            moreComida = itemView.findViewById(R.id.moreComida);
            moreMerienda = itemView.findViewById(R.id.moreMerienda);
            moreCena = itemView.findViewById(R.id.moreCena);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    // convenience method for getting data at click position
    public DiaDieta getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
