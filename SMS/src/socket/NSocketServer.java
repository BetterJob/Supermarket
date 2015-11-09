package socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.sql.Connection;

import org.dom4j.Document;

import tools.Tools;

public class NSocketServer implements Runnable {
	private Selector selector = null;
	private ServerSocketChannel server = null;
	Charset charset = Charset.forName("UTF-8");
	public void init(Connection conn) throws IOException{
		selector = Selector.open();
		//ͨ��open��������һ��δ�󶨵�ServerSocketChannelʵ��
		server = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1",30000);
		//����ServerSocketChannel�󶨵�ָ����IP��ַ
		server.socket().bind(isa);
		//����ServerSocket�Է�����ģʽ����
		server.configureBlocking(false);
		//��serverע�ᵽָ����Selector����
		server.register(selector, SelectionKey.OP_ACCEPT);
	//	this.conn = conn;
	}
	
	//��socketChannel��д��xml�ĵ�
    public void writeXmltoSocketChannel(SocketChannel sc , Document doc) {
    	try {
			sc.write(Tools.charset.encode(doc.asXML()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(selector.select()>0) {
				//���δ���selector�ϵ�ÿ����ѡ���SelectionKey
				for(SelectionKey sk : selector.selectedKeys()) {
					//��selector�ϵ���ѡ���key������ɾ�����ڴ����selectionKey
					selector.selectedKeys().remove(sk);
					//���sk��Ӧ��ͨ�������ͻ��˵���������
					if(sk.isAcceptable()) {
						//����accept�����������ӣ������������˶�Ӧ��SocketChannel
						SocketChannel sc = server.accept();
						//����Ϊ������ģʽ
						sc.configureBlocking(false);
						//��scע�ᵽָ����Selector����
						sc.register(selector, SelectionKey.OP_READ);
						//��sk��Ӧ��Channel���ó�׼��������������
						sk.interestOps(SelectionKey.OP_ACCEPT);
					}
					else if(sk.isReadable()) {
						//��ȡ��SelectionKey��Ӧ��Channel����Channel���пɶ�ȡ������
						SocketChannel sc = (SocketChannel) sk.channel();
/**************************************��������****************************************/
						ByteBuffer buff = ByteBuffer.allocate(1024);
						String content = "";
						//��ʼ��ȡ����
						try{
							while(sc.read(buff)>0){//ȷ����Ϣ������,ÿ�ζ�����������
								buff.flip();
								content += charset.decode(buff);
							}
							System.out.println("========"+content);
							//����content
							XMLManage xm = new XMLManage(content);
							xm.writeXmltoOutputStream(new FileOutputStream(new File("D:\\xml.xml")));
							sk.interestOps(SelectionKey.OP_READ);
						}
						catch (IOException e){
							sk.cancel();
							if(sk.channel() != null){
								sk.channel().close();
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
