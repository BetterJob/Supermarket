package database;

import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

import tools.Tools;

/**
 * Description:��Ʒ�����ݷ�װ
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class GoodsData extends DataModel {
	/*��ƷID��CHAR(20) NOT NULL PRIMARY KEY������Ʒ���ƣ�CHAR(100) NOT NULL����
	��Ʒ���루CHAR(50)��������ͺ�(CHAR(50))����Ʒ������ID(CHAR(20)���)����Ʒ����(DOUBLE)����Ʒ�ۼ�(DOUBLE)��
	��Ʒ��Ч��(DATE)����Ʒ���(CHAR(100))����Ʒ������CHAR(200)��*/
	public final static String nameRN="Name";
	public final static String barCodeRN="BarCode";//��Ʒ����
	public final static String typeRN="Type";//��Ʒ����ͺ�
	public final static String supplierIDRN="SupplierID";//������ID_���
	public final static String priceInRN="PriceIn";//����
	public final static String priceOutRN="PriceOut";//�ۼ�
	public final static String validDateRN="ValidDate";//��Ч��
	public final static String categoryRN="Category";//���
	public final static String commentRN="Comment";//����
	public final static String remainAmountRN="remainAmount";//�����
	public final static String warnAmountRN="warnAmount";//����ٷֱ�
	
	private String name;
	private String barCode;
	private String type;
	private String supplierID;
	private double priceIn;
	private double priceOut;
	private Date validDate;
	private String category;
	private String comment;
	private double remainAmount;//�����
	private double warnAmount;
	
	public GoodsData(String ID,String name,String barCode,String type,String supplierID,
			double priceIn,double priceOut,Date validDate,String category,String comment,
			double remainAmount,double warnAmount){
		this.id=ID;
		this.name=name;
		this.barCode=barCode;
		this.type=type;
		this.supplierID=supplierID;
		this.priceIn=priceIn;
		this.priceOut=priceOut;
		this.validDate=validDate;
		this.category=category;
		this.comment=comment;
		this.remainAmount = remainAmount;
		this.warnAmount = warnAmount;
	}
	/**
	 * Description:���캯��������xml�й���GoodsData��Element�ڵ�
	 * @param Element dataModelElement:xml�й���GoodsData��Element�ڵ�
	 * @exception DatatypeConfigurationException �����ݸ�ʽ��ƥ��
	 */
	public GoodsData(Element dataModelElement) {
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
	
	public void setBarCode(String barCode){
		this.barCode=barCode;
	}
	public String getBarCode(){
		return this.barCode;
	}
	
	public void setType(String type){
		this.type=type;
	}
	public String getType(){
		return this.type;
	}
	
	public void setSupplierID(String supplierID){
		this.supplierID=supplierID;
	}
	public String getSupplierID(){
		return this.supplierID;
	}
	
	public void setPriceIn(double priceIn){
		this.priceIn=priceIn;
	}
	public double getPriceIn(){
		return this.priceIn;
	}
	
	public void setPriceOut(double priceOut){
		this.priceOut=priceOut;
	}
	public double getPriceOut(){
		return this.priceOut;
	}
	
	public void setValidDate(Date validDate){
		this.validDate=validDate;
	}
	public Date getValidDate(){
		return this.validDate;
	}
	
	public void setCategory(String category){
		this.category=category;
	}
	public String getCategory(){
		return this.category;
	}
	
	public void setComment(String comment){
		this.comment=comment;
	}
	public String getComment(){
		return this.comment;
	}
	
	public void setRemainAmount(double remainAmount){
		this.remainAmount = remainAmount;
	}
	public double getRemainAmount(){
		return remainAmount;
	}
	public void setWarnPercent(int warnAmount){
		this.warnAmount = warnAmount;
	}
	public double getWarnPercent(){
		return warnAmount;
	}
	/**
	 * Description:����GoodsData�����Element��
	 * @param Element fatherElement:Ҫ�������Element��ͨ����Element������xml�ĵ�
	 */
	@Override
	public void packDataIntoElement(Element fatherElement,String dealType) {
		// TODO Auto-generated method stub
		Element gd = fatherElement.addElement(this.getClass().getSimpleName());
		gd.addAttribute(Tools.dealType, dealType);
		gd.addAttribute(Tools.content, this.id + Tools.contentDelimiter + this.name + Tools.contentDelimiter
				+ this.barCode + Tools.contentDelimiter + this.type + Tools.contentDelimiter + this.supplierID 
				+ Tools.contentDelimiter + this.priceIn + Tools.contentDelimiter + this.priceOut + Tools.contentDelimiter
				+ this.validDate + Tools.contentDelimiter + this.category + Tools.contentDelimiter + this.comment 
				+ Tools.contentDelimiter + this.remainAmount + Tools.contentDelimiter + this.warnAmount);
		
	}
	/**
	 * Description:��Element dataModelElement����ȡGoodsData
	 * @param Element dataModelElement:������GoodsData���ݵ�Element
	 * @exception DatatypeConfigurationException �� ��ʽ��ƥ��
	 */
	@Override
	protected void getDatafromElement(Element dataModelElement) throws DatatypeConfigurationException{
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			String content = dataModelElement.attributeValue(Tools.content);
			String[] temp = content.split(Tools.contentDelimiter);
			this.id=temp[0];
			this.name=temp[1];
			this.barCode=temp[2];
			this.type=temp[3];
			this.supplierID=temp[4];
			this.priceIn=temp[5].equals("null")?null:Double.valueOf(temp[5]);
			this.priceOut=temp[6].equals("null")?null:Double.valueOf(temp[6]);
			this.validDate=temp[7].equals("null")?null:Date.valueOf(temp[7]);
			this.category=temp[8];
			this.comment=temp[9];
			this.remainAmount = temp[10].equals("null")?null:Double.valueOf(temp[10]);
			this.warnAmount = temp[11].equals("null")?null:Double.valueOf(temp[11]);
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:��ӡ��GoodsData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id); 
		System.out.println(nameRN + ":" + this.name);
		System.out.println(barCodeRN + ":" + this.barCode);
		System.out.println(typeRN + ":" + this.type);
		System.out.println(supplierIDRN + ":" + this.supplierID);
		System.out.println(priceInRN + ":" + this.priceIn);
		System.out.println(priceOutRN + ":" + this.priceOut);
		System.out.println(validDateRN + ":" + this.validDate);
		System.out.println(categoryRN + ":" + this.category);
		System.out.println(commentRN + ":" + this.comment);
		System.out.println(remainAmountRN + ":" + this.remainAmount);
		System.out.println(warnAmountRN + ":" + this.warnAmount);
	}
}
