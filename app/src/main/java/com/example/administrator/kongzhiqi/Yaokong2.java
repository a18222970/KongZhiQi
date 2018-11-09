package com.example.administrator.kongzhiqi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonToken;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kongzhiqi.mqtt.esp8266_shou;
import com.google.gson.Gson;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Yaokong2 extends AppCompatActivity {
    final String topic = "csxg";
    private String user = "026727k/cheshi";
    private String pass = "O70Yyrf2drvtuEJu";
    private String host = "tcp://026727k.mqtt.iot.gz.baidubce.com:1883";
    private String clientId = "cx00001";
    private String clientId1 = "cx00002";
    private int qos = 0;

    private MqttClient mqttClient;
    private MqttAndroidClient androidClient;
    private MqttConnectOptions options;
    private TextView xsmqtt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaokong2);
       // xsmqtt = (TextView) findViewById(R.id.xianshiMqtt);

        final MemoryPersistence persistence = new MemoryPersistence();
        //clientId1 参数不能与发信端一样
        androidClient = new MqttAndroidClient(getApplicationContext(), host, clientId1, persistence);
        options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setUserName(user);
        options.setPassword(pass.toCharArray());
        options.setConnectionTimeout(30);
        options.setKeepAliveInterval(60);

        try {
            //这个代码启动后一直运行
            androidClient.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        androidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                subscribeToTopic();
            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                startNotification(message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    private void shuaxin() {

    }

    private void subscribeToTopic() {
        try {
            androidClient.subscribe("csxg", 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    private void startNotification(MqttMessage message) {

        final String info = message.toString();
        Gson gson = new Gson();
        final esp8266_shou a2 = gson.fromJson(info, esp8266_shou.class);

//调用UI文本框组件
        runOnUiThread (new Runnable () {
            @Override
            public void run() {
                //显示接收到的消息
                xsmqtt.setText (info);
                System.out.println ("------------" + a2);
            }
        });

    }
    }


