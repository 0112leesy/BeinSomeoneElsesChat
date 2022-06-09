package com.example.beinsomeoneelseschat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class SelectionActivity extends AppCompatActivity {
    public static int selected_theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        RadioButton kakao = (RadioButton) findViewById(R.id.radio1);
        RadioButton facebook = (RadioButton) findViewById(R.id.radio2);
        RadioButton instagram = (RadioButton) findViewById(R.id.radio3);
        Button buttonOk = (Button) findViewById(R.id.button1);
        Button buttonCancel = (Button) findViewById(R.id.button2);

//        buttonOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("INPUT_TEXT", edit.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(kakao.isChecked()){
                    selected_theme = 1;
                }

                else if(facebook.isChecked()){
                    selected_theme = 2;
                }

                else if(instagram.isChecked()){
                    selected_theme = 3;
                }
                Intent intent = new Intent(SelectionActivity.this, ResultActivity.class);
                startActivity(intent);

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.putExtra("INPUT_TEXT", edit.getText().toString());
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
