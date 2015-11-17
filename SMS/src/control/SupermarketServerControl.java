package control;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import socket.NSocketServer;
import database.Database;

public class SupermarketServerControl {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Database db=new Database("./config/dbProperties.ini");
		System.out.println("服务器开始");
		NSocketServer nss = new NSocketServer();
		nss.init(db.getConnection());
		new Thread(nss).start();
		/*ServerSocket ss = new ServerSocket(30000);
		while(true){
			Socket s = ss.accept();
			PrintStream ps= new PrintStream(s.getOutputStream());
			ps.println("SocketServer send!");
			ps.close();
			s.close();
		}*/
		//System.out.println("服务器结束");
		//db.createDatabase();
		
	}

}
