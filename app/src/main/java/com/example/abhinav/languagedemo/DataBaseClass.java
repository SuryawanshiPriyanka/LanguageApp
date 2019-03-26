package com.example.abhinav.languagedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DataBaseClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "language_details.db";
    public static final int DATABASE_VERSION = 1;

    Context context;
    SQLiteDatabase db;

    private static final String TABLE_LANGUAGE_DETAILS = "details";
    private static final String ID = "_Id";
    private static final String SID = "sid";
    private static final String SENTENCE = "Sentence";

    private static final String TABLE_OREDR_DETAILS = "oredrdetails";
    private static final String _OREDR = "_order";
    private static final String SENTENCEID = "sentenceid";
    private static final String WORD = "word";

    public DataBaseClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String create_sentence_details =
                "create table if not exists "
                        + TABLE_LANGUAGE_DETAILS + " ( " + SID + " integer primary key, "
                        + SENTENCE + " text);";


        String create_order_details =
                "create table if not exists " + TABLE_OREDR_DETAILS + " ( " + ID + " integer primary key autoincrement, "
                        + SID + " integer, "
                        + _OREDR + " text, "
                        + WORD + " text, "
                        + " FOREIGN KEY (" + SID + ") REFERENCES " + TABLE_LANGUAGE_DETAILS + "(" + SID + "));";

        sqLiteDatabase.execSQL(create_sentence_details);
        sqLiteDatabase.execSQL(create_order_details);
    }

    public void createTable() {
        open();
        Toast.makeText(context, "" + db.getPath(), Toast.LENGTH_SHORT).show();
        db.close();
    }

    public void open() throws SQLException {
        db = getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

  /*  public long sentence(String sentence)
    {
        open();

        ContentValues values = new ContentValues();
        values.put(SENTENCE, sentence);

        long l = db.insert(TABLE_LANGUAGE_DETAILS, null, values);
        db.close();
        return l;
    }*/
    public long order(String s_id, String order_id, String word) {
        open();
        ContentValues values1 = new ContentValues();

        values1.put(SID, s_id);
        values1.put(_OREDR, order_id);
        values1.put(WORD, word);

        long l = db.insert(TABLE_OREDR_DETAILS, null, values1);
        db.close();
        return l;
    }

    @Override
    public void onConfigure(SQLiteDatabase db)
    {
        db.setForeignKeyConstraintsEnabled(true);
    }

    public ArrayList<LanguageBean> getSentenceDetails(String sid)
    {
        String name = " ";
        ArrayList<LanguageBean> arrayList = new ArrayList<>();
        arrayList.clear();

        open();
        Cursor c = db.query(TABLE_LANGUAGE_DETAILS, null, SID + "=?",
                new String[]{sid}, null, null, null);

        if (c != null)
        {
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                do {
                    LanguageBean languageBean = new LanguageBean();
                    languageBean.setSentence(c.getString(c.getColumnIndex(SENTENCE)));
                    arrayList.add(languageBean);
                }
                while (c.moveToNext());
            }
        }

        db.close();
        return arrayList;
    }

    public String getOrderDetails()
    {
        String name = "";
        try {
            open();

            Cursor cursor = db.query(TABLE_OREDR_DETAILS, null, null,
                    null, null, null, null);

            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    do {

                        String _order = cursor.getString(cursor.getColumnIndex(_OREDR));
                        String sentenceid = cursor.getString(cursor.getColumnIndex(SENTENCEID));
                        String word = cursor.getString(cursor.getColumnIndex(WORD));

                        System.out.println("OREDR => " + _order + " SENTENCEID => " + sentenceid + " WORD => " + word);

                    } while (cursor.moveToNext());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return name;
    }
    public String getRandom()
    {
        String name = " ";

        open();
        Cursor c = db.query(TABLE_LANGUAGE_DETAILS + " ORDER BY RANDOM() LIMIT 1",
                null, null, null, null, null, null);
/**/z
        if (c != null)
        {
            if (c.getCount() > 0)
            {
                c.moveToFirst();
                do {
                    if(c.moveToFirst())
                        return c.getString(c.getColumnIndex(SENTENCE));
                    else
                        return "nothing";
                }
                while (c.moveToNext());
            }
        }
        db.close();
        return name;
    }
}






