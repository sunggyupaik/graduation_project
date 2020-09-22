package kr.co.MarineLife;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

import kr.co.MarineLife.R;

public class Graph extends AppCompatActivity {
    Intent intent;
    String year;
    String month;
    ImageView imageView_pie;
    String pie_pic;
    String [] shape={"①","②","③","④","⑤","⑥","⑦","⑧","⑨","⑩"};
    int i=0;

    //뷰의 주소값을 담을 참조변수
    ListView list1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph);
        list1 = (ListView)findViewById(R.id.list1);

        intent = getIntent();
        year = getIntent().getStringExtra("year");
        month = getIntent().getStringExtra("month");
        pie_pic = "o"+year+month;
        Log.d("tag","pie"+pie_pic);
        imageView_pie=(ImageView) findViewById(R.id.imageView);
        int resID = getResources().getIdentifier(pie_pic, "drawable","kr.co.MarineLife");

        imageView_pie.setImageResource(resID);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<HashMap<String,Object>> data_list = new ArrayList<HashMap<String,Object>>();

//        정보10개를 배열에 담음
        Cursor c = db.rawQuery("select * from topmap where 위판일자="+Integer.parseInt(year+month), null);
        while(c.moveToNext()){
            int sale = c.getInt(c.getColumnIndex("위판수량")); // 위판수량
            String name = c.getString(c.getColumnIndex("수산물표준코드명")); // 수산물표준코드명
            String percent= c.getString(c.getColumnIndex("percent")); // percent

            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("img",FindPic(name));
            map.put("name",shape[i++]+" "+name);
            map.put("sale",percent+"% ("+sale+"마리)");

            data_list.add(map);
        }

        String [] keys = {"img","name", "sale"};
        int [] ids= {R.id.imageView, R.id.textView, R.id.textView2};

        Log.d("tag","hello1");
        SimpleAdapter adapter = new SimpleAdapter(this, data_list, R.layout.row, keys, ids);
        Log.d("tag","hello2");
        list1.setAdapter(adapter);
        Log.d("tag","hello3");
   }

    public int FindPic(String name){
        int[] PicArray={R.drawable.bam,R.drawable.flower,R.drawable.gal,R.drawable.go,R.drawable.key,
                R.drawable.mul,R.drawable.nak,R.drawable.sal,R.drawable.so,R.drawable.soong};
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
