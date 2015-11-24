package socket;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import tools.Tools;

public class SocketServer implements Runnable {
	ServerSocket ss = null;
	private int cnt = 0;
	public SocketServer(int port) throws IOException{
		ss = new ServerSocket(port);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Socket s = ss.accept();
			new Thread(new ServerThread(s)).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private class ServerThread implements Runnable{
		Socket s = null;
		BufferedReader br = null;
		public ServerThread(Socket s) throws IOException {
			// TODO Auto-generated constructor stub
			this.s = s;
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String content = "";
			try {
				String readStr = "";
				while((readStr = br.readLine()) != null) {
					//readStr = readStr.replaceAll("[\\t\\n\\r]", "");
					content += readStr;
					if(content.contains(Tools.fileEndFlag)) {
						praseData(new XMLManage(content.substring(0, content.indexOf(Tools.fileEndFlag))));
						content = "";
					}
					/*content += readStr;
					if(content.contains(Tools.fileEndFlag)) {
						content = content.replaceAll("[\\t\\n\\r]", "");
						content += " ";
						String[] temp = content.split(Tools.fileEndFlag);
						System.out.println("content : "+content);
						System.out.println("length:"+temp.length);
						for(int i=0;i<temp.length-1;i++){
							System.out.println("temp : "+i+temp[i]);
							praseData(new XMLManage(temp[i]));
						}
						content = temp[temp.length-1].equals(" ")?"":temp[temp.length-1].substring(0, temp[temp.length-1].length()-1);
						System.out.println("next :"+content);
					}*/
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void praseData(XMLManage xm) throws FileNotFoundException {
			// TODO Auto-generated method stub
			System.out.println(xm.getDocument().asXML());
			xm.writeXmltoOutputStream(new FileOutputStream(new File("D:\\xml"+((cnt++)%10)+".xml")));
			//System.out.println("success");
		}
		
	}

}
