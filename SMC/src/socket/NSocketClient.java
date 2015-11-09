package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

import org.dom4j.Document;

import tools.Tools;

public class NSocketClient {
	private Selector selector = null;
	private SocketChannel sc = null;
	
	public void init() throws IOException{
		selector = Selector.open();
		InetSocketAddress isa = new InetSocketAddress("127.0.0.1",30000);
		sc = SocketChannel.open(isa);
		sc.configureBlocking(false);
		sc.register(selector, SelectionKey.OP_READ);
		new Thread(new SocketClientThread()).start();
	}
	
	public SocketChannel getSocketChannel(){
		return sc;
	}
	public void writeXmltoSocketChannel(Document doc) {
    	try {
			sc.write(Tools.charset.encode(doc.asXML()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public class SocketClientThread implements Runnable{
		public void run(){
			try {
				while(selector.select()>0){
					for(SelectionKey sk:selector.selectedKeys()){
						selector.selectedKeys().remove(sk);
						if (sk.isReadable()) {
							SocketChannel sc = (SocketChannel)sk.channel();
/**************************************处理数据****************************************/
							
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
