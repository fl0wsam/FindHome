package com.example.myapplication;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<kijijiData> movieList;
    RecyclerViewAdapter(List<kijijiData> movieList){
        this.movieList = movieList;
    }
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter_layout,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, final int position) {
        final kijijiData movie = movieList.get(position);
        holder.title.setText(movie.getAddress1());
        //holder.title.setBackgroundResource(movie.getImage());
    }
    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.carView);
        }
    }
}