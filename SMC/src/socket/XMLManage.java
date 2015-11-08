package socket;
//smc
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.SocketChannel;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;


public class XMLManage {   
	/** ����document���� */  
    private Document doc = null;
    /** ����msg���ڵ� */ 
    private Element root = null;
    
    private File XMLf = null;
    
    public XMLManage(){
    	doc = DocumentHelper.createDocument();
    	root = doc.addElement("root");
    }
    public XMLManage(String str){
    	try {
			doc = DocumentHelper.parseText(str);
			root = doc.getRootElement();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void writeXmltoOutputStream(OutputStream os){
    	//�����ɵ�xml�ĵ������Ӳ����  true�����Ƿ���  
        OutputFormat format = new OutputFormat("    ",true);  
        format.setEncoding("UTF-8");//���ñ����ʽ  
        XMLWriter xmlWriter = null;
        //nsc.getSocketChannel().write();
        try {
        	xmlWriter = new XMLWriter(os,format);  
			xmlWriter.write(getDocument());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	try {
				xmlWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }
    public Document getDocument() {
    	return doc;
    }
    public Element getRootElement(){
    	return root;
    }
    public File getFile(){
    	return XMLf;
    }
}
