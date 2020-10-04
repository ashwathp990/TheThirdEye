package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class SplashScreen extends AppCompatActivity implements TextToSpeech.OnInitListener  {
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mTts = new TextToSpeech(SplashScreen.this, SplashScreen.this);
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
                    Intent intent=new Intent(SplashScreen.this,HomePage.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError(String utteranceId) {
                    //Log.e(TAG, "Error:  " + utteranceId);
                }
                @Override
                public void onStart(String utteranceId) {
                    //Log.i(TAG, "Started:  " + utteranceId);
                }
            });
            //mTts.speak("Welcome You to the Third Eye. I am Harry. I will help u throughout the app. Long press on the screen to Know the service and Double Tap to Use it", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
            mTts.speak("Welcome to Third Eye. I am Hayley Marshall . I will be helping you throughout this app.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");

        } else {
            //Log.e(TAG, "Failed");
        }
    }
}
