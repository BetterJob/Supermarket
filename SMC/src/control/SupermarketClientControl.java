package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import database.StaffData;
import socket.SocketClient;
import socket.XMLManage;

public class SupermarketClientControl {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub	
		XMLManage xm = new XMLManage();
		StaffData sd = new StaffData("","guotonglu","1901210",
				5,"430224199101160000",24,"F","kfgz","kf","13146094606",
				4000,new Date(20150908),null);
		sd.packDataIntoElement(xm.getRootElement(),"test");
		StaffData sd2 = new StaffData("11","guotonglu","1901210",
				5,"430224199101160000",24,"F","kfgz","kf","13146094606",
				4000,new Date(20150908),new Date(20150908));
		sd2.packDataIntoElement(xm.getRootElement(),"test");
		xm.writeXmltoOutputStream(new FileOutputStream(new File("E:\\xml.xml")));
		StaffData sd1 = new StaffData(xm.getRootElement().element("StaffData"));
		sd1.printData();
		/*SocketClient sc = new SocketClient("127.0.0.1",30000);
		XMLManage[] xm = new XMLManage[10];
		StaffData[] sd = new StaffData[10];
		while(true){
			for(int i=0;i<xm.length;i++) {
				xm[i] = new XMLManage();
				sd[i] = new StaffData(i+"",i+"guotonglu",i+"1901210",
						5,i+"430224199101160000",24,i+"F",i+"kfgz",i+"kf",i+"13146094606",
						4000,new Date(20150908),null);
				sd[i].packDataIntoElement(xm[i].getRootElement());
				//sc.write(xm[i].getDocument());
			}	
			for(int j = 0 ; j<xm.length ; j++) {
				sc.write(xm[j].getDocument());
			}
		}*/
		/*SocketClient sc = new SocketClient("127.0.0.1",30000);
		XMLManage xm = new XMLManage();
		StaffData[] sd = new StaffData[100000];
		for(int i=0;i<sd.length;i++) {
			//xm[i] = new XMLManage();
			sd[i] = new StaffData(i+"",i+"guotonglu",i+"1901210",
					5,i+"430224199101160000",24,i+"F",i+"kfgz",i+"kf",i+"13146094606",
					4000,new Date(20150908),null);
			sd[i].packDataIntoElement(xm.getRootElement());
		}
		xm.writeXmltoOutputStream(new FileOutputStream(new File("E:\\xml.xml")));
		sc.write(xm.getDocument());
		System.out.println("dd");*/
		//sd1.packDataIntoElement(xm.getRootElement());
		//sc.write(xm.getDocument());
		//sc.write("test~~");
		
		//System.out.println(xm.getRootElement().element("StaffData").elementText("staffName"));
       /* System.out.println("succeed!");
        while(true){
        	System.out.println("num:"+Thread.activeCount()+"	Thread name:"+Thread.currentThread().getName());
        	Thread.sleep(100);
        	
        }*/
	}

}
