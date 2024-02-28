package com.example.intra2022reseau;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.intra2022reseau.transfer.Conversion;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public List<Conversion> data;
    public MyAdapter(List<Conversion> data){
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Conversion conversion = data.get(position);
        holder.txtDesc.setText(conversion.getDescription()); // Use the getter method to access 'annee'
        holder.txtRepr.setText(conversion.getRepresentation()); // Use the getter method to access 'jourDeLaSemaine'
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Conversion> newData) {
        data.clear(); // Clear existing data
        data.addAll(newData); // Add new data
        notifyDataSetChanged(); // Notify RecyclerView
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDesc;
        TextView txtRepr;
        public ViewHolder(View itemView) {
            super(itemView);
            txtDesc = itemView.findViewById(R.id.txtDescription);
            txtRepr = itemView.findViewById(R.id.txtRepresentation);
        }
    }
}
