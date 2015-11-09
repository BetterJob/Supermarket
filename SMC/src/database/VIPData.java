package database;

import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

/**
 * Description:会员表数据封装
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class VIPData extends DataModel {
	/*会员ID(主键+CHAR(20))，会员姓名(CHAR(50) NOT NULL)，会员身份证号（CHAR(50)），
	会员性别(CHAR(1))，会员联系方式（CHAR(30) NOT NULL），会员生日（），会员密码（），
	会员邮箱(CHAR(50))，会员积分（INT），会员总积分（INT）,会员注册时间（DATE）*/
	public final static String nameRN="Name";
	public final static String iDNoRN="iDNo";//会员身份证号
	public final static String genderRN="gender";//
	public final static String telRN="tel";//
	public final static String birthdayRN="birthday";//
	public final static String passwordRN="password";//
	public final static String emailRN="email";//
	public final static String pointsRN="points";//
	public final static String totalPointsRN="totalPoints";//会员总积分（INT）,用于判断会员级别
	public final static String registDateRN="registDate";//
	
	private String name;
	private String iDNo;//
	private String gender;//
	private String tel;//
	private Date birthday;//
	private String password;//
	private String email;//
	private int points;//
	private int totalPoints;//
	private Date registDate;//
	
	public VIPData(String iD,String name,String iDNo,String gender,String tel,Date birthday,
			String password,String email,int points,int totalPoints,Date registDate){
		this.id=iD;
		this.name=name;
		this.iDNo=iDNo;//
		this.gender=gender;//
		this.tel=tel;//
		this.birthday=birthday;//
		this.password=password;//
		this.email=email;//
		this.points=points;//
		this.totalPoints=totalPoints;//
		this.registDate=registDate;//
	}
	/**
	 * Description:构造函数，传入xml中关于VIPData的Element节点
	 * @param Element dataModelElement:xml中关于VIPData的Element节点
	 * @exception DatatypeConfigurationException ：数据格式不匹配
	 */
	public VIPData(Element dataModelElement) {
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
	
	public void setIDNo(String iDNo){
		this.iDNo=iDNo;
	}
	public String getIDNo(){
		return this.iDNo;
	}
	
	public void setGender(String gender){
		this.gender=gender;
	}
	public String getGender(){
		return this.gender;
	}
	
	public void setTel(String tel){
		this.tel=tel;
	}
	public String getTel(){
		return this.tel;
	}
	
	public void setBirthday(Date birthday){
		this.birthday=birthday;
	}
	public Date getBirthday(){
		return this.birthday;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return this.password;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return this.email;
	}
	
	public void setPoints(int points){
		this.points=points;
	}
	public int getPoints(){
		return this.points;
	}
	
	public void setTotalPoints(int totalPoints){
		this.totalPoints=totalPoints;
	}
	public int getTotalPoints(){
		return this.totalPoints;
	}
	
	public void setRegistDate(Date registDate){
		this.registDate=registDate;
	}
	public Date getRegistDate(){
		return this.registDate;
	}
	/**
	 * Description:将该VIPData打包到Element中
	 * @param Element fatherElement:要打包到的Element，通过该Element可生成xml文档
	 */
	@Override
	public void packDataIntoElement(Element fatherElement) {
		// TODO Auto-generated method stub
		Element sd = fatherElement.addElement(this.getClass().getSimpleName());
		sd.addElement(idRN).addText(this.id);
		sd.addElement(nameRN).addText(this.name);
		sd.addElement(iDNoRN).addText(this.iDNo);
		sd.addElement(genderRN).addText(this.gender);
		sd.addElement(telRN).addText(this.tel);
		sd.addElement(birthdayRN).addText(this.birthday+"");
		sd.addElement(passwordRN).addText(this.password);
		sd.addElement(emailRN).addText(this.email);
		sd.addElement(pointsRN).addText(this.points+"");
		sd.addElement(totalPointsRN).addText(this.totalPoints+"");
		sd.addElement(registDateRN).addText(this.registDate+"");
	}
	/**
	 * Description:从Element dataModelElement中提取VIPData
	 * @param Element dataModelElement:保存有VIPData数据的Element
	 * @exception DatatypeConfigurationException ： 格式不匹配
	 */
	@Override
	public void getDatafromElement(Element dataModelElement)
			throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			this.id=dataModelElement.elementText(idRN);
			this.name=dataModelElement.elementText(nameRN);
			this.iDNo=dataModelElement.elementText(iDNoRN);
			this.gender=dataModelElement.elementText(genderRN);
			this.tel=dataModelElement.elementText(telRN);
			this.birthday=Date.valueOf(dataModelElement.elementText(birthdayRN));
			this.password=dataModelElement.elementText(passwordRN);
			this.email=dataModelElement.elementText(emailRN);
			this.points=Integer.valueOf(dataModelElement.elementText(pointsRN));
			this.totalPoints=Integer.valueOf(dataModelElement.elementText(totalPointsRN));
			this.registDate = Date.valueOf(dataModelElement.elementText(registDateRN));
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:打印该VIPData
	 */
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		System.out.println("Data Type is " + this.getClass().getSimpleName());
		System.out.println(idRN + ":" + this.id); 
		System.out.println(nameRN + ":" + this.name);
		System.out.println(iDNoRN + ":" + this.iDNo);
		System.out.println(genderRN + ":" + this.gender);
		System.out.println(telRN + ":" + this.tel);
		System.out.println(birthdayRN + ":" + this.birthday);
		System.out.println(passwordRN + ":" + this.password);
		System.out.println(emailRN + ":" + this.email);
		System.out.println(pointsRN + ":" + this.points);
		System.out.println(totalPointsRN + ":" + this.totalPoints);
		System.out.println(registDateRN + ":" + this.registDate);
	}
}
