package tools;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Tools {
	//����xml�����ʽ
	public static Charset charset = Charset.forName("UTF-8");
	public static String endingFlag = "!@#$%^&*()";
	/**
     * String ת�� ByteBuffer
     * @param str
     * @return
     */
    public static ByteBuffer getByteBuffer(String str)
    {
        return ByteBuffer.wrap(str.getBytes());
    }
}
