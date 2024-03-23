package org.osuji.exerciseretrofitsimple.adapters;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.osuji.exerciseretrofitsimple.R;
import org.osuji.exerciseretrofitsimple.transfer.ModelGithub;
import org.osuji.exerciseretrofitsimple.transfer.ModelList;

import java.util.List;

public class MyAdapterListGithub  extends RecyclerView.Adapter<MyAdapterListGithub.MyViewHolder> {
    private List<ModelGithub> dataList;

    public MyAdapterListGithub(List<ModelGithub> dataList) {
        this.dataList = dataList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layoutgithub, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterListGithub.MyViewHolder holder, int position) {
        ModelGithub data = dataList.get(position);
        holder.itemtxtNameList.setText(data.name);
        holder.itemtxtDescList.setText(data.description);
        holder.itemtxtUrlList.setText(data.html_url);

        // Set OnClickListener for btnUrl
        holder.itembtnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the URL from the TextView
                String url = holder.itemtxtUrlList.getText().toString();

                // Create an Intent to open the URL in a web browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Start the activity
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemtxtNameList;
        TextView itemtxtDescList;
        TextView itemtxtUrlList;
        Button itembtnUrl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemtxtNameList = itemView.findViewById(R.id.txtNameGit);
            itemtxtDescList = itemView.findViewById(R.id.txtDescriptionGit);
            itemtxtUrlList = itemView.findViewById(R.id.txtUrlGit);
            itembtnUrl = itemView.findViewById(R.id.btnUrl);
        }
    }
}
