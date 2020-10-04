package com.google.android.gms.samples.vision.ocrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.icu.util.TimeUnit;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

public class Emailer extends AppCompatActivity implements TextToSpeech.OnInitListener{


    private EditText email, subject, message;
    private Button button;
    public int a=0;
    public String j;
    private TextToSpeech mTts,tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailer);

        email = findViewById(R.id.email);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        button = findViewById(R.id.btn);
        mTts = new TextToSpeech(Emailer.this, Emailer.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senEmail();
            }
        });

        tts = new TextToSpeech(this.getApplicationContext(), listener);
    }

    private void senEmail() {
        String mEmail = email.getText().toString();
        String mSubject = subject.getText().toString();
        String mMessage = message.getText().toString();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mEmail, mSubject, mMessage);
        javaMailAPI.execute();
        tts.speak("Email sent successfully", TextToSpeech.QUEUE_ADD, null, "b");

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent intent=new Intent(Emailer.this,HomePage.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onPause() {
        if(mTts != null){
            mTts.stop();
            mTts.shutdown();
        }
        super.onPause();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                @Override
                public void onDone(String utteranceId) {
                    //Log.d(TAG, "Done:  " + utteranceId);
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                    startActivityForResult(intent, 10);
                }

                @Override
                public void onError(String utteranceId) {
                    //Log.e(TAG, "Error:  " + utteranceId);
                }
                @Override
                public void onStart(String utteranceId) {
                    //.i(TAG, "Started:  " + utteranceId);
                }
            });
            //mTts.speak("Welcome You to the Third Eye. I am Harry. I will help u throughout the app. Long press on the screen to Know the service and Double Tap to Use it", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
            mTts.speak("Please share us the required details to send Email. Tell the recipient Email address before at sign.", TextToSpeech.QUEUE_ADD, null, "a");

        } else {
            //Log.e(TAG, "Failed");
        }
    }

    TextToSpeech.OnInitListener listener =
            new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(final int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        Log.d("TTS", "Text to speech engine started successfully.");
                        tts.setLanguage(Locale.US);
                    } else {
                        Log.d("TTS", "Error starting the text to speech engine.");
                    }
                }
            };

    public void speak(int x){
        if(x==1){
            tts.speak("Tell the Subject", TextToSpeech.QUEUE_ADD, null, "b");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            startActivityForResult(intent, 10);
        }
        if(x==2){
            tts.speak("Tell the Message", TextToSpeech.QUEUE_ADD, null, "c");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            startActivityForResult(intent, 10);
        }
        if(x==3){
            button.performClick();
        }
    }

    public void setter(int x,String b){
        if(x==0){
            b=b.replace(" ","").toLowerCase();
            b=b+"@gmail.com";
            email.setText(b);
        }
        if(x==1){
            subject.setText(b);
        }
        if(x==2){
            message.setText(b);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    j=result.get(0);
                    setter(a, j);
                    a++;
                    speak(a);
                }
                break;
        }
    }
}