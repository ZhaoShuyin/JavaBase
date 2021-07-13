package com.zsy.socket.simple;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
	
	public static void main(String[] args) throws InterruptedException {
		try {
			// 和服务器创建连接
			Socket socket = new Socket("localhost",8088);
			
			// 要发送给服务器的信息
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("客户端发送信息");
			pw.flush();
			
			socket.shutdownOutput();
			
			// 从服务器接收的信息
			InputStream is = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info = br.readLine())!=null){
				System.out.println("我是客户端，服务器返回信息："+info);
			}
			
			br.close();
			is.close();
			os.close();
			pw.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 
}