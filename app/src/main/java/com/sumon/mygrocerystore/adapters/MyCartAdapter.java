package com.sumon.mygrocerystore.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sumon.mygrocerystore.R;
import com.sumon.mygrocerystore.models.MyCartModel;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> myCartModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyCartAdapter(Context context, List<MyCartModel> myCartModelList) {
        this.context = context;
        this.myCartModelList = myCartModelList;
        firestore =FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MyCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartAdapter.ViewHolder holder, int position) {

        holder.cartName.setText(myCartModelList.get(position).getProductName());
        holder.cartPrice.setText(myCartModelList.get(position).getProductPrice());
        holder.cartDate.setText(myCartModelList.get(position).getCurrentDate());
        holder.cartTime.setText(myCartModelList.get(position).getCurrentTime());
        holder.cartQuantity.setText(myCartModelList.get(position).getTotalQuantity());
        holder.cartTotalPrice.setText(String.valueOf(myCartModelList.get(position).getTotalPrice()));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("Add To Cart")
                        .document(myCartModelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    myCartModelList.remove(myCartModelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

    }

    @Override
    public int getItemCount() {
        return myCartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cartName, cartPrice, cartDate, cartTime, cartQuantity, cartTotalPrice;
        ImageView deleteItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartName = itemView.findViewById(R.id.my_cart_productName);
            cartPrice = itemView.findViewById(R.id.my_cart_productPrice);
            cartDate = itemView.findViewById(R.id.my_cart_currentDate);
            cartTime = itemView.findViewById(R.id.my_cart_currentTime);
            cartQuantity = itemView.findViewById(R.id.my_cart_totalQuantity);
            cartTotalPrice = itemView.findViewById(R.id.my_cart_totalPrice);
            deleteItem = itemView.findViewById(R.id.my_cart_delete);
        }
    }
}
