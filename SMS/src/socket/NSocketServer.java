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
		//通过open方法来打开一个未绑定的ServerSocketChannel实例
		server = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1",30000);
		//将该ServerSocketChannel绑定到指定的IP地址
		server.socket().bind(isa);
		//设置ServerSocket以非阻塞模式工作
		server.configureBlocking(false);
		//将server注册到指定的Selector对象
		server.register(selector, SelectionKey.OP_ACCEPT);
	//	this.conn = conn;
	}
	
	//向socketChannel中写入xml文档
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
				//依次处理selector上到每个已选择的SelectionKey
				for(SelectionKey sk : selector.selectedKeys()) {
					//从selector上的已选择的key集合中删除正在处理的selectionKey
					selector.selectedKeys().remove(sk);
					//如果sk对应的通道包含客户端的连接请求
					if(sk.isAcceptable()) {
						//调用accept方法接受连接，产生服务器端对应的SocketChannel
						SocketChannel sc = server.accept();
						//设置为非阻塞模式
						sc.configureBlocking(false);
						//将sc注册到指定的Selector对象
						sc.register(selector, SelectionKey.OP_READ);
						//将sk对应的Channel设置成准备接受其他请求
						sk.interestOps(SelectionKey.OP_ACCEPT);
					}
					else if(sk.isReadable()) {
						//获取该SelectionKey对应的Channel，该Channel中有可读取的数据
						SocketChannel sc = (SocketChannel) sk.channel();
/**************************************处理数据****************************************/
						ByteBuffer buff = ByteBuffer.allocate(1024);
						String content = "";
						//开始读取数据
						try{
							while(sc.read(buff)>0){//确保消息完整性,每次读完所有数据
								buff.flip();
								content += charset.decode(buff);
							}
							System.out.println("========"+content);
							//解析content
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
