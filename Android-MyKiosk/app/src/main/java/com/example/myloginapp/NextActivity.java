package com.example.myloginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myloginapp.PlaceOrderActivity;
import com.example.myloginapp.R;

import java.util.ArrayList;
import java.util.Locale;

public class NextActivity extends AppCompatActivity {
    TextView welcomeTextView;
    //TextToSpeech tts;
    //MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        welcomeTextView = findViewById(R.id.welcomeTextView);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        String welcomeMessage = "안녕하세요, " + username + "님!";
        welcomeTextView.setText(welcomeMessage);

        Button frequentlyMenuButton = findViewById(R.id.button2);
        frequentlyMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this, PlaceOrderActivity.class);
                startActivity(intent);
            }
        });

        Button orderButton = findViewById(R.id.button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this,MainpageActivity.class);
                startActivity(intent);
            }
        });
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NextActivity.this, Realtts_Activity.class);
                startActivity(intent);
            }
        });
/*
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 5);
            toast("We need your permission");
        }

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        final TextView txt = new TextView(this);
        txt.setText("\n");
        txt.setTextSize(18);
        layout.addView(txt);

        Button input = new Button(this);
        input.setText("voice recognition");
        input.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                inputVoice(txt);
            }
        });

        layout.addView(input);
        ScrollView scroll = new ScrollView(this);
        scroll.addView(layout);
        //setContentView(scroll);
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            public void onInit(int status) {
                tts.setLanguage(Locale.ENGLISH);
            }
        });

        mediaPlayer = MediaPlayer.create(this, R.raw.bibidibabidibuu);

        Button voiceButton = findViewById(R.id.voiceButton);
        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputVoice(txt);
            }
        });



    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void inputVoice(final TextView txt) {
        try {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
            final SpeechRecognizer stt = SpeechRecognizer.createSpeechRecognizer(this);
            stt.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle params) {
                    toast("starting recognition...");
                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {

                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {
                    toast("Voice input is over");
                }

                @Override
                public void onError(int error) {
                    toast("error occurred: " + error);
                    stt.destroy();
                }

                @Override
                public void onResults(Bundle results) {
                    ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    txt.append("[Me] " + result.get(0) + "\n");
                    replyAnswer(result.get(0), txt);
                    stt.destroy();
                }

                @Override
                public void onPartialResults(Bundle bundle) {

                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });

            stt.startListening(intent);
        } catch (Exception e) {
            toast(e.toString());
        }
    }

    private void replyAnswer(String input, TextView txt) {
        try {
            if (input.equals("hello")) {
                txt.append("[Alexa] Who are you?\n");
                tts.speak("Who are you?", TextToSpeech.QUEUE_FLUSH, null);
            } else if (input.equals("who are you")) {
                txt.append("[Alexa] I am Alexa.\n");
                tts.speak("I am Alexa.", TextToSpeech.QUEUE_FLUSH, null);
            } else if (input.equals("I am Sam")) {
                txt.append("[Alexa] Nice to meet you, Sam.\n");
                tts.speak("Nice to meet you, Sam.", TextToSpeech.QUEUE_FLUSH, null);
            } else if (input.equals("goodbye")) {
                finish();
            } else if (input.equals("Play the music")) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                    toast("Music is playing");
                }
            } else if (input.equals("Stop the music")) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    toast("Music is stopped");
                }
            } else if (input.equals("Go to the next page")) {
                Intent intent = new Intent(NextActivity.this, PlaceOrderActivity.class);
                startActivity(intent);
            } else if (input.equals("Google")) {
                // Open Google website or perform related action
            } else {
                txt.append("[Alexa] Sorry, I cannot understand.\n");
                tts.speak("Sorry, I cannot understand.", TextToSpeech.QUEUE_FLUSH, null);
            }
        } catch (Exception e) {
            toast(e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }*/
}
}
