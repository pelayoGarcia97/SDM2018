package com.example.scar.gym.Vistas.Ejercicios;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.scar.gym.Datos.Ejercicio;
import com.example.scar.gym.R;
import com.example.scar.gym.Vistas.MainMenuActivity;

import java.io.IOException;
import java.util.List;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class EjerciciosViewRecyclerView extends RecyclerView.Adapter<ViewHolder> {

    private int tipo;   // 0 => Mostrar repeticiones ; 1 => No mostrar repeticiones
    private List<Ejercicio> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public EjerciciosViewRecyclerView(Context context, List<Ejercicio> data, int tipo) {
        this.tipo = tipo;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recview_ejercicios, parent, false);
        return new ViewHolderEjercicio(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nombre = mData.get(position).getNombre();
        String x = mData.get(position).getContadorTxt();
        ((ViewHolderEjercicio)holder).myTextView.setText(nombre);
        if(nombre.length() > 35)
            ((ViewHolderEjercicio)holder).myTextView.setTextSize(12);
        //if(nombre.length() > 35)
        //    ((ViewHolderEjercicio)holder).myTextView.setTextSize(11);
        ((ViewHolderEjercicio)holder).myTextView.setText(nombre);
        if(this.tipo == 0)  // Mostrar repeticiones
            ((ViewHolderEjercicio)holder).numero.setText(x);
        ((ViewHolderEjercicio)holder).playGif(context.getResources(), mData.get(position).getImagen());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolderEjercicio extends ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView numero;

        GifImageView gifImageView;
        GifDrawable gifDrawable;

        ViewHolderEjercicio(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.moreAlmuerzo);
            numero = itemView.findViewById(R.id.txtNumero);
            gifImageView = itemView.findViewById(R.id.splashGif);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null)
                mClickListener.onItemClick(view, getAdapterPosition());
        }

        public void playGif(Resources resources, String imagen) {
            final String finalName = imagen.toLowerCase();
            try {
                String minus = imagen.toLowerCase();
                int id = resources.getIdentifier(finalName, "drawable", MainMenuActivity.PACKAGE_NAME);
                gifDrawable = new GifDrawable(resources, id);
                //gifDrawable = new GifDrawable(resources, R.drawable.ej1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            gifDrawable.setLoopCount(1);
            gifDrawable.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    playGif(context.getResources(), finalName);
                    //gifImageView.setImageDrawable(ContextCompat.getDrawable(activity(), R.drawable.gymicon));
                }
            });
            gifImageView.setImageDrawable(gifDrawable);
        }
    }

    // convenience method for getting data at click position
    public Ejercicio getItem(int id) {
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