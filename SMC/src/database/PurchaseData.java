package database;
/**
 * Description:�������ݷ�װ
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

import tools.Tools;

public class PurchaseData extends DataModel {
	/*�������ţ�CHAR(20) NOT NULL������ƷID�������Ʒ��CHAR(20) NOT NULL����
	����(DOUBLE NOT NULL)������(DOUBLE NOT NULL)������ʱ��(DATETIME(ϵͳ�Զ���ȡ��ǰʱ��))��
	������ID�����Ա����CHAR(20) NOT NULL��*/
	public final static String goodsIDRN="goodsID";//��ƷID
	public final static String priceInRN="priceIn";//����
	public final static String amountRN="weight";//����������λkg��
	public final static String dateInRN="dateIn";//��������
	public final static String operatorRN="operator";//����Ա
	
	private String goodsID;//��ƷID
	private double priceIn;//����
	private double amount;//����������λkg��
	private Date dateIn;//��������
	private String operator;//����Ա
	
	public PurchaseData(String purchaseID,String goodsID,double priceIn,double amount,Date dateIn,String operator){
		this.id = purchaseID;
		this.goodsID = goodsID;
		this.priceIn = priceIn;
		this.amount = amount;
		this.dateIn = dateIn;
		this.operator = operator;
	}
	/**
	 * Description:���캯��������xml�й���PurchaseData��Element�ڵ�
	 * @param Element dataModelElement:xml�й���PurchaseData��Element�ڵ�
	 * @exception DatatypeConfigurationException �����ݸ�ʽ��ƥ��
	 */
	public PurchaseData(Element dataModelElement) {
		try {
			getDatafromElement(dataModelElement);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setGoodsID(String goodsID){
		this.goodsID = goodsID;
	}
	public String getGoodsID(){
		return this.goodsID;
	}
	
	public void setPriceIn(double priceIn){
		this.priceIn = priceIn;
	}
	public double getPriceIn(){
		return this.priceIn;
	}
	
	public void setAmount(double amount){
		this.amount = amount;
	}
	public double getAmount(){
		return this.amount;
	}
	
	public void setDateIn(Date dateIn){
		this.dateIn = dateIn;
	}
	public Date getDateIn(){
		return this.dateIn;
	}
	
	public void setOperator(String operator){
		this.operator = operator;
	}
	public String getOperator(){
		return this.operator;
	}
	/**
	 * Description:����PurchaseData�����Element��
	 * @param Element fatherElement:Ҫ�������Element��ͨ����Element������xml�ĵ�
	 */
	@Override
	public void packDataIntoElement(Element fatherElement,String dealType) {
		// TODO Auto-generated method stub
		Element pd = fatherElement.addElement(this.getClass().getSimpleName());
		pd.addAttribute(Tools.dealType, dealType);
		pd.addAttribute(Tools.content, this.id + Tools.contentDelimiter + this.goodsID + Tools.contentDelimiter
				+ this.priceIn + Tools.contentDelimiter + this.amount + Tools.contentDelimiter + this.dateIn 
				+ Tools.contentDelimiter + this.operator);
	}
	/**
	 * Description:��Element dataModelElement����ȡPurchaseData
	 * @param Element dataModelElement:������PurchaseData���ݵ�Element
	 * @exception DatatypeConfigurationException �� ��ʽ��ƥ��
	 */
	@Override
	public void getDatafromElement(Element dataModelElement)
			throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			String content = dataModelElement.attributeValue(Tools.content);
			String[] temp = content.split(Tools.contentDelimiter);
			this.id = temp[0];
			this.goodsID = temp[1];
			this.priceIn = temp[2].equals("null")?null:Double.valueOf(temp[2]);
			this.amount = temp[3].equals("null")?null:Double.valueOf(temp[3]);
			this.dateIn = temp[4].equals("null")?null:Date.valueOf(temp[4]);
			this.operator = temp[5];
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:��ӡ��PurchaseData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id);
		System.out.println(goodsIDRN + ":" + this.goodsID);
		System.out.println(priceInRN + ":" + this.priceIn);
		System.out.println(amountRN + ":" + this.amount);
		System.out.println(dateInRN + ":" + this.dateIn);
		System.out.println(operatorRN + ":" + this.operator);
	}
}
