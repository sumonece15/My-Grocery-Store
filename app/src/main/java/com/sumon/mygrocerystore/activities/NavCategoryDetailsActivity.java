package com.sumon.mygrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.sumon.mygrocerystore.R;
import com.sumon.mygrocerystore.adapters.NavCategoryDetailsAdapter;
import com.sumon.mygrocerystore.models.NavCategoryDetailsModel;
import com.sumon.mygrocerystore.models.PopularModel;
import com.sumon.mygrocerystore.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryDetailsActivity extends AppCompatActivity {

    RecyclerView nav_cat_details_rec;
    List<NavCategoryDetailsModel> navCategoryDetailsModelList;
    NavCategoryDetailsAdapter navCategoryDetailsAdapter;
    FirebaseFirestore db;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category_details);

        progressBar = findViewById(R.id.navCat_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        //nav_cat_details_rec.setVisibility(View.GONE);

        nav_cat_details_rec = findViewById(R.id.nav_cat_details_rec);
        nav_cat_details_rec.setLayoutManager(new LinearLayoutManager(this));

        navCategoryDetailsModelList = new ArrayList<>();
        navCategoryDetailsAdapter = new NavCategoryDetailsAdapter(this, navCategoryDetailsModelList);
        nav_cat_details_rec.setAdapter(navCategoryDetailsAdapter);

        db = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");



        if (type != null && type.equalsIgnoreCase("drink")){

            db.collection("NavCategory Details").whereEqualTo("type","drink").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        NavCategoryDetailsModel navCategoryDetailsModel = documentSnapshot.toObject(NavCategoryDetailsModel.class);
                        navCategoryDetailsModelList.add(navCategoryDetailsModel);
                        navCategoryDetailsAdapter.notifyDataSetChanged();

                        Log.d("Sumon", String.valueOf(navCategoryDetailsModel));

                        progressBar.setVisibility(View.GONE);
                        nav_cat_details_rec.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}