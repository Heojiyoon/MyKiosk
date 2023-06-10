package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myloginapp.R;

public class MainpageActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[];
    int images[]={
            R.drawable.ediya,
            R.drawable.macdonald,
            R.drawable.starbucks,
            R.drawable.ediya,
            R.drawable.ediya,
            R.drawable.ediya,
            R.drawable.ediya,
            R.drawable.ediya,
            R.drawable.ediya,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        recyclerView=findViewById(R.id.recyclerView);

        s1=getResources().getStringArray(R.array.store_name);
        s2=getResources().getStringArray(R.array.store_description);

        Store_Adapter store_adapter=new Store_Adapter(this,s1,s2,images);
        recyclerView.setAdapter(store_adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}