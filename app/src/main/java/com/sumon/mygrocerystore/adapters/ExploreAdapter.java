package com.sumon.mygrocerystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sumon.mygrocerystore.R;
import com.sumon.mygrocerystore.activities.ViewAllActivity;
import com.sumon.mygrocerystore.models.ExploreModel;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ViewHolder> {

    Context context;
    List<ExploreModel> exploreModelList;

    public ExploreAdapter(Context context, List<ExploreModel> exploreModelList) {
        this.context = context;
        this.exploreModelList = exploreModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(exploreModelList.get(position).getImg_url()).into(holder.explore_cat_img);
        holder.explore_cat_name.setText(exploreModelList.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type", exploreModelList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return exploreModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView explore_cat_img;
        TextView explore_cat_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            explore_cat_img = itemView.findViewById(R.id.explore_cat_img);
            explore_cat_name = itemView.findViewById(R.id.explore_cat_name);
        }
    }
}
