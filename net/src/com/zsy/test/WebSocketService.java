package com.zsy.test;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class WebSocketService extends WebSocketServer {

    private static String TAG = "服务端";

    private static int counter = 0;

    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public WebSocketService(int port, Draft d) throws UnknownHostException {
        super(new InetSocketAddress(port), Collections.singletonList(d));
        System.out.println(TAG + " ======== 构造方法 ");
    }

    public WebSocketService(InetSocketAddress address, Draft d) {
        super(address, Collections.singletonList(d));
        System.out.println(TAG + " ======== 构造方法 1");
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(TAG + " Opened " + counter);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println(TAG + " closed Address :" + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        System.out.println(TAG + "WebSocket Error:" + ex.getMessage());
    }

    @Override
    public void onStart() {
        System.out.println(TAG + " started!");
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println(TAG + " onMessage message : " + message);
        conn.send(bytes);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer blob) {
        byte[] array = blob.array();
        System.out.println(TAG + " onMessage array : " + Arrays.toString(array));
        conn.send(bytes);
    }


    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        FramedataImpl1 builder = (FramedataImpl1) frame;
        builder.setTransferemasked(false);
        conn.sendFrame(frame);
    }

    private static int[] ints;
    private static byte[] bytes;


    // 1111 1111 1111 1111 1111 1111 1111 1000
    public static void main(String[] args) throws Exception {
       /* ints = new int[]{-5000, -8, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        bytes = new byte[ints.length * 2];
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i] + "  ====> " + Integer.toBinaryString(ints[i]));
            bytes[2 * i] = (byte) (ints[i] & 255);
            bytes[2 * i + 1] = (byte) (ints[i] >> 8);
            System.out.println(bytes[2 * i] & 255 | bytes[2 * i + 1] << 8);
        }
        System.out.println(Arrays.toString(bytes));*/
        File file = new File("D:/abc/origin.data");
        FileInputStream inputStream = new FileInputStream(file);
        bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        inputStream.close();
        WebSocketService webSocketService = null;
        try {
            webSocketService = new WebSocketService(9005, new Draft_6455());
            webSocketService.setConnectionLostTimeout(0);
            webSocketService.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}