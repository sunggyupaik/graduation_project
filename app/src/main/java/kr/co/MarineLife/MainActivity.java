package kr.co.MarineLife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnDialog1;
    private Button btnDialog2;
    private Button btnDialog3;
    private Button btnDialog4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDialog1 = (Button) findViewById(R.id.button1);
        btnDialog2 = (Button) findViewById(R.id.button2);
        btnDialog3 = (Button) findViewById(R.id.button3);
        btnDialog4 = (Button) findViewById(R.id.button4);

//        해양생물정보
        btnDialog1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent graph = new Intent(getApplicationContext(), TypeSelect.class);
                        startActivity(graph);
                    }
                }
        );

//        가격예측
        btnDialog2.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent predict = new Intent(getApplicationContext(), PredictSelect.class);
                        startActivity(predict);
                    }
                }
        );

        btnDialog3.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent map = new Intent(getApplicationContext(), MapSelect.class);
                        startActivity(map);
                    }
                }
        );

        btnDialog4.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent graph = new Intent(getApplicationContext(), GraphSelect.class);
                        startActivity(graph);
                    }
                }
        );
    }
}
