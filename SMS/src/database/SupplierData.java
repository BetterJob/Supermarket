package database;


import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

/**
 * Description:�����̱����ݷ�װ
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class SupplierData extends DataModel {
	/*������id(CHAR(20))�����������ƣ�CHAR(50)������������ϵ��(CHAR(50))��
	��������ϵ��ַ(CHAR(50))����������ϵ�绰(CHAR(30))���������ʼ���ַ(CHAR(30))��
	��������(CHAR(30))���˻�����(CHAR(30))���˻��˺�(CHAR(50))������(CHAR(200))*/
	public final static String nameRN="Name";
	public final static String linkmanRN="Linkman";//��������ϵ��
	public final static String addrRN="Addr";//
	public final static String telRN="Tel";//
	public final static String emailRN="Email";//
	public final static String bankRN="Bank";
	public final static String accountNameRN="AccountName";
	public final static String accountNumRN="AccountNum";
	public final static String commentRN="Comment";
	
	private String name;
	private String linkman;//��������ϵ��
	private String addr;//
	private String tel;//
	private String email;//
	private String bank;
	private String accountName;
	private String accountNum;
	private String comment;
	
	public SupplierData(String ID,String name,String linkman,String addr,String tel,String email,String bank,
			String accountName,String accountNum,String comment){
		this.id=ID;
		this.name=name;
		this.linkman=linkman;//��������ϵ��
		this.addr=addr;//
		this.tel=tel;//
		this.email=email;//
		this.bank=bank;
		this.accountName=accountName;
		this.accountNum=accountNum;
		this.comment=comment;
	}
	/**
	 * Description:���캯��������xml�й���SupplierData��Element�ڵ�
	 * @param Element dataModelElement:xml�й���SupplierData��Element�ڵ�
	 * @exception DatatypeConfigurationException �����ݸ�ʽ��ƥ��
	 */
	public SupplierData(Element dataModelElement) {
		try {
			getDatafromElement(dataModelElement);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return this.name;
	}
	
	public void setLinkman(String linkman){
		this.linkman=linkman;
	}
	public String getLinkman(){
		return this.linkman;
	}

	public void setAddr(String addr){
		this.addr=addr;
	}
	public String getAddr(){
		return this.addr;
	}
	
	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return this.tel;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setBank(String bank){
		this.bank=bank;
	}
	public String getBank(){
		return this.bank;
	}
	
	public void setAccountName(String accountName){
		this.accountName=accountName;
	}
	public String getAccountName(){
		return this.accountName;
	}
	
	public void setAccountNum(String accountNum){
		this.accountNum=accountNum;
	}
	public String getAccountNum(){
		return this.accountNum;
	}
	
	public void setComment(String comment){
		this.comment=comment;
	}
	public String getComment(){
		return this.comment;
	}
	/**
	 * Description:����SupplierData�����Element��
	 * @param Element fatherElement:Ҫ�������Element��ͨ����Element������xml�ĵ�
	 */
	@Override
	public void packDataIntoElement(Element fatherElement) {
		// TODO Auto-generated method stub
		Element sd = fatherElement.addElement(this.getClass().getSimpleName());
		sd.addElement(idRN).addText(this.id);
		sd.addElement(nameRN).addText(this.name);
		sd.addElement(linkmanRN).addText(this.linkman);
		sd.addElement(addrRN).addText(this.addr);
		sd.addElement(telRN).addText(this.tel);
		sd.addElement(emailRN).addText(this.email);
		sd.addElement(bankRN).addText(this.bank);
		sd.addElement(accountNameRN).addText(this.accountName);
		sd.addElement(accountNumRN).addText(this.accountNum);
		sd.addElement(commentRN).addText(this.comment);		
	}
	/**
	 * Description:��Element dataModelElement����ȡSupplierData
	 * @param Element dataModelElement:������SupplierData���ݵ�Element
	 * @exception DatatypeConfigurationException �� ��ʽ��ƥ��
	 */
	@Override
	public void getDatafromElement(Element dataModelElement)
			throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			this.id=dataModelElement.elementText(idRN);
			this.name=dataModelElement.elementText(nameRN);
			this.linkman=dataModelElement.elementText(linkmanRN);
			this.addr=dataModelElement.elementText(addrRN);
			this.tel=dataModelElement.elementText(telRN);
			this.email=dataModelElement.elementText(emailRN);
			this.bank=dataModelElement.elementText(bankRN);
			this.accountName=dataModelElement.elementText(accountNameRN);
			this.accountNum=dataModelElement.elementText(accountNumRN);
			this.comment=dataModelElement.elementText(commentRN);
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:��ӡ��SupplierData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id); 
		System.out.println(nameRN + ":" + this.name);
		System.out.println(linkmanRN + ":" + this.linkman);
		System.out.println(addrRN + ":" + this.addr);
		System.out.println(telRN + ":" + this.tel);
		System.out.println(emailRN + ":" + this.email);
		System.out.println(bankRN + ":" + this.bank);
		System.out.println(accountNameRN + ":" + this.accountName);
		System.out.println(accountNumRN + ":" + this.accountNum);
		System.out.println(commentRN + ":" + this.comment);
	}
}
