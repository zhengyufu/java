package com.fzy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

	public void start(int port)
	{
		try {
			ServerSocket servSocket = new ServerSocket(port);
			
			while(true)
			{
				Socket socket = servSocket.accept();
				
				new Processor().start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		int port = 80;
		
		if(args.length==1)
			port = Integer.parseInt(args[0]);
		new WebServer().start(port);

	}

}
