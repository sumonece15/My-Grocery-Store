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
import com.sumon.mygrocerystore.models.NavCategoryModel;

import java.util.List;

public class NavCategoryAdapter extends RecyclerView.Adapter<NavCategoryAdapter.ViewHolder> {

    Context context;
    List<NavCategoryModel> navCategoryModelList;

    public NavCategoryAdapter(Context context, List<NavCategoryModel> navCategoryModelList) {
        this.context = context;
        this.navCategoryModelList = navCategoryModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        NavCategoryModel model = navCategoryModelList.get(position);

        Glide.with(context).load(navCategoryModelList.get(position).getImg_url()).into(holder.nav_category_img);
        holder.nav_category_name.setText(navCategoryModelList.get(position).getName());
        holder.nav_category_desc.setText(navCategoryModelList.get(position).getDescription());
        holder.nav_category_discount.setText(navCategoryModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, NavCategoryDetailsActivity.class);
                intent.putExtra("type",model.getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return navCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView nav_category_img;
        TextView nav_category_name, nav_category_desc, nav_category_discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nav_category_img = itemView.findViewById(R.id.nav_category_img);
            nav_category_name = itemView.findViewById(R.id.nav_category_name);
            nav_category_desc = itemView.findViewById(R.id.nav_category_desc);
            nav_category_discount = itemView.findViewById(R.id.nav_category_discount);
        }
    }
}
