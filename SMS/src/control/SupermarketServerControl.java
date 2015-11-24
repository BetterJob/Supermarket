package control;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import socket.NSocketServer;
import socket.SocketServer;
import tools.Tools;
import database.Database;

public class SupermarketServerControl {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String str = "<?xml version="+1.0+" encoding="+"UTF-8"+"?><root><StaffData><id>3</id><staffName>3guotonglu</staffName><password>3901210</password><authority>5</authority><staffIDNo>3430224199101160000</staffIDNo><staffAge>24</staffAge><staffGender>F</staffGender><staffPosition>3kfgz</staffPosition><staffDepartment>3kf</staffDepartment><staffTel>313146094606</staffTel><staffSalary>4000.0</staffSalary><staffHireDate>1970-01-01</staffHireDate><staffDimissionDate>null</staffDimissionDate></StaffData></root>!-@fileEnd-!<?xml version=1.";
		String str = " "+Tools.fileEndFlag;
		String[] end = str.split(Tools.fileEndFlag);
		System.out.println("[");
		for(String temp:end){
			System.out.println(temp);
		}
		System.out.println("]");
		
		System.out.println("get content:"+str.substring(0,str.indexOf(Tools.fileEndFlag)));
		System.out.println("get next content:"+str.substring(str.indexOf(Tools.fileEndFlag)+Tools.fileEndFlag.length()));
		
		new Thread(new SocketServer(30000)).start();
		
		Database db=new Database("./config/dbProperties.ini");
		/*Database db=new Database("./config/dbProperties.ini");
		System.out.println("服务器开始");
		NSocketServer nss = new NSocketServer();
		nss.init();
		new Thread(nss).start();*/
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
