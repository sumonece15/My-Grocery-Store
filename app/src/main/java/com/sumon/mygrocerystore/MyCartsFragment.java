package com.sumon.mygrocerystore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sumon.mygrocerystore.activities.PlacedOrderActivity;
import com.sumon.mygrocerystore.adapters.MyCartAdapter;
import com.sumon.mygrocerystore.models.MyCartModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartsFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView myCart_rec;
    MyCartAdapter myCartAdapter;
    List<MyCartModel> myCartModelList;
    TextView overTotalAmount;
    Button buyNow;
    int totalBill;

    ProgressBar progressBar;

    public MyCartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_carts, container, false);

        progressBar = root.findViewById(R.id.myCart_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //myCart_rec.setVisibility(View.GONE);

        buyNow = root.findViewById(R.id.buy_now);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        myCart_rec = root.findViewById(R.id.my_cart_rec);
        myCart_rec.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.totalPrice_tv);

        myCartModelList = new ArrayList<>();
        myCartAdapter = new MyCartAdapter(getActivity(), myCartModelList);
        myCart_rec.setAdapter(myCartAdapter);

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Add To Cart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();

                        MyCartModel myCartModel = documentSnapshot.toObject(MyCartModel.class);
                        myCartModel.setDocumentId(documentId);
                        myCartModelList.add(myCartModel);
                        myCartAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        myCart_rec.setVisibility(View.VISIBLE);
                    }

                    calculateTotalAmount(myCartModelList);
                }
            }
        });


        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PlacedOrderActivity.class);
                intent.putExtra("itemList", (Serializable) myCartModelList);
                startActivity(intent);
            }
        });


        return root;
    }

    private void calculateTotalAmount(List<MyCartModel> myCartModelList) {

        double totalAmount = 0.0;
        for (MyCartModel myCartModel : myCartModelList){

            totalAmount += myCartModel.getTotalPrice();
        }

        overTotalAmount.setText("Total Bill : "+ totalAmount);
    }

}