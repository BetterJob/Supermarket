package socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.dom4j.Document;

import tools.Tools;

public class SocketClient {	
	private PrintStream ps = null;
	private Socket s = null;
	private XMLManage socketResult = null;
	
	public SocketClient(String addr,int port,int timeOut) throws IOException {
		s = new Socket();
		s.connect(new InetSocketAddress(addr, port), timeOut);//连接，并设置超时时间
		ps = new PrintStream(s.getOutputStream());
		new Thread(new ClientThread(s)).start();
	}
	public SocketClient(String addr,int port) throws IOException {
		s = new Socket(addr, port);//连接
		ps = new PrintStream(s.getOutputStream());
		new Thread(new ClientThread(s)).start();
	}
	
	//fileEndFlag必须和数据在同一行
	public void write(String str){
		ps.println(str+Tools.fileEndFlag);
		//ps.print();
	}
	
	public void write(Document doc){
		ps.println(doc.asXML()+Tools.fileEndFlag);
		//ps.print();
	}
	
	public class ClientThread implements Runnable {
		private BufferedReader br = null;
		public ClientThread(Socket s) throws IOException {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String content = "";
			try {
				content = br.readLine();
				write("client send 2");
				write("client send 3");
				write("client send 4");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("clint print receive "+content);
			
			/*String content = "";
			try {
				String temp = "";
				while(!(temp = br.readLine()).endsWith(Tools.endingFlag)) {
					content += Tools.charset.decode(Tools.getByteBuffer(temp));
				}
				content = removeEndingFlag(content);
				socketResult = new XMLManage(content);
				socketResult.writeXmltoOutputStream(new FileOutputStream(new File("E:\\xml.xml")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					br.close();
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
		}
		
	}
	
	public XMLManage getResult(){
		return socketResult;
	}
	
	public String removeEndingFlag(String str){
		return str.substring(0, str.indexOf(Tools.fileEndFlag));
	}
}
