package com.example.mqtt;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mqtt.client.MQTTClient;
import com.example.mqtt.service.MQTTConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MQTTClient client;
    EditText topic;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topic = findViewById(R.id.topic);
        message = findViewById(R.id.message);
        final Button disconnect_btn = findViewById(R.id.disconnect_btn);

        client = MQTTClient.getInstance(this);

        final Button connect_btn = findViewById(R.id.connect_btn);
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.startConnection();
                TextView MQTT_Server = findViewById(R.id.Server_MQTT);
                MQTT_Server.setText(MQTTConfiguration.MQTT_BROKER_URL.substring(6));
                disconnect_btn.setEnabled(true);
                connect_btn.setEnabled(false);
            }
        });

        disconnect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.disconnect();
                TextView MQTT_Server = findViewById(R.id.Server_MQTT);
                MQTT_Server.setText(R.string.no_conn);
                Button disconnect_btn = findViewById(R.id.disconnect_btn);
                disconnect_btn.setEnabled(false);
                connect_btn.setEnabled(true);
            }
        });

        Button publish_btn = findViewById(R.id.publish_btn);
        publish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.publish(topic.getText().toString(), message.getText().toString());
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
