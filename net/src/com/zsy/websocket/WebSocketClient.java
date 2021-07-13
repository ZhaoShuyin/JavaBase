package com.zsy.websocket;

import com.zsy.util.HexUtil;

import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        int offset = buffer.arrayOffset();
        System.out.println(TAG + " offset: " + offset);
        if (buffer.hasArray()) {
            byte[] array = buffer.array();
//            System.out.println(TAG + " onMessage \n" + HexUtil.encodeHexStr(array));
            showData(array);
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

    public static void main(String[] args) {
        try {
            Map<String, String> httpHeaders = new HashMap<>();
            httpHeaders.put("zzz", "123");
            WebSocketClient client = new WebSocketClient(new URI("ws://127.0.0.1:6666"), httpHeaders);//192.168.1.125
            client.connect();
            System.out.println(TAG + " client.connect()");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static void showData(byte[] array) {
        if (array.length != 938) {
            return;
        }
        int index = 12;
        int[] temp = new int[12];
        for (int i = 0; i < 14; i++) {
            index += 2;
            for (int j = 0; j < 4; j++) {
                temp[1] = array[index] & 255 | array[index + 1] << 8;
                temp[2] = array[index + 2] & 255 | array[index + 3] << 8;
                temp[0] = temp[1] - temp[2];                               //I   = II-III
                temp[3] = temp[2] / 2 - temp[1];                           //avR = III/2-II
                temp[4] = temp[1] / 2 - temp[2];                           //avL = II/2-III
                temp[5] = (temp[1] + temp[2]) / 2;                         //avF = (II+III)/2
                temp[6] = array[index + 4] & 255 | array[index + 5] << 8;
                temp[7] = array[index + 6] & 255 | array[index + 7] << 8;
                temp[8] = array[index + 8] & 255 | array[index + 9] << 8;
                temp[9] = array[index + 10] & 255 | array[index + 11] << 8;
                temp[10] = array[index + 12] & 255 | array[index + 13] << 8;
                temp[11] = array[index + 14] & 255 | array[index + 15] << 8;
                index += 16;
                System.out.println((4 * i + j) + "  ==> " + Arrays.toString(temp));
            }
        }
    }
}
