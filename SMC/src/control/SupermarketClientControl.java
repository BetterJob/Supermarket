package control;

import java.io.IOException;
import java.sql.Date;

import database.StaffData;
import socket.SocketClient;
import socket.XMLManage;

public class SupermarketClientControl {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub	
		SocketClient sc = new SocketClient("127.0.0.1",30000);
		XMLManage xm = new XMLManage();
		StaffData sd = new StaffData("20150908001","guotonglu","901210",
				5,"430224199101160000",24,"F","kfgz","kf","13146094606",
				4000,new Date(20150908),null);
		sd.packDataIntoElement(xm.getRootElement());
		//sc.write(xm.getDocument());
		sc.write("test~~");
		
		System.out.println(xm.getRootElement().element("StaffData").elementText("staffName"));
        System.out.println("succeed!");
        while(true){
        	System.out.println("num:"+Thread.activeCount()+"	Thread name:"+Thread.currentThread().getName());
        	Thread.sleep(10000);
        	
        }
	}

}
