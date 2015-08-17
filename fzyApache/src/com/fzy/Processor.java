package com.fzy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Processor extends Thread {

	private Socket socket;
	private InputStream in;
	private PrintStream out;
	
	public final static String web_root = "";
	
	public void Processor(Socket socket) {
		try {
			this.socket = socket;
			in = socket.getInputStream();
			out = new PrintStream(socket.getOutputStream());
		} catch (IOException e) {

		}
	}

	public void start() {
		String fileName = parse(in);
		
		if(fileName!=null)
			SendFile(fileName);
	}

	public String parse(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String filename = null;
		
		try{
			String httpMsg = br.readLine();
			String[] content = httpMsg.split(" ");
			
			if(content.length!=3)
			{
				SendErrorMessage(400,"receive error");
				return null;
			}
			
			filename = content[1];
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return filename;
	}

	public void SendErrorMessage(int errCode, String errMessage) {
		out.println("HTTP/1.0"+errCode+" "+errMessage);
		out.println("content-type:text/html");
		out.println();
		out.println("<html>");
		out.println("<title>Error Message</title");
		out.println("<body>");
		out.println("<h1>ErrorCode："+errCode+" ErrorMessage:"+errMessage+"</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void SendFile(String fileName) {
		File file = new File(web_root+fileName);
		
		if(!file.exists())
		{
			SendErrorMessage(404,"file not found");
			return;
		}
		
		try
		{
			InputStream in = new FileInputStream(file);
			byte content[]= new byte[(int)file.length()];
			in.read(content);
			out.println("HTTP/1.0 200 queryfile");
			out.println("content-length:"+content.length);
			out.println();
			out.write(content);
			out.flush();
			out.close();
			in.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
