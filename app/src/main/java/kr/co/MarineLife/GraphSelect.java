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


public class GraphSelect extends AppCompatActivity {

    private Button btnDialog1;
    private Button btnDialog2;
    private Button btnDialog3;
    private TextView TextView1;
    private Button button4;
    String year,month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graphselect);

//        final List<String> selectedItems = new ArrayList<String>();

        btnDialog1 = (Button) findViewById(R.id.btnDialog1);
        btnDialog2 = (Button) findViewById(R.id.btnDialog2);
        button4 = (Button) findViewById(R.id.button2);
        TextView1 = (TextView) findViewById(R.id.TextView1);

        btnDialog1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                final String[] items = new String[]{"연도","2017","2018","2019"};
                final int[] selectedIndex = {0};
                AlertDialog.Builder dialog = new AlertDialog.Builder(GraphSelect.this);
                dialog  .setTitle("연도를 선택하세요")
                        .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedIndex[0] = which;
                                year=items[selectedIndex[0]];
                                if(year=="2019" && (month=="08" || month=="09" || month=="10" || month=="11" || month=="12")  ) {
                                    month="월";
                                    btnDialog2.setText("월");
                                }
                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(GraphSelect.this, items[selectedIndex[0]], Toast.LENGTH_SHORT).show();
                                btnDialog1.setText(items[selectedIndex[0]]);
                            } }).create().show();
            }
        });

        btnDialog2.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                if(year=="2019") {

                    final String[] items_ = new String[]{"월","01","02","03","04","05","06","07"};
                    final int[] selectedIndex_ = {0};
                    AlertDialog.Builder dialog_ = new AlertDialog.Builder(GraphSelect.this);
                    dialog_  .setTitle("월을 선택하세요")
                            .setSingleChoiceItems(items_, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    selectedIndex_[0] = which;
                                    month=items_[selectedIndex_[0]];
                                }
                            })
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(GraphSelect.this, items_[selectedIndex_[0]], Toast.LENGTH_SHORT).show();
                                    btnDialog2.setText(items_[selectedIndex_[0]]);
                                } }).create().show();
                }

                else {
                    final String[] items = new String[]{"월","01","02","03","04","05","06","07","08","09","10","11","12"};
                    final int[] selectedIndex = {0};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(GraphSelect.this);
                    dialog  .setTitle("월을 선택하세요")
                            .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    selectedIndex[0] = which;
                                    month=items[selectedIndex[0]];
                                }
                            })
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(GraphSelect.this, items[selectedIndex[0]], Toast.LENGTH_SHORT).show();
                                    btnDialog2.setText(items[selectedIndex[0]]);
                                } }).create().show();
                }
            }
        });

        //다른 화면으로 넘어가는 버튼
        button4.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Graph.class);
                        if(year == null || year =="연도" || month == null || month=="월") {
                            AlertDialog.Builder alert = new AlertDialog.Builder(GraphSelect.this);
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
                        startActivity(intent);
                    }
                }
        );
    }


}
