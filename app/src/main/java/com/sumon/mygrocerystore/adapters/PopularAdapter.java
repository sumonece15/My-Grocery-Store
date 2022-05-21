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
import com.sumon.mygrocerystore.models.PopularModel;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapter(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(popularModelList.get(position).getImg_url()).into(holder.popular_img);
        holder.popular_name.setText(popularModelList.get(position).getName());
        holder.popular_desc.setText(popularModelList.get(position).getDescription());
        holder.popular_rating.setText(popularModelList.get(position).getRating());
        holder.popular_discount.setText(popularModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type", popularModelList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popular_img;
        TextView popular_name, popular_desc, popular_rating, popular_discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popular_img = itemView.findViewById(R.id.popular_img);
            popular_name = itemView.findViewById(R.id.popular_name);
            popular_desc = itemView.findViewById(R.id.popular_desc);
            popular_rating = itemView.findViewById(R.id.popular_rating);
            popular_discount = itemView.findViewById(R.id.popular_discount);

        }
    }
}
