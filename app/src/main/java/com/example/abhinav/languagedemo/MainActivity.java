package com.example.abhinav.languagedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    /*New Repo*/
    TextView txtv_textView;
    DataBaseClass db;
    EditText edt_edittext;
    Button btn_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtv_textView=(TextView)findViewById(R.id.txtv_textView);
        edt_edittext=(EditText) findViewById(R.id.edt_edittext);
        btn_button=(Button) findViewById(R.id.btn_button);
        txtv_textView.setOnClickListener(this);
        btn_button.setOnClickListener(this);
        db = new DataBaseClass(MainActivity.this);


    }
    @Override
    public void onClick(View view)
    {
        if (view.getId()==R.id.txtv_textView)
        {
            Intent intent=new Intent(this,NewClassLanguage.class);
            startActivity(intent);
        }

        if (view.getId()==R.id.btn_button)
        {
            long l = db.sentence(edt_edittext.getText().toString().trim().toLowerCase());

            if(l > 0)
            {
                Toast.makeText(this, "Insert Successfully", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Not Inserted", Toast.LENGTH_SHORT).show();
            }
/*
            long l1 = 0 ;
            String str = "";
            String arr[] = str.split(" ");
            if(arr.length > 0)
            {
                for (int i = 0; i < arr.length ; i++)
                {
                    l1 = db.order(" ", String.valueOf(i + 1), arr[i]);
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

            edt_edittext.setText(" ");
        }
    }
}
