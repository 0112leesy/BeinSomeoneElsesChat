package com.example.beinsomeoneelseschat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

    static final int GET_STRING = 1;
    TextView title;
    TextView text_me;
    TextView text_you;
    TextView my_name;
    TextView your_name;
    Button screen_shot;
    Button button_OK;
    Button button_Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int theme = SelectionActivity.selected_theme;

        verifyStoragePermission(this);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        screen_shot = (Button) findViewById(R.id.button2);
        title = (TextView) findViewById(R.id.result_title);

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout1);
        button_OK = (Button) findViewById(R.id.button1);
        button_Cancel = (Button) findViewById(R.id.button3);
        your_name = (TextView) findViewById(R.id.name1);
        text_you = (TextView) findViewById(R.id.text1);
        my_name = (TextView) findViewById(R.id.name2);
        text_me = (TextView) findViewById(R.id.text2);

        if(theme == 1){
            layout.setBackgroundColor(Color.parseColor("#b2c7d9"));
            text_you.setBackground(getDrawable(R.drawable.kakao_yellow));
            text_me.setBackground(getDrawable(R.drawable.kakao_white));
        }

        else if(theme == 2){
            layout.setBackgroundColor(Color.parseColor("#ffffff"));
            text_you.setBackground(getDrawable(R.drawable.fackbook_blue));
            text_you.setTextColor(Color.parseColor("#ffffff"));
            text_me.setBackground(getDrawable(R.drawable.fackbook_gray));
//            text_you.setBackgroundColor(Color.parseColor("#0080ff"));
//            text_me.setBackgroundColor(Color.parseColor("#f1f1f1"));
        }
        else if(theme == 3){
            layout.setBackgroundColor(Color.parseColor("#ffffff"));
            text_you.setBackground(getDrawable(R.drawable.instagram_gray));
            text_me.setBackground(getDrawable(R.drawable.instagram_white));
//            text_you.setBackgroundColor(Color.parseColor("#efefef"));
//            text_me.setBackground(getDrawable(R.drawable.edge));
        }


        button_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                your_name = (TextView) findViewById(R.id.your_name);
//                text_you = (TextView) findViewById(R.id.your_chat);
//                my_name = (TextView) findViewById(R.id.my_name);
//                text_me = (TextView) findViewById(R.id.my_chat);
//
//                TextView sub_your_name = (TextView) findViewById(R.id.your_name);
//                TextView sub_text_you = (TextView) findViewById(R.id.your_chat);
//                TextView sub_my_name = (TextView) findViewById(R.id.my_name);
//                TextView sub_text_me = (TextView) findViewById(R.id.my_chat);
//
//                sub_your_name.setText(your_name.getText().toString());
//                sub_text_you.setText(text_you.getText().toString());
//                sub_my_name.setText(my_name.getText().toString());
//                sub_text_me.setText(text_me.getText().toString());

                Intent intent = new Intent(ResultActivity.this, SubActivity.class);

                startActivityForResult(intent, GET_STRING);
            }
        });

        button_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_STRING && resultCode == RESULT_OK) {
            my_name.setText(data.getStringExtra("MY_NAME"));
            your_name.setText(data.getStringExtra("YOUR_NAME"));
            text_me.setText(data.getStringExtra("MY_CHAT"));
            text_you.setText(data.getStringExtra("YOUR_CHAT"));
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void ScreenshotButton(View view){
        View rootView = getWindow().getDecorView();

        File screenShot = ScreenShot(rootView);
        if(screenShot != null){
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(screenShot)));
            screen_shot.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
            button_OK.setVisibility(View.VISIBLE);
            button_Cancel.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),"갤러리에 저장되었습니다",Toast.LENGTH_SHORT).show();
        }
    }

    public File ScreenShot(View view){
        screen_shot.setVisibility(View.INVISIBLE);
        title.setVisibility(View.INVISIBLE);
        button_OK.setVisibility(View.INVISIBLE);
        button_Cancel.setVisibility(View.INVISIBLE);

        view.setDrawingCacheEnabled(true);
        Bitmap screenBitmap = view.getDrawingCache();
        String filename = "chat_screenshot"+System.currentTimeMillis()+".png";
        File file = new File(Environment.getExternalStorageDirectory()+"/Pictures",filename);
        FileOutputStream os = null;
        try{
            os = new FileOutputStream(file);
            screenBitmap.compress(Bitmap.CompressFormat.PNG, 90, os);
            os.close();
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        view.setDrawingCacheEnabled(false);
        return file;
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSION_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void verifyStoragePermission(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity, PERMISSION_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }
}