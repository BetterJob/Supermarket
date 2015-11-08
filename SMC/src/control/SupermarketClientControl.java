package control;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Date;

import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import database.StaffData;
import socket.NSocketClient;
import socket.XMLManage;

public class SupermarketClientControl {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub	
		NSocketClient nsc = new NSocketClient();
		nsc.init();
		XMLManage xm = new XMLManage();
		StaffData sd = new StaffData("20150908001","guotonglu","901210",
				5,"430224199101160000",24,"F","kfgz","kf","13146094606",
				4000,new Date(20150908),null);
		sd.packDataIntoElement(xm.getRootElement());
		sd.printData();
		//xm.writeXmltoOutputStream(new FileOutputStream(new File("E:\\xml.xml")));
        //nsc.getSocketChannel().write();
		//xm.writeXmltoSocketChannel(nsc.getSocketChannel());
		//nsc.getSocketChannel().write();
		nsc.writeXmltoSocketChannel(xm.getDocument());
		
		System.out.println(xm.getRootElement().element("StaffData").elementText("staffName"));
        System.out.println("succeed!");
	}

}
