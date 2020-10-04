package com.google.android.gms.samples.vision.ocrreader;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import com.google.android.gms.samples.vision.ocrreader.OcrCaptureActivity;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class Extrafeatures extends AppCompatActivity implements TextToSpeech.OnInitListener{

    Button a,b,c,d,e;int i=0;
    private TextToSpeech tts,mTts;;
    private static int SPLASH_SCREEN_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrafeatures);
        //Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_LONG).show();
        a=(Button)findViewById(R.id.a);
        b=(Button)findViewById(R.id.b);
        c=(Button)findViewById(R.id.c);
        d=(Button)findViewById(R.id.d);
        e=(Button)findViewById(R.id.e);


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
        //tts.speak("Welcome You to the Third Eye. I am Harry. I will help u throughout the app. Long press on the screen to Know the service and Double Tap to Use it", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
        mTts = new TextToSpeech(Extrafeatures.this, Extrafeatures.this);
        a.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Make Call to a number", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //tts.speak("Service 1", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"AAAAAAAAAAAAAA",Toast.LENGTH_LONG).show();
                return false;
            }
        });
        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Send SMS to a number", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"BBBBBBBBBBBBBB",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        c.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Know the Phone Battery level", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"CCCCCCCCCCCCCC",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        d.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Know the Current Time", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                //Toast.makeText(getApplicationContext(),"DDDDDDDDDDDDDDD",Toast.LENGTH_LONG).show();
                return false;
            }
        });

        e.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak("Goto Main Page One", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
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
                    tts.speak("You chosen to make call to a number.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    Intent intent = new Intent(Extrafeatures.this,call.class);
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
                    tts.speak("You chosen to send SMS to a number.", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    Intent intent = new Intent(Extrafeatures.this,sms.class);
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

                    IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                    Intent batteryStatus = getApplicationContext().registerReceiver(null, ifilter);
                    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                    float batteryPct = level / (float)scale;
                    float p = batteryPct * 100;
                    tts.speak("Your Battery Percentage is " + String.valueOf(Math.round(p)), TextToSpeech.QUEUE_ADD, null, "DEFAULT");
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
                    //Toast.makeText(getApplicationContext(),"Double Clicked D",Toast.LENGTH_LONG).show();
                    //tts.speak("Read a Text", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
                    String dateString = dateFormat.format(new Date()).toString();
                    Format f = new SimpleDateFormat("EEEE");
                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat simpleformat = new SimpleDateFormat("dd MMMM yyyy");
                    String str = f.format(new Date());
                    tts.speak("Today's date is "+simpleformat.format(cal.getTime()), TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    tts.speak("The Day is "+str, TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    tts.speak("and The Current Time is "+dateString, TextToSpeech.QUEUE_ADD, null, "DEFAULT");
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
                    //tts.speak("You moved to Main Page One. Long press on the screen to Know the service available and Double Tap to Use it", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    //Toast.makeText(getApplicationContext(),"Double Clicked D",Toast.LENGTH_LONG).show();
                    //tts.speak("Read a Text", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
                    Intent intent = new Intent(Extrafeatures.this,HomePage.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void func() {
        mTts.speak("You are now in Main Page Two. Long press on the screen to Know the service available and Double Tap to Use it", TextToSpeech.QUEUE_ADD, null, "DEFAULT");
    }

    @Override
    public void onInit(int status) {
        func();
    }

}

