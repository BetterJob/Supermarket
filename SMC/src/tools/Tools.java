package tools;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Tools {
	//����xml�����ʽ
	public static Charset charset = Charset.forName("UTF-8");
	//����socket������ʾ
	public static String fileEndFlag = "!-@fileEnd-!";
	public static String partEndFlag = "!-@partEnd-!";
	//����socketͨ������������
	public static String content = "content";
	public static String contentDelimiter = ",";
	public static String dealType = "dealType";
	
	//����socketͨ�Ŵ���ʽ
	public static char dealLogin = 'L';//�û���¼ 
	public static char dealDataInsert  = 'I';//���ݿ����ݲ���
	public static char dealDataDelete  = 'D';//���ݿ�����ɾ��
	public static char dealDataAlter  = 'A';//���ݿ������޸�
	public static char dealDataFind  = 'F';//���ݿ����ݲ���
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
