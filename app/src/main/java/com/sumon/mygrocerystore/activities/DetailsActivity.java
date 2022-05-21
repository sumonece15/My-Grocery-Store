package com.sumon.mygrocerystore.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sumon.mygrocerystore.R;
import com.sumon.mygrocerystore.models.ViewAllModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity {

    TextView quantity;
    int totalQuantity = 1;
    int totalPrice = 0;

    ImageView details_img;
    TextView price_details, desc_details, rating_details;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar details_toolbar;
    ViewAllModel viewAllModel = null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        details_img = findViewById(R.id.details_img);
        price_details = findViewById(R.id.details_price);
        rating_details = findViewById(R.id.details_rating);
        desc_details = findViewById(R.id.details_desc);
        addToCart = findViewById(R.id.add_to_cart);
        details_img = findViewById(R.id.details_img);
        addItem = findViewById(R.id.add_items);
        removeItem = findViewById(R.id.remove_items);
        quantity = findViewById(R.id.quantity);
        addToCart = findViewById(R.id.add_to_cart);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        details_toolbar = findViewById(R.id.details_toolbar);
        setSupportActionBar(details_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ViewAllModel){

            viewAllModel =(ViewAllModel) object;

        }

        if (viewAllModel != null){

            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(details_img);
            rating_details.setText(viewAllModel.getRating());
            desc_details.setText(viewAllModel.getDescription());
            price_details.setText("Price : $"+viewAllModel.getPrice()+"/kg");

            totalPrice = viewAllModel.getPrice() * totalQuantity;


            if (viewAllModel.getType().equals("egg")){

                price_details.setText("Price : $"+viewAllModel.getPrice()+"/dozen");
                totalPrice = viewAllModel.getPrice() * totalQuantity;

            }

            if (viewAllModel.getType().equals("milk")){

                price_details.setText("Price : $"+viewAllModel.getPrice()+"/litre");
                totalPrice = viewAllModel.getPrice() * totalQuantity;
            }
        }


        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalQuantity < 10 ){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }

            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalQuantity > 1 ){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = viewAllModel.getPrice() * totalQuantity;
                }

            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addedToCart();
            }
        });


    }

    private void addedToCart() {

        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM:dd:yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price_details.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("Add To Cart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(DetailsActivity.this, "Added To A Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}