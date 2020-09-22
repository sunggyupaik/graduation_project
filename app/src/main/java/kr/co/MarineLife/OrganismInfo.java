package kr.co.MarineLife;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrganismInfo extends AppCompatActivity {

    Intent intent;
    ImageView pic;
    TextView text1;
    TextView text2;
    TextView text4;
    TextView text6;
    TextView text8;
    TextView text10;

    int resID;
    String real_name;
    int index;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organisminfo);

        intent = getIntent();
        resID = getIntent().getIntExtra("image",1);
        pic=(ImageView)findViewById(R.id.imageView1);
        pic.setImageResource(resID);

        real_name = getIntent().getStringExtra("name");
        text1=(TextView)findViewById(R.id.TextView1); //이름
        text2=(TextView)findViewById(R.id.TextView2); //영어이름
        text4=(TextView)findViewById(R.id.TextView4); //분포
        text6=(TextView)findViewById(R.id.TextView6); //형태
        text8=(TextView)findViewById(R.id.TextView8); //체색
        text10=(TextView)findViewById(R.id.TextView10); //먹이

        index = getIntent().getIntExtra("index",1);

        DBHelper2 helper = new DBHelper2(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor c = db.rawQuery("select * from info", null);
        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex("이름"));
            String En_name = c.getString(c.getColumnIndex("영어이름"));
            String bun = c.getString(c.getColumnIndex("분포"));
            String hyung = c.getString(c.getColumnIndex("형태"));
            String che = c.getString(c.getColumnIndex("체색"));
            String muk = c.getString(c.getColumnIndex("먹이"));

            Log.d("tag","형태"+hyung);
            Log.d("tag","먹이"+muk);
            text1.setText(name);
            text2.setText(En_name);
            text4.setText(bun);
            text6.setText(hyung);
            text8.setText(che);
            text10.setText(muk);
            if(name.equals(real_name)) break;
        }
    }

    public int FindPic(String name){
        int[] PicArray={R.drawable.bam2,R.drawable.flower2,R.drawable.gal2,R.drawable.go2,R.drawable.key2,
                R.drawable.mul2,R.drawable.nak2,R.drawable.sal2,R.drawable.so2,R.drawable.soong2};
        if(name.equals("뱀장어")) return PicArray[0];
        if(name.equals("꽃게")) return PicArray[1];
        if(name.equals("갈치류")) return PicArray[2];
        if(name.equals("고등어")) return PicArray[3];
        if(name.equals("키조개")) return PicArray[4];
        if(name.equals("멸치")) return PicArray[5];
        if(name.equals("낙지")) return PicArray[6];
        if(name.equals("살오징어")) return PicArray[7];
        if(name.equals("소라")) return PicArray[8];
        if(name.equals("숭어")) return PicArray[9];
        return 0;
    }
}
