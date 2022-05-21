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
import com.sumon.mygrocerystore.activities.DetailsActivity;
import com.sumon.mygrocerystore.models.ViewAllModel;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {


    Context context;
    List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewAllAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapter.ViewHolder holder, int position) {

        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.viewAll_img);
        holder.viewAll_name.setText(viewAllModelList.get(position).getName());
        holder.viewAll_desc.setText(viewAllModelList.get(position).getDescription());
        holder.viewAll_rating.setText(viewAllModelList.get(position).getRating());
        holder.viewAll_price.setText(viewAllModelList.get(position).getPrice()+"/kg");

        if (viewAllModelList.get(position).getType().equals("egg")){

            holder.viewAll_price.setText(viewAllModelList.get(position).getPrice()+"/dozen");
        }

        if (viewAllModelList.get(position).getType().equals("milk")){

            holder.viewAll_price.setText(viewAllModelList.get(position).getPrice()+"/litre");
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("detail", viewAllModelList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView viewAll_img;
        TextView viewAll_name, viewAll_desc, viewAll_price, viewAll_rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            viewAll_img = itemView.findViewById(R.id.view_all_img);
            viewAll_name = itemView.findViewById(R.id.view_all_name);
            viewAll_desc = itemView.findViewById(R.id.view_all_desc);
            viewAll_price = itemView.findViewById(R.id.view_all_price);
            viewAll_rating = itemView.findViewById(R.id.view_all_rating);
        }
    }
}
