package com.zsy.netty.websocket;

import com.zsy.util.HexUtil;

import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Title com.socket
 */

public class WebSocketClient extends org.java_websocket.client.WebSocketClient {

    private static String TAG = "客户端";


    public WebSocketClient(URI serverUri, Map<String, String> httpHeaders) {
        super(serverUri, httpHeaders);
        System.out.println(TAG + " ======== 构造方法 ");
    }

    public WebSocketClient(URI serverUri, Draft protocolDraft, Map<String, String> httpHeaders) {
        super(serverUri, protocolDraft, httpHeaders);
        System.out.println(TAG + " ======== 构造方法 ");
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println(TAG + " onOpen ");
        send("device:0006");
    }

    @Override
    public void onMessage(String s) {
        System.out.println(TAG + " onMessage " + s);
    }

    @Override
    public void onMessage(ByteBuffer buffer) {
        super.onMessage(buffer);
        if (buffer.hasArray()) {
            byte[] array = buffer.array();
            System.out.println(TAG + " onMessage " + HexUtil.encodeHexStr(array));
        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println(TAG + " onClose " + s);
    }

    @Override
    public void onError(Exception e) {
        System.out.println(TAG + " onError " + e.toString());
    }


    private static WebSocketClient client;
    private static int count;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Map<String, String> httpHeaders = new HashMap<>();
                    httpHeaders.put("zzz", "123");
                    //192.168.1.125
                    client = new WebSocketClient(new URI("ws://127.0.0.1:9999"), httpHeaders);
                    client.connect();
                    System.out.println(TAG + " client.connect()");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                if (count % 2 == 1) {
                    client.send("测试:" + count);
                } else {
                    byte[] bytes = new byte[2];
                    bytes[0] = (byte) (count << 8);
                    bytes[1] = (byte) (count & 255);
                    client.send(bytes);
                }
                if (count >= 100) {
                    cancel();
                    client.close();
                }
            }
        }, 5000, 5000);
    }

}
