package tools;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Tools {
	//定义xml编码格式
	public static Charset charset = Charset.forName("UTF-8");
	//定义socket结束标示
	public static String fileEndFlag = "!-@fileEnd-!";
	public static String partEndFlag = "!-@partEnd-!";
	//定义socket通信中数据内容
	public static String content = "content";
	public static String contentDelimiter = ",";
	public static String dealType = "dealType";
	
	//定义socket通信处理方式
	public static char dealLogin = 'L';//用户登录 
	public static char dealDataInsert  = 'I';//数据库数据插入
	public static char dealDataDelete  = 'D';//数据库数据删除
	public static char dealDataAlter  = 'A';//数据库数据修改
	public static char dealDataFind  = 'F';//数据库数据查找
	/**
     * String 转换 ByteBuffer
     * @param str
     * @return
     */
    public static ByteBuffer getByteBuffer(String str)
    {
        return ByteBuffer.wrap(str.getBytes());
    }
}
