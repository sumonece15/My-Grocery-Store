package com.sumon.mygrocerystore.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.sumon.mygrocerystore.MainActivity;
import com.sumon.mygrocerystore.R;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null){

            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Please wait you are already logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
    }

    public void login(View view){
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void registration(View view){
        startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
    }
}