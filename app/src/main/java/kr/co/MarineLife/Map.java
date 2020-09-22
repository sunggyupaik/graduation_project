package kr.co.MarineLife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity
        implements OnMapReadyCallback {

    Intent intent;
    private GoogleMap mMap;
    int count=1;
    String SelectYear;
    String SelectMonth;

    //위판장 겹칠 시 위치조정할 횟수
    int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,i=0,j=0, k=0,l=0,m=0,n=0,o=0,p=0;
    int Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

//       year, month를 받아와서 넘겨주는 intent 부분
        intent = getIntent();
        SelectYear = getIntent().getStringExtra("year");
        SelectMonth = getIntent().getStringExtra("month");


//      database를 불러와 query를 만드는 부분
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

//        Googlemap 사용을 호출하는 부분
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public String CallMonthQuery(String year, String month) {
        String sql="select * from topmap where 위판일자="+Integer.parseInt(year+month);
        return sql;
    }

    public String TotalSum(String year, String month) {
        String sql="select sum(위판수량) as 수량 from topmap where 위판일자="+Integer.parseInt(year+month);
        return sql;
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

    public Double ChangePlace(String place, String v) {
        if(place.equals("포항수산업협동조합")) return Double.parseDouble(v)+(0.26)*(a++);
        if(place.equals("하동군수산업협동조합")) return Double.parseDouble(v)+(0.26)*(b++);
        if(place.equals("후포수산업협동조합")) return Double.parseDouble(v)+(0.26)*(c++);
        if(place.equals("한림수산업협동조합")) return Double.parseDouble(v)+(0.26)*(d++);
        if(place.equals("민물장어양식수산업협동조합")) return Double.parseDouble(v)+(0.26)*(e++);
        if(place.equals("인천수산업협동조합")) return Double.parseDouble(v)+(0.26)*(f++);
        if(place.equals("완도금일수산업협동조합")) return Double.parseDouble(v)+(0.26)*(g++);
        if(place.equals("해남군수산업협동조합")) return Double.parseDouble(v)+(0.26)*(h++);
        if(place.equals("진해수산업협동조합")) return Double.parseDouble(v)+(0.26)*(i++);
        if(place.equals("신안군수산업협동조합")) return Double.parseDouble(v)+(0.26)*(j++);
        if(place.equals("태안남부수산업협동조합")) return Double.parseDouble(v)+(0.26)*(k++);
        if(place.equals("통영수산업협동조합")) return Double.parseDouble(v)+(0.26)*(l++);
        if(place.equals("영광군수산업협동조합")) return Double.parseDouble(v)+(0.26)*(m++);
        if(place.equals("장흥군수산업협동조합")) return Double.parseDouble(v)+(0.26)*(n++);
        if(place.equals("의창수산업협동조합 ")) return Double.parseDouble(v)+(0.26)*(o++);
        if(place.equals("제3.4구잠수기수산업협동조합 ")) return Double.parseDouble(v)+(0.26)*(p++);
        return 0.0;
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
//        text1.setMovementMethod(new ScrollingMovementMethod());

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

//        전체 판매수량을 계산
        Cursor c3 = db.rawQuery("select distinct 산지조합명 from topmap", null);
        while(c3.moveToNext()){
            String place = c3.getString(c3.getColumnIndex("산지조합명")); // 총판매수량
        }


//        전체 판매수량을 계산
        Cursor c2 = db.rawQuery(TotalSum(SelectYear,SelectMonth), null);
        while(c2.moveToNext()){
            int TotalSale = c2.getInt(c2.getColumnIndex("수량")); // 총판매수량
            this.Total = TotalSale;
        }

//        핸드폰 화면에 나타날 지도 모양
        mMap = googleMap;
        LatLng Center = new LatLng(35.50, 127.90);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Center));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(7));

//        해당 year month를 지도에 표시
        String sql = CallMonthQuery(SelectYear,SelectMonth);
        Cursor c = db.rawQuery(sql, null);

        while(c.moveToNext()){
//            int place_col = c.getColumnIndex("산지조합명");
            int sale = c.getInt(c.getColumnIndex("위판수량")); // 위판수량
            String name = c.getString(c.getColumnIndex("수산물표준코드명")); // 수산물표준코드명
            String place = c.getString(c.getColumnIndex("산지조합명")); // 산지조합명
            String v = c.getString(c.getColumnIndex("v")); // v
            String v1= c.getString(c.getColumnIndex("v1")); // v1
            Double percent= c.getDouble(c.getColumnIndex("percent")); // percent

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(ChangePlace(place,v), Double.parseDouble(v1)))
                    .title(name+"("+place+")")
                    .snippet(count+++"등("+sale+ "마리, "+percent+"%)");
            BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(FindPic(name));
            Bitmap b=bitmapdraw.getBitmap();
            Bitmap smallMarker = Bitmap.createScaledBitmap(b, 330, 165, false);
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
            mMap.addMarker(markerOptions);
        }

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 마커 클릭시 호출되는 콜백 메서드
                Toast.makeText(getApplicationContext(),
                        marker.getTitle()
                        , Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
