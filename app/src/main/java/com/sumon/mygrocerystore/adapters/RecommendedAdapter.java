package com.sumon.mygrocerystore.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sumon.mygrocerystore.R;
import com.sumon.mygrocerystore.models.RecommendedModel;

import java.util.List;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

    Context context;
    List<RecommendedModel> recommendedModelList;

    public RecommendedAdapter(Context context, List<RecommendedModel> recommendedModelList) {
        this.context = context;
        this.recommendedModelList = recommendedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(recommendedModelList.get(position).getImg_url()).into(holder.recommended_img);
        holder.recommended_name.setText(recommendedModelList.get(position).getName());
        holder.recommended_desc.setText(recommendedModelList.get(position).getDescription());
        holder.recommended_rating.setText(String.valueOf(recommendedModelList.get(position).getPrice()));


    }

    @Override
    public int getItemCount() {
        return recommendedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView recommended_img;
        TextView recommended_name, recommended_desc, recommended_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recommended_img = itemView.findViewById(R.id.recommended_img);
            recommended_name = itemView.findViewById(R.id.recommended_name);
            recommended_desc = itemView.findViewById(R.id.recommended_desc);
            recommended_rating = itemView.findViewById(R.id.recommended_rating);
        }
    }
}
