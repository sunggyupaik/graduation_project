package kr.co.MarineLife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeSelect extends AppCompatActivity {

    GridView gridView;

    String[] values = {
            "뱀장어", "꽃게", "갈치", "고등어", "키조개", "멸치",
            "낙지", "살오징어", "소라", "숭어"
    } ;

    int[] images = {
            R.drawable.bam, R.drawable.flower, R.drawable.gal, R.drawable.go,
            R.drawable.key, R.drawable.mul, R.drawable.nak, R.drawable.sal,
            R.drawable.so, R.drawable.soong
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typeselect);

        ArrayList<HashMap<String,Object>> data_list = new ArrayList<HashMap<String,Object>>();

        //데이터담는다
        for(int i=0; i<images.length; i++) {
            //데이터를 해시 맵에 담는다.
            HashMap<String,Object> map = new HashMap<String, Object>();
            map.put("flag",images[i]);
            map.put("data",values[i]);
            data_list.add(map);
        }

        gridView = (GridView) findViewById(R.id.gridview);
        GridAdapter gridAdapter = new GridAdapter(this, values, images);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), OrganismInfo.class);
                intent.putExtra("image", images[i]);
                intent.putExtra("name",values[i]);
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });

//        SimpleAdapter adapter = new SimpleAdapter(this, data_list, R.layout. typeselect,keys,ids);
//        gridView.setAdapter(adapter);

    }
}