package org.osuji.exerciseretrofitsimple.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.osuji.exerciseretrofitsimple.R;

import java.util.List;

public class MyAdapterListLong extends RecyclerView.Adapter<MyAdapterListLong.MyViewHolder> {

    private List<Long> dataList;

    public MyAdapterListLong(List<Long> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layoutlong, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Long data = dataList.get(position);
        holder.itemtxtLong.setText(String.valueOf(data));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemtxtLong;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemtxtLong = itemView.findViewById(R.id.txtLong);
        }
    }
}
