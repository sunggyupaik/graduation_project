package kr.co.MarineLife;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PredictPrice extends AppCompatActivity {

    Intent intent;
    ImageView pic;
    TextView text1;

    String year,month,day,name,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predict);

        text1 = (TextView) findViewById(R.id.textView1);
        pic = (ImageView) findViewById(R.id.imageView1);

        intent = getIntent();
        year = getIntent().getStringExtra("year");
        month = getIntent().getStringExtra("month");
        day = getIntent().getStringExtra("day");
        name = getIntent().getStringExtra("name");
        date = day;

        DBHelper3 helper = new DBHelper3(this);
        SQLiteDatabase db = helper.getWritableDatabase();

//        String sql = "select * from predict where ds=" + date;
        String sql = "select * from predict where ds=" + date;
        Cursor c = db.rawQuery(sql, null);
        while (c.moveToNext()) {

            String price = c.getString(c.getColumnIndex("yhat"));
            String name = c.getString(c.getColumnIndex("name"));
            String date = c.getString(c.getColumnIndex("ds"));

            double tmp = Double.parseDouble(price);
            price = String.format("%.0f", tmp);
            text1.setText(date + "일의 " + name + " 가격 = " + price+"원");
            pic.setImageResource(FindPic(name));

//            text1.setText(date + "의 " + name + " 가격 = " + price);
//            text1.setText("2019-08-01의 살오징어 가격 33647원");
//            pic.setImageResource(FindPic("살오징어"));
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
