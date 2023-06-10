package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class PlaceOrderActivity extends AppCompatActivity {

    private FrameLayout flLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder);

        flLayout = findViewById(R.id.flLayout);

        // 기존 코드와 통합하여 사용할 수 있도록 수정 가능

        // Example: Inflate and add a view to the FrameLayout dynamically
        View customView = LayoutInflater.from(this).inflate(R.layout.activity_customview, null);
        flLayout.addView(customView);

        // Example: Accessing radio buttons and setting listeners
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton dineInBtn = findViewById(R.id.dineInBtn);
        RadioButton deliveryBtn = findViewById(R.id.deliveryBtn);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if (checkedId == R.id.dineInBtn) {
                    // Dine In option selected
                } else if (checkedId == R.id.deliveryBtn) {
                    // Delivery option selected
                }
            }
        });

        // Example: Perform actions based on button clicks or other events
        // ...

    }
}
