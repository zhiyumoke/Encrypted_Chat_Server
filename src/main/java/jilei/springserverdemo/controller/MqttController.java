package jilei.springserverdemo.controller;

import com.alibaba.fastjson.JSONObject;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Controller
public class MqttController {
    private static MqttClient mqttClient;

    public static final String MQTT_HOST = "tcp://localhost:1883";
    public static final String MQTT_CLIENTID = "SERVER";
    public static final String MQTT_USERNAME = "admin";
    public static final String MQTT_PASSWORD = "admin";

    public MqttController(){
        Subscribe("Server");
    }

//    @RequestMapping(value = "/MqttPublishMessage")
//    @ResponseBody
//    public String doPublish(@RequestBody String json){
//        JSONObject jsonObject=JSONObject.parseObject(json);
//        String topic=jsonObject.getString("topic");
//        String message=jsonObject.getString("message");
//
//        try {
//            mqttClient=new MqttClient(MQTT_HOST,MQTT_CLIENTID,new MemoryPersistence());
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//        Connect(topic);
//        mqttMessage = new MqttMessage();
//        mqttMessage.setQos(2);
//        mqttMessage.setRetained(true);
//        mqttMessage.setPayload(message.getBytes());
//
//        try {
//            Publish(mqttTopic,mqttMessage);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//        return "已发送";
//    }

    @RequestMapping(value = "/MqttPublishJsonObject",method = RequestMethod.POST)
    @ResponseBody
    public String doPublish(@RequestParam Map<String,String> map){
        System.out.println(map);
        String topic=map.get("topic");
        String message=map.get("message");
        MqttMessage mqttMessage=new MqttMessage();
        mqttMessage.setQos(2);
        mqttMessage.setRetained(false);
        mqttMessage.setPayload(message.getBytes());
        try {
            mqttClient=new MqttClient(MQTT_HOST,MQTT_CLIENTID,new MemoryPersistence());
            Connect(topic);
            MqttTopic mqttTopic=mqttClient.getTopic(topic);
            Publish(mqttTopic,mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return "Server:Public Success";
    }

    private void Connect(String topic) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(MQTT_USERNAME);
        options.setPassword(MQTT_PASSWORD.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        try {
            mqttClient.connect(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Publish(MqttTopic topic, MqttMessage message) throws MqttException {
        MqttDeliveryToken token=topic.publish(message);
        token.waitForCompletion();
    }

    public void Subscribe(String topic){
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(MQTT_USERNAME);
        options.setPassword(MQTT_PASSWORD.toCharArray());
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        options.setAutomaticReconnect(true);
        try {
            MqttClient mqttClient = new MqttClient(MQTT_HOST,MQTT_CLIENTID,new MemoryPersistence());
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {

                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("Topic:"+s);
                    System.out.println("Message:"+ mqttMessage.toString());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
            mqttClient.connect(options);
            mqttClient.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
