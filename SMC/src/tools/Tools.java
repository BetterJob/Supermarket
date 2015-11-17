package tools;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Tools {
	//定义xml编码格式
	public static Charset charset = Charset.forName("UTF-8");
	public static String endingFlag = "!@#$%^&*()";
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
