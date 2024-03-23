package org.osuji.exerciseretrofitsimple.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.osuji.exerciseretrofitsimple.R;
import org.osuji.exerciseretrofitsimple.transfer.ModelList;

import java.util.List;

public class MyAdapterListObject extends RecyclerView.Adapter<MyAdapterListObject.MyViewHolder> {

    private List<ModelList> dataList;

    public MyAdapterListObject(List<ModelList> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layoutobject, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ModelList data = dataList.get(position);
        holder.itemtxtAList.setText(String.valueOf(data.a));
        holder.itemtxtBList.setText(data.b);
        holder.itemtxtCList.setText(data.c.toString());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemtxtAList;
        TextView itemtxtBList;
        TextView itemtxtCList;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemtxtAList = itemView.findViewById(R.id.txtAList);
            itemtxtBList = itemView.findViewById(R.id.txtBList);
            itemtxtCList = itemView.findViewById(R.id.txtCList);
        }
    }
}

