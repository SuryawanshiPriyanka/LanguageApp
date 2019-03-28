package com.example.abhinav.languagedemo;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class NewClassLanguage extends AppCompatActivity implements View.OnClickListener {
    private TextToSpeech textToSpeech;
    TextView txt_textview;
    Button btn_check;
    ImageView imgv_mic;
    EditText edt_edit1;

    RecyclerView rec_recyclerView1, rec_recyclerView;
    DataBaseClass db;
    ArrayList<LanguageBean> arrayList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_class_language);

        getIds();
        initTextToSpeech();

        /*  arrayList = db.getSentenceDetails("3");

        if(arrayList.size() > 0)
        {
            for (int i = 0 ; i < arrayList.size() ; i++)
            {
                txt_textview.setText(arrayList.get(i).getSentence() + "\n");
            }
        }
        db.getOrderDetails();*/

        String str = db.getRandom();
        txt_textview.setText(str);

        if (arrayList.size() > 0) {
            rec_recyclerView1 = (RecyclerView) findViewById(R.id.rec_recyclerView1);
            rec_recyclerView = (RecyclerView) findViewById(R.id.rec_recyclerView);

            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
            rec_recyclerView.setLayoutManager(staggeredGridLayoutManager);

            customAdapter = new CustomAdapter(arrayList, NewClassLanguage.this);
            rec_recyclerView1.setAdapter(customAdapter);
            rec_recyclerView.setAdapter(customAdapter);
        }
    }

    private void initTextToSpeech() {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int ttsLang = textToSpeech.setLanguage(Locale.US);

                    if (ttsLang == TextToSpeech.LANG_MISSING_DATA
                            || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "The Language is not supported!");
                    } else {
                        Log.i("TTS", "Language Supported.");
                    }
                    Log.i("TTS", "Initialization success.");
                } else {
                    Toast.makeText(getApplicationContext(), "TTS Initialization failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getIds()
    {
        db = new DataBaseClass(NewClassLanguage.this);
        arrayList = new ArrayList<>();
        txt_textview = (TextView) findViewById(R.id.txt_textview);
        btn_check = (Button) findViewById(R.id.btn_check);
        btn_check.setOnClickListener(this);
        imgv_mic = (ImageView) findViewById(R.id.imgv_mic);
        edt_edit1 = (EditText) findViewById(R.id.edt_edit1);
        imgv_mic.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String data = txt_textview.getText().toString();
                Log.i("TTS", "button clicked: " + data);
                int speechStatus = textToSpeech.speak(data, TextToSpeech.QUEUE_FLUSH, null);

                if (speechStatus == TextToSpeech.ERROR) {
                    Log.e("TTS", "Error in converting Text to Speech!");
                }
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (textToSpeech != null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_check) {
            //long l = db.sentence("I Love To Eat");

            /*if(l > 0)
            {
                Toast.makeText(this, "Insert Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
            }*/
            /*long l1 = 0 ;
            String str = "My Name Is Priyanka";
            String arr[] = str.split(" ");
            if(arr.length > 0)
            {
                for (int i = 0; i < arr.length ; i++)
                {

                    l1 = db.order("3", String.valueOf(i + 1), arr[i]);
                }
            }
            if(l1 > 0)
            {
                Toast.makeText(this, "Insert Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
            }*/

           /* arrayList = db.getSentenceDetails(" ");
            if(arrayList.size() > 0)
            {
                for (int i = 0; i < arrayList.size(); i++)
                {
                    txt_textview.setText(arrayList.get(i).getSentence() + "\n");
                }
            }*/

            String originalString = "";
            String str = "";


            int len = 0;
            originalString = txt_textview.getText().toString().trim().toLowerCase();
            len = originalString.split(" ").length;
            str = edt_edit1.getText().toString().trim().toLowerCase();
            String arr[] = str.split(" ");
            if (arr.length > 0)
            {
                int count = 0;

                for (int i = 0; i < arr.length; i++) {
                    if (originalString.contains(arr[i])) {
                        count++;
                    }
                }
                if (count == len) {
                    Toast.makeText(this, "Sentence Is Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Sentence Is Not Correct", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
