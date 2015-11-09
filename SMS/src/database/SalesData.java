package database;

import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

/**
 * Description:���۱����ݷ�װ
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-7
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class SalesData extends DataModel {
	/*���۵��ţ�CHAR(20) NOT NULL������ƷID�������Ʒ��CHAR(20) NOT NULL����
	�ۼ�(DOUBLE NOT NULL)������(DOUBLE NOT NULL)���ۻ�ʱ��(DATETIME(ϵͳ�Զ���ȡ��ǰʱ��))��
	������ID�����Ա����CHAR(20) NOT NULL�����˿�ID�������Ա��CHAR(20)  DEFAULT NULL������+��ƷID����������*/
	public final static String goodsIDRN="goodsID";
	public final static String priceOutRN="priceOut";//�ۼ�
	public final static String amountRN="amount";//����
	public final static String dateOutRN="dateOut";//�ۻ�ʱ��
	public final static String operaterIDRN="operater";//
	public final static String clientIDRN="clientID";//�˿�ID���������ڻ�Ա
	
	private String goodsID;
	private double priceOut;//�ۼ�
	private double amount;//����
	private Date dateOut;//�ۻ�ʱ��
	private String operaterID;//
	private String clientID;//�˿�ID���������ڻ�Ա
	
	public SalesData(String oDDNum,String goodsID,double priceOut,double amount,Date dateOut,String operaterID,String clientID){
		this.id=oDDNum;//����
		this.goodsID=goodsID;
		this.priceOut=priceOut;//�ۼ�
		this.amount=amount;//����
		this.dateOut=dateOut;//�ۻ�ʱ��
		this.operaterID=operaterID;//
		this.clientID=clientID;//�˿�ID���������ڻ�Ա
	}
	/**
	 * Description:���캯��������xml�й���SalesData��Element�ڵ�
	 * @param Element dataModelElement:xml�й���SalesData��Element�ڵ�
	 * @exception DatatypeConfigurationException �����ݸ�ʽ��ƥ��
	 */
	public SalesData(Element dataModelElement) {
		try {
			getDatafromElement(dataModelElement);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void setGoodsID(String goodsID){
		this.goodsID=goodsID;//
	}
	public String getGoodsID(){
		return this.goodsID;
	}
	
	public void setPriceOut(double priceOut){
		this.priceOut=priceOut;//
	}
	public double getPriceOut(){
		return this.priceOut;
	}
	
	public void setAmount(double amount){
		this.amount=amount;//
	}
	public double getAmount(){
		return this.amount;
	}
	
	public void setDateOut(Date dateOut){
		this.dateOut=dateOut;//
	}
	public Date getDateOut(){
		return this.dateOut;
	}
	
	public void setOperaterID(String operaterID){
		this.operaterID=operaterID;//
	}
	public String getOperaterID(){
		return this.operaterID;
	}
	
	public void setClientID(String clientID){
		this.clientID=clientID;//
	}
	public String getClientID(){
		return this.clientID;
	}
	/**
	 * Description:����SalesData�����Element��
	 * @param Element fatherElement:Ҫ�������Element��ͨ����Element������xml�ĵ�
	 */
	@Override
	public void packDataIntoElement(Element fatherElement) {
		// TODO Auto-generated method stub
		Element sd = fatherElement.addElement(this.getClass().getSimpleName());
		sd.addElement(idRN).addText(this.id);
		sd.addElement(goodsIDRN).addText(this.goodsID);
		sd.addElement(priceOutRN).addText(this.priceOut+"");
		sd.addElement(amountRN).addText(this.amount+"");
		sd.addElement(dateOutRN).addText(this.dateOut+"");
		sd.addElement(operaterIDRN).addText(this.operaterID);
		sd.addElement(clientIDRN).addText(this.clientID);
	}
	/**
	 * Description:��Element dataModelElement����ȡSalesData
	 * @param Element dataModelElement:������SalesData���ݵ�Element
	 * @exception DatatypeConfigurationException �� ��ʽ��ƥ��
	 */
	@Override
	public void getDatafromElement(Element dataModelElement)
			throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			this.id = dataModelElement.elementText(idRN);
			this.goodsID = dataModelElement.elementText(goodsIDRN);
			this.priceOut = Double.valueOf(dataModelElement.elementText(priceOutRN));
			this.amount = Double.valueOf(dataModelElement.elementText(amountRN));
			this.dateOut = Date.valueOf(dataModelElement.elementText(dateOutRN));
			this.operaterID = dataModelElement.elementText(operaterIDRN);
			this.clientID = dataModelElement.elementText(clientIDRN);
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:��ӡ��SalesData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id);
		System.out.println(goodsIDRN + ":" + this.goodsID);
		System.out.println(priceOutRN + ":" + this.priceOut);
		System.out.println(amountRN + ":" + this.amount);
		System.out.println(dateOutRN + ":" + this.dateOut);
		System.out.println(operaterIDRN + ":" + this.operaterID);
		System.out.println(clientIDRN + ":" + this.clientID);
	}
}
