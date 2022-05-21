package com.sumon.mygrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sumon.mygrocerystore.R;
import com.sumon.mygrocerystore.adapters.ViewAllAdapter;
import com.sumon.mygrocerystore.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    RecyclerView viewAll_rec;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;

    FirebaseFirestore firestore;
    Toolbar viewAll_toolbar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);


        viewAll_toolbar = findViewById(R.id.viewAll_toolbar);
        setSupportActionBar(viewAll_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.viewAll_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //viewAll_rec.setVisibility(View.GONE);

        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");

        viewAll_rec = findViewById(R.id.view_all_rec);
        viewAll_rec.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this, viewAllModelList);
        viewAll_rec.setAdapter(viewAllAdapter);

        //getting Fruits
        if (type != null && type.equalsIgnoreCase("fruit")){

            firestore.collection("View All Products").whereEqualTo("type","fruit").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        viewAll_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        //getting Vegetables
        if (type != null && type.equalsIgnoreCase("vegetable")){

            firestore.collection("View All Products").whereEqualTo("type","vegetable").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        viewAll_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        //getting Milk
        if (type != null && type.equalsIgnoreCase("milk")){

            firestore.collection("View All Products").whereEqualTo("type","milk").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        viewAll_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        //getting Fish
        if (type != null && type.equalsIgnoreCase("fish")){

            firestore.collection("View All Products").whereEqualTo("type","fish").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        viewAll_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        //getting Eggs
        if (type != null && type.equalsIgnoreCase("egg")){

            firestore.collection("View All Products").whereEqualTo("type","egg").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                        progressBar.setVisibility(View.GONE);
                        viewAll_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }
}