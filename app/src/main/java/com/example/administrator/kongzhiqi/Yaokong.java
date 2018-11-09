package com.example.administrator.kongzhiqi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.kongzhiqi.mqtt.esp8266;
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

public class Yaokong extends AppCompatActivity {
    final String topic = "csxg";
    private String user = "026727k/cheshi";
    private String pass = "O70Yyrf2drvtuEJu";
    private String host = "tcp://026727k.mqtt.iot.gz.baidubce.com:1883";
    private String clientId = "cx00001";
    private String clientId1 = "cx00002";
    private int qos = 0;

    private MqttClient mqttClient;
    private ImageView kg01_zt;
    private ImageView kg02_zt;
    private MqttAndroidClient androidClient;
    private MqttConnectOptions options;
    private MemoryPersistence persistence;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yaokong);

        //定义控件
        Button kg01_k = (Button) findViewById(R.id.kaiGuan_01_kai);
        Button kg01_g = (Button) findViewById(R.id.kaiGuan_01_guan);
        Button kg02_k = (Button) findViewById(R.id.kaiGuan_02_kai);
        Button kg02_g = (Button) findViewById(R.id.kaiGuan_02_guan);
        Button kg_sx = (Button) findViewById(R.id.yaokong_shuaXin);

        kg01_zt = (ImageView) findViewById(R.id.kaiGuan_01_zhuangtai);
        kg02_zt = (ImageView) findViewById(R.id.kaiGuan_02_zhuangtai);

        //shuaxin();

        persistence = new MemoryPersistence();
        //clientId1 参数不能与发信端一样
        androidClient = new MqttAndroidClient(getApplicationContext(), host, clientId1, persistence);
        options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setUserName(user);
        options.setPassword(pass.toCharArray());
        options.setConnectionTimeout(30);
        options.setKeepAliveInterval(60);
        //System.out.println ("---------------------------------");

        kg_sx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuaxin();
            }
        });

        kg01_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esp8266 a1 = new esp8266("0001", "01", "qidong");
                //System.out.println(a1);
                Gson gson = new Gson();
                String json = gson.toJson(a1);
                //System.out.println(json);
                try {
                    mqttClient = new MqttClient(host, clientId, persistence);
                    mqttClient.connect(options);
                    MqttMessage mqttMessage = new MqttMessage(json.getBytes());
                    mqttMessage.setQos(qos);
                    mqttClient.publish(topic, mqttMessage);
                    mqttClient.disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }

            }
        });
        kg01_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esp8266 a1 = new esp8266("0001", "01", "tingzhi");
                System.out.println(a1);
                Gson gson = new Gson();
                String json = gson.toJson(a1);
                //System.out.println(json);
                try {
                    mqttClient.connect(options);
                    MqttMessage mqttMessage = new MqttMessage(json.getBytes());
                    mqttMessage.setQos(qos);
                    mqttClient.publish(topic, mqttMessage);
                    mqttClient.disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }

            }
        });
        kg02_k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esp8266 a1 = new esp8266("0001", "02", "qidong");
                //System.out.println(a1);
                Gson gson = new Gson();
                String json = gson.toJson(a1);
                System.out.println(json);
                try {
                    mqttClient.connect(options);
                    MqttMessage mqttMessage = new MqttMessage(json.getBytes());
                    mqttMessage.setQos(qos);
                    mqttClient.publish(topic, mqttMessage);
                    mqttClient.disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        });
        kg02_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esp8266 a1 = new esp8266("0001", "02", "tingzhi");
                //System.out.println(a1);
                Gson gson = new Gson();
                String json = gson.toJson(a1);
                System.out.println(json);
                try {
                    mqttClient.connect(options);
                    MqttMessage mqttMessage = new MqttMessage(json.getBytes());
                    mqttMessage.setQos(qos);
                    mqttClient.publish(topic, mqttMessage);
                    mqttClient.disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }

            }
        });

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
        esp8266 a1 = new esp8266("0001", "chakan");
        System.out.println(a1);
        Gson gson = new Gson();
        String json = gson.toJson(a1);
                System.out.println("----------" + json);
        //System.out.println(json);
        try {
            MqttClient mqttClient1 = new MqttClient(host, clientId, persistence);
            MqttConnectOptions options1 = new MqttConnectOptions();
            options1.setAutomaticReconnect(true);
            options1.setCleanSession(true);
            options1.setUserName(user);
            options1.setPassword(pass.toCharArray());
            options1.setConnectionTimeout(30);
            options1.setKeepAliveInterval(60);
            mqttClient1.connect(options1);
            MqttMessage mqttMessage = new MqttMessage(json.getBytes());
            mqttMessage.setQos(qos);
            mqttClient1.publish(topic, mqttMessage);
            mqttClient1.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }

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

        String info = message.toString();
        System.out.println ("------------------00" + info);
        Gson gson = new Gson();
        esp8266_shou a2 = gson.fromJson(info, esp8266_shou.class);
        System.out.println ("------------------00a   " + a2.getJiancha());

        if (a2.getSbbianhao().equals("0001")){
            if (a2.getKg_bianhao().equals("01") && a2.getZhuangtai().equals("qidong")){
                kg01_zt.setImageResource(R.mipmap.ic_kai);
            } else if (a2.getKg_bianhao().equals("01") && a2.getZhuangtai().equals("tingzhi")){
                kg01_zt.setImageResource(R.mipmap.ic_guan);
            } else if (a2.getKg_bianhao().equals("02") && a2.getZhuangtai().equals("qidong")){
                kg02_zt.setImageResource(R.mipmap.ic_kai);
            } else if (a2.getKg_bianhao().equals("02") && a2.getZhuangtai().equals("tingzhi")){
                System.out.println ("---------------------------------04");
                kg02_zt.setImageResource(R.mipmap.ic_guan);
            } else if (a2.getKg_bianhao().equals("01") && a2.getJiancha() == 0){
                System.out.println ("---------------------------------01");
                kg01_zt.setImageResource(R.mipmap.ic_kai);
            } else if (a2.getKg_bianhao().equals("01") && a2.getJiancha() == 1){
                System.out.println ("---------------------------------02");
                kg01_zt.setImageResource(R.mipmap.ic_guan);
            } else if (a2.getKg_bianhao().equals("02") && a2.getJiancha() == 0) {
                kg02_zt.setImageResource(R.mipmap.ic_kai);
            } else if (a2.getKg_bianhao().equals("02") && a2.getJiancha() == 1) {
                kg02_zt.setImageResource(R.mipmap.ic_guan);
            } else {
                System.out.println ("---------------------------------05");
            }
        } else {
            System.out.println ("---------------------------------03");
        }

    }


}





