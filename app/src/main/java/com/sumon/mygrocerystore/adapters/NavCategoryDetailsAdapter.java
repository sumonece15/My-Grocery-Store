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
import com.sumon.mygrocerystore.activities.NavCategoryDetailsActivity;
import com.sumon.mygrocerystore.models.NavCategoryDetailsModel;

import java.util.List;

public class NavCategoryDetailsAdapter extends RecyclerView.Adapter<NavCategoryDetailsAdapter.ViewHolder> {

    Context context;
    List<NavCategoryDetailsModel> navCategoryDetailsModelList;

    public NavCategoryDetailsAdapter(Context context, List<NavCategoryDetailsModel> navCategoryDetailsModelList) {
        this.context = context;
        this.navCategoryDetailsModelList = navCategoryDetailsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_details_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(navCategoryDetailsModelList.get(position).getImg_url()).into(holder.nav_cat_details_img);
        holder.nav_cat_details_name.setText(navCategoryDetailsModelList.get(position).getName());
        holder.nav_cat_details_price.setText(navCategoryDetailsModelList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NavCategoryDetailsActivity.class);
                intent.putExtra("type", navCategoryDetailsModelList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return navCategoryDetailsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView nav_cat_details_img;
        TextView nav_cat_details_name, nav_cat_details_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nav_cat_details_img = itemView.findViewById(R.id.nav_cat_details_img);
            nav_cat_details_name = itemView.findViewById(R.id.nav_cat_details_name);
            nav_cat_details_price = itemView.findViewById(R.id.nav_cat_details_price);
        }
    }
}
