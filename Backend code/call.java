package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.net.Uri;
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

public class call extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech mTts,tts;
    private Button button;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        button = findViewById(R.id.call);
        phone=findViewById(R.id.editText4);
        mTts = new TextToSpeech(call.this, call.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });

        tts = new TextToSpeech(this.getApplicationContext(), listener);
    }

    public void call(){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phone.getText().toString()));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
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
            mTts.speak("Tell the receiver's phone number", TextToSpeech.QUEUE_ADD, null, "a");

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    phone.setText(result.get(0));
                    button.performClick();
                }
                break;
        }
    }
}
