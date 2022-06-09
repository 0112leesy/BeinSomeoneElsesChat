package com.example.beinsomeoneelseschat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class SubActivity extends AppCompatActivity {
    TextView text_me;
    TextView text_you;
    TextView my_name;
    TextView your_name;

    TextView result_text_me;
    TextView result_text_you;
    TextView result_my_name;
    TextView result_your_name;

    String NOTIFICATION_CHANNEL_ID = "my_channel";
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel description");
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void sendNotification(View view) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        int theme = SelectionActivity.selected_theme;
        if(theme == 1){
            notificationBuilder.setSmallIcon(R.drawable.kakao_talk);
        }
        else if(theme == 2){
            notificationBuilder.setSmallIcon(R.drawable.messenger_facebook);
        }
        else if(theme == 3){
            notificationBuilder.setSmallIcon(R.drawable.instagram);
        }

        notificationBuilder.setContentTitle(my_name.getText().toString());
        notificationBuilder.setContentText(text_me.getText().toString());
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        createNotificationChannel();

        your_name = (TextView) findViewById(R.id.your_name);
        text_you = (TextView) findViewById(R.id.your_chat);
        my_name = (TextView) findViewById(R.id.my_name);
        text_me = (TextView) findViewById(R.id.my_chat);

//        result_your_name = (TextView) findViewById(R.id.name1);
//        result_text_you = (TextView) findViewById(R.id.text1);
//        result_my_name = (TextView) findViewById(R.id.name2);
//        result_text_me = (TextView) findViewById(R.id.text2);

        Button buttonOk = (Button) findViewById(R.id.button_ok);
        Button buttonCancel = (Button) findViewById(R.id.button_cancel);

//        your_name.setText(result_your_name.getText().toString());
//        text_you.setText(result_text_you.getText().toString());
//        my_name.setText(result_my_name.getText().toString());
//        text_me.setText(result_text_me.getText().toString());

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("YOUR_NAME", your_name.getText().toString());
                intent.putExtra("YOUR_CHAT", text_you.getText().toString());
                intent.putExtra("MY_NAME", my_name.getText().toString());
                intent.putExtra("MY_CHAT", text_me.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("YOUR_NAME", your_name.getText().toString());
                intent.putExtra("YOUR_CHAT", text_you.getText().toString());
                intent.putExtra("MY_NAME", my_name.getText().toString());
                intent.putExtra("MY_CHAT", text_me.getText().toString());
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}