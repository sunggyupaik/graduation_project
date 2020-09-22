package kr.co.MarineLife;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PredictSelect extends AppCompatActivity {

    private Button btnDialog1;
    private Button btnDialog2;
    private Button btnDialog3;
    private Button button1;
    private Button button2;
    private TextView text1;
    String year="2019",month="08",day,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.predictselect);

//        final List<String> selectedItems = new ArrayList<String>();

        btnDialog1 = (Button) findViewById(R.id.btnDialog1);
        btnDialog2 = (Button) findViewById(R.id.btnDialog2);
        btnDialog3 = (Button) findViewById(R.id.btnDialog3);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        text1 = (TextView) findViewById(R.id.TextView1);

//        btnDialog1.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//                final String[] items = new String[]{"월","01","02","03","04","05","06","07","08","09","10","11","12"};
//                final int[] selectedIndex = {0};
//                AlertDialog.Builder dialog = new AlertDialog.Builder(PredictSelect.this);
//                dialog  .setTitle("월을 선택하세요")
//                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                selectedIndex[0] = which;
//                                month=items[selectedIndex[0]];
//                            }
//                        })
//                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(PredictSelect.this, items[selectedIndex[0]], Toast.LENGTH_SHORT).show();
//                                btnDialog1.setText(items[selectedIndex[0]]);
//                            } }).create().show();
//            }
//        });

        btnDialog2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final String[] items = new String[]{"일","01","02","03","04","05","06","07","08","09","10","11","12","13","14",
                "15","16","17","18","!9","20","21","22","23","24","25","26","27","28","29","30","31"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(PredictSelect.this);
                dialog  .setTitle("일을 선택하세요")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedIndex[0] = which;
                                day=items[selectedIndex[0]];
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PredictSelect.this, items[selectedIndex[0]], Toast.LENGTH_SHORT).show();
                                btnDialog2.setText(items[selectedIndex[0]]);
                            } }).create().show();
            }
        });

        btnDialog3.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final String[] items = new String[]{"해산물","뱀장어", "꽃게", "갈치", "고등어", "키조개", "멸치",
                        "낙지", "살오징어", "소라", "숭어"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(PredictSelect.this);
                dialog  .setTitle("해산물을 선택하세요")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedIndex[0] = which;
                                name=items[selectedIndex[0]];
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(PredictSelect.this, items[selectedIndex[0]], Toast.LENGTH_SHORT).show();
                                btnDialog3.setText(items[selectedIndex[0]]);
                            } }).create().show();
            }
        });

        //다른 화면으로 넘어가는 버튼
        button2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), PredictPrice.class);
                        if(month == null || month =="월" || day == null || day=="일"|| name==null || name=="해산물") {
                            AlertDialog.Builder alert = new AlertDialog.Builder(PredictSelect.this);
                            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();     //닫기
                                }
                            });
                            alert.setMessage("항목을 모두 선택해주세요");
                            alert.show();
                            return;
                        }
                        intent.putExtra("year", year);
                        intent.putExtra("month", month);
                        intent.putExtra("day", day);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                }
        );
    }


}
