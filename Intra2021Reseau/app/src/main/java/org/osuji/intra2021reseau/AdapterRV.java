package org.osuji.intra2021reseau;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.osuji.intra2021reseau.transfer.Fete;
import org.w3c.dom.Text;

import java.util.List;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder> {
    public List<Fete> data;
    public AdapterRV(List<Fete> data) {
        this.data = data;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fete fete = data.get(position);
        holder.txtYear.setText(fete.getAnnee()); // Use the getter method to access 'annee'
        holder.txtJour.setText(fete.getJourDeLaSemaine()); // Use the getter method to access 'jourDeLaSemaine'
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Fete> newData) {
        data.clear(); // Clear existing data
        data.addAll(newData); // Add new data
        notifyDataSetChanged(); // Notify RecyclerView
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtYear;
        TextView txtJour;

        public ViewHolder(View itemView) {
            super(itemView);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtJour = itemView.findViewById(R.id.txtJour);
        }
    }
}
