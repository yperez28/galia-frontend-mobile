package com.pnp.galia_app_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class NewPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_patient);

        ImageView firstIcon = findViewById(R.id.first_step);
        firstIcon.setActivated(true);

        ImageButton closeView = findViewById(R.id.close_patient_view);
        closeView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        Button ready = findViewById(R.id.ready);
        ready.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });

        Button goBack = findViewById(R.id.go_back);
        goBack.setOnClickListener(view -> {
            RelativeLayout popUpSuccessMessage = findViewById(R.id.popup_success);
            RelativeLayout popUpFailureMessage = findViewById(R.id.popup_failure);
            popUpSuccessMessage.setVisibility(View.INVISIBLE);
            popUpFailureMessage.setVisibility(View.INVISIBLE);
        });

        Button cancelSuccess = findViewById(R.id.cancel);
        cancelSuccess.setOnClickListener(view -> {
            RelativeLayout popUpSuccessMessage = findViewById(R.id.popup_success);
            popUpSuccessMessage.setVisibility(View.INVISIBLE);
            RelativeLayout popUpFailureMessage = findViewById(R.id.popup_failure);
            popUpFailureMessage.setVisibility(View.INVISIBLE);
        });

        Button cancelFailure = findViewById(R.id.cancel_failure);
        cancelFailure.setOnClickListener(view -> {
            RelativeLayout popUpFailureMessage = findViewById(R.id.popup_failure);
            popUpFailureMessage.setVisibility(View.INVISIBLE);
        });
    }
}
