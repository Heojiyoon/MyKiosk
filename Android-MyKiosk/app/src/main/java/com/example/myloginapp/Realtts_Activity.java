package com.example.myloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Locale;

public class Realtts_Activity extends AppCompatActivity {
    private TextView textResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtts);

        textResult=findViewById(R.id.text_result);

    }
    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        //음성 인식기에 사용되는 음성모델 정보 설정
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        //음성 인식기에 인식되는 언어 설정
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.KOREAN);

        if(intent.resolveActivity(getPackageManager())!=null){
    startActivityForResult(intent,10);
        }
        else{
            Toast.makeText(this,"당신의 장비가 음성을 텍스트로 변경불가합니다.",Toast.LENGTH_SHORT).show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textResult.setText(result.get(0));
                }
                break;
        }
    }
}
