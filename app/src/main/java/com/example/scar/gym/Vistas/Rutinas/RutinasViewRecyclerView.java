package com.example.scar.gym.Vistas.Rutinas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scar.gym.Datos.Rutina;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.MainMenuActivity;

import java.util.List;

public class RutinasViewRecyclerView extends RecyclerView.Adapter<RutinasViewRecyclerView.ViewHolder> {

    private final boolean seMuestranReps;
    private List<Rutina> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public RutinasViewRecyclerView(Context context, List<Rutina> data, boolean seMuestranReps) {
        this.seMuestranReps = seMuestranReps;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recview_rutinas, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nombre = mData.get(position).getNombre();
        holder.myTextView.setText(nombre);
        int id = context.getResources().getIdentifier( mData.get(position).getImagen(), "drawable",
                                                                    MainMenuActivity.PACKAGE_NAME);
        holder.imageView.setImageResource(id);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myTextView;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.moreAlmuerzo);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }

    // convenience method for getting data at click position
    public Rutina getItem(int id) {
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