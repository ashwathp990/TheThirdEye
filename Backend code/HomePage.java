package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import com.google.android.gms.samples.vision.ocrreader.OcrCaptureActivity;

import java.util.Locale;
public class HomePage extends AppCompatActivity implements TextToSpeech.OnInitListener{

    OcrCaptureActivity ocrCaptureActivity;

    Button a,b,c,d,e;int i=0;
    private TextToSpeech tts,mTts;
    private static int SPLASH_SCREEN_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ocrCaptureActivity=new OcrCaptureActivity();
        //Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
        a=(Button)findViewById(R.id.button5);
        b=(Button)findViewById(R.id.button6);
        c=(Button)findViewById(R.id.button7);
        d=(Button)findViewById(R.id.button8);
        e=(Button)findViewById(R.id.button9);


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
        tts = new TextToSpeech(this.getApplicationContext(), listener);
        mTts = new TextToSpeech(HomePage.this, HomePage.this);
        a.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Detect objects around you.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //tts.speak("Service 1", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"AAAAAAAAAAAAAA",Toast.LENGTH_LONG).show();
                return false;
            }
        });
        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Find out an object you are searching for.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"BBBBBBBBBBBBBB",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        c.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Read Text in an object.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"CCCCCCCCCCCCCC",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        d.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Send Email to a recipient", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"DDDDDDDDDDDDDDD",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        e.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Go to Main Page two and explore other features of this app.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"DDDDDDDDDDDDDDD",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        i = 0;
                    }
                };

                if (i == 1) {
                    handler.postDelayed(r, 250);
                } else if (i == 2) {
                    i = 0;

                    tts.speak("You have chosen to detect object around you. Kindly rotate your screen to Landscape mode and turn around.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    Intent intent = new Intent(HomePage.this,YoloObject.class);
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(),"Double Clicked A",Toast.LENGTH_LONG).show();
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        i = 0;
                    }
                };

                if (i == 1) {
                    handler.postDelayed(r, 250);
                } else if (i == 2) {
                    i = 0;
                    tts.speak("You chosen to find an object near you.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    //Toast.makeText(getApplicationContext(),"Double Clicked B",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HomePage.this,SayObject.class);
                    startActivity(intent);
                }
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        i = 0;
                    }
                };

                if (i == 1) {
                    handler.postDelayed(r, 250);
                } else if (i == 2) {
                    i = 0;
                    tts.speak("You have chosen to read text witten in an object. Kindly bring the object near camera and tap on the screen.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    Intent intent = new Intent(HomePage.this,OcrCaptureActivity.class);
                    startActivity(intent);
                    // Toast.makeText(getApplicationContext(),"Double Clicked C",Toast.LENGTH_LONG).show();
                }
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        i = 0;
                    }
                };
                if (i == 1) {
                    handler.postDelayed(r, 250);
                } else if (i == 2) {
                    i = 0;
                    tts.speak("You have chosen to send Email", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    //Toast.makeText(getApplicationContext(),"Double Clicked D",Toast.LENGTH_LONG).show();
                    //tts.speak("Read a Text", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    Intent intent = new Intent(HomePage.this,Emailer.class);
                    startActivity(intent);
                }
            }
        });

        e.setOnClickListener(new View.OnClickListener() {
            int i = 0;
            @Override
            public void onClick(View v) {
                i++;
                Handler handler = new Handler();
                Runnable r = new Runnable() {

                    @Override
                    public void run() {
                        i = 0;
                    }
                };
                if (i == 1) {
                    handler.postDelayed(r, 250);
                } else if (i == 2) {
                    i = 0;
                    //tts.speak("You moved to Main Page Two. Long press on the screen to Know the service available and Double Tap to Use it", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    //Toast.makeText(getApplicationContext(),"Double Clicked D",Toast.LENGTH_LONG).show();
                    //tts.speak("Read a Text", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    Intent intent = new Intent(HomePage.this,Extrafeatures.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void func() {
        mTts.speak("You are now in Main Page One. Long press on the screen to Know the service available and Double Tap to Use it", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
    }

    @Override
    public void onInit(int status) {
        func();
    }
}