package database;

import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

/**
 * Description:销售表数据封装
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-7
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class SalesData extends DataModel {
	/*销售单号（CHAR(20) NOT NULL），商品ID（外键商品表CHAR(20) NOT NULL），
	售价(DOUBLE NOT NULL)，售量(DOUBLE NOT NULL)，售货时间(DATETIME(系统自动获取当前时间))，
	经办人ID（外键员工表CHAR(20) NOT NULL），顾客ID（外键会员表CHAR(20)  DEFAULT NULL）单号+商品ID的联合主键*/
	public final static String goodsIDRN="goodsID";
	public final static String priceOutRN="priceOut";//售价
	public final static String amountRN="amount";//售量
	public final static String dateOutRN="dateOut";//售货时间
	public final static String operaterIDRN="operater";//
	public final static String clientIDRN="clientID";//顾客ID，尽适用于会员
	
	private String goodsID;
	private double priceOut;//售价
	private double amount;//售量
	private Date dateOut;//售货时间
	private String operaterID;//
	private String clientID;//顾客ID，尽适用于会员
	
	public SalesData(String oDDNum,String goodsID,double priceOut,double amount,Date dateOut,String operaterID,String clientID){
		this.id=oDDNum;//单号
		this.goodsID=goodsID;
		this.priceOut=priceOut;//售价
		this.amount=amount;//售量
		this.dateOut=dateOut;//售货时间
		this.operaterID=operaterID;//
		this.clientID=clientID;//顾客ID，尽适用于会员
	}
	/**
	 * Description:构造函数，传入xml中关于SalesData的Element节点
	 * @param Element dataModelElement:xml中关于SalesData的Element节点
	 * @exception DatatypeConfigurationException ：数据格式不匹配
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
	 * Description:将该SalesData打包到Element中
	 * @param Element fatherElement:要打包到的Element，通过该Element可生成xml文档
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
	 * Description:从Element dataModelElement中提取SalesData
	 * @param Element dataModelElement:保存有SalesData数据的Element
	 * @exception DatatypeConfigurationException ： 格式不匹配
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
	 * Description:打印该SalesData
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
