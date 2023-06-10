package com.example.myloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Store_MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;
    TextView title;
    String data1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_menu_layout);

        title=findViewById(R.id.title_store);

        getData();
        setData();

        CardView cardView01=(CardView) findViewById(R.id.cardview1);
        cardView01.setOnClickListener(this);


    }

    private void getData(){
        data1=getIntent().getStringExtra("title");
    }

    private void setData(){
        title.setText(data1);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardview1:
                Intent it1=new Intent(this, MenuDetail_Activity.class);
                startActivity(it1);
                break;
        }

    }
}
