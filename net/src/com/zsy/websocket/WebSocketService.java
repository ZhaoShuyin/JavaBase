package com.zsy.websocket;

import org.java_websocket.WebSocket;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.FramedataImpl1;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

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
        counter++;
        System.out.println(TAG + " Opened " + counter);
        String zzz = handshake.getFieldValue("zzz");
        System.out.println("===================================== zzz: "+zzz);
        System.out.println(TAG + " onOpen Address :" + conn.getRemoteSocketAddress().getAddress().getHostAddress());
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int anInt = new Random().nextInt(10);
                System.out.println(TAG + " ============> " + anInt);
                conn.send(new byte[anInt]);
            }
        }, 5000, 20000);
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
//        conn.send("res -> "+message);
    }

    @Override
    public void onMessage(WebSocket conn, ByteBuffer blob) {
        byte[] array = blob.array();
        System.out.println(TAG + " onMessage array : " + Arrays.toString(array));
//        conn.send(blob);
    }


    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        FramedataImpl1 builder = (FramedataImpl1) frame;
        builder.setTransferemasked(false);
        conn.sendFrame(frame);
    }

    public static void main(String[] args) {
        WebSocketService webSocketService = null;
        try {
            webSocketService = new WebSocketService(9002, new Draft_6455());
            webSocketService.setConnectionLostTimeout(0);
            webSocketService.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}