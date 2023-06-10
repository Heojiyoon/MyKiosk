package com.example.myloginapp;



import android.app.Activity;

import android.content.ActivityNotFoundException;

import android.content.Intent;

import android.graphics.Color;

import android.os.Bundle;



import android.speech.RecognitionListener;

import android.speech.RecognizerIntent;

import android.speech.SpeechRecognizer;



import android.speech.tts.TextToSpeech;

//import android.support.design.widget.FloatingActionButton;

//import android.support.design.widget.Snackbar;

import android.view.View;

import android.view.Menu;

import android.view.MenuItem;

import android.widget.ImageButton;

import android.widget.TextView;

import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import java.util.Locale;



public class Tts_Activity extends Activity{



    private static final int RESULT_SPEECH = 1; // REQUEST_CODE로 쓰임



    private Intent i;

    private TextView tv;

    private ImageButton bt;

    private ImageButton user_bt;



    private SpeechRecognizer mRecognizer;



    TextToSpeech tts;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tts);



        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)

                        .show();

            }

        });



        /* 레이아웃의 컴포넌트를 가져옵니다.*/

        tv = (TextView) findViewById(R.id.textVeiw);

        bt = (ImageButton) findViewById(R.id.button);

        user_bt = (ImageButton) findViewById(R.id.user_button);





        user_bt.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if (v.getId() == R.id.user_button) {

                    ;

                }

            }

        });



        /* 버튼에 대한 클릭 리스너 등록 부분*/

        bt.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v) {

                if(v.getId() == R.id.button) {

                    /* Intent 부분*/

                    i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); // Intent 생성

                    i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName()); // 호출한 패키지

                    i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR"); // 인식할 언어를 설정한다.

                    i.putExtra(RecognizerIntent.EXTRA_PROMPT, "말해주세요"); // 유저에게 보여줄 문자



                    Toast.makeText(Tts_Activity.this,"start speak",Toast.LENGTH_SHORT).show();



                    try {

                        startActivityForResult(i, RESULT_SPEECH);

                    }catch(ActivityNotFoundException e) {

                        Toast.makeText(getApplicationContext(),"Speech To Text를 지원하지 않습니다.",Toast.LENGTH_SHORT).show();

                        e.getStackTrace();

                    }



                }

            }

        });

    }




    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && (requestCode == RESULT_SPEECH)) {
            ArrayList<String> sstResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String result_sst = sstResult.get(0);

            tv.setText("" + result_sst);
            Toast.makeText(Tts_Activity.this, result_sst, Toast.LENGTH_SHORT).show();

            // TTS로 음성 출력
            tts.speak(result_sst, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }



   /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            // Settings 메뉴 항목이 선택되었을 때의 동작을 수행합니다.
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}

