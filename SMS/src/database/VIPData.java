package database;

import java.sql.Date;

import javax.xml.datatype.DatatypeConfigurationException;

import org.dom4j.Element;

import tools.Tools;

/**
 * Description:��Ա�����ݷ�װ
 * Copyright (C), 2015-2018, Tonglu Guo
 * This program is protected by copyright laws.
 * Date:2015-5-6
 * @author  Tonglu Guo guotonglu@126.com
 * @version  1.0
 */
public class VIPData extends DataModel {
	/*��ԱID(����+CHAR(20))����Ա����(CHAR(50) NOT NULL)����Ա���֤�ţ�CHAR(50)����
	��Ա�Ա�(CHAR(1))����Ա��ϵ��ʽ��CHAR(30) NOT NULL������Ա���գ�������Ա���루����
	��Ա����(CHAR(50))����Ա���֣�INT������Ա�ܻ��֣�INT��,��Աע��ʱ�䣨DATE��*/
	public final static String nameRN="Name";
	public final static String iDNoRN="iDNo";//��Ա���֤��
	public final static String genderRN="gender";//
	public final static String telRN="tel";//
	public final static String birthdayRN="birthday";//
	public final static String passwordRN="password";//
	public final static String emailRN="email";//
	public final static String pointsRN="points";//
	public final static String totalPointsRN="totalPoints";//��Ա�ܻ��֣�INT��,�����жϻ�Ա����
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
	 * Description:���캯��������xml�й���VIPData��Element�ڵ�
	 * @param Element dataModelElement:xml�й���VIPData��Element�ڵ�
	 * @exception DatatypeConfigurationException �����ݸ�ʽ��ƥ��
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
	 * Description:����VIPData�����Element��
	 * @param Element fatherElement:Ҫ�������Element��ͨ����Element������xml�ĵ�
	 */
	@Override
	public void packDataIntoElement(Element fatherElement,String dealType) {
		// TODO Auto-generated method stub
		Element vd = fatherElement.addElement(this.getClass().getSimpleName());
		vd.addAttribute(Tools.dealType, dealType);
		vd.addAttribute(Tools.content, this.id + Tools.contentDelimiter + this.name + Tools.contentDelimiter
				+ this.iDNo + Tools.contentDelimiter + this.gender + Tools.contentDelimiter + this.tel 
				+ Tools.contentDelimiter + this.birthday + Tools.contentDelimiter + this.password 
				+ Tools.contentDelimiter + this.email + Tools.contentDelimiter + this.points 
				+ Tools.contentDelimiter + this.totalPoints + Tools.contentDelimiter + this.registDate);	
	}
	/**
	 * Description:��Element dataModelElement����ȡVIPData
	 * @param Element dataModelElement:������VIPData���ݵ�Element
	 * @exception DatatypeConfigurationException �� ��ʽ��ƥ��
	 */
	@Override
	public void getDatafromElement(Element dataModelElement)
			throws DatatypeConfigurationException {
		// TODO Auto-generated method stub
		if(dataModelElement.getName().equals(this.getClass().getSimpleName())){
			String content = dataModelElement.attributeValue(Tools.content);
			String[] temp = content.split(Tools.contentDelimiter);
			this.id=temp[0];
			this.name=temp[1];
			this.iDNo=temp[2];
			this.gender=temp[3];
			this.tel=temp[4];
			this.birthday=temp[5].equals(null)?null:Date.valueOf(temp[5]);
			this.password=temp[6];
			this.email=temp[7];
			this.points=temp[8].equals(null)?null:Integer.valueOf(temp[8]);
			this.totalPoints=temp[9].equals(null)?null:Integer.valueOf(temp[9]);
			this.registDate = temp[10].equals(null)?null:Date.valueOf(temp[10]);
		}
		else {
			throw new DatatypeConfigurationException("function getDatafromElement in " + this.getClass().getSimpleName() + "is not matched!");
		}
	}
	/**
	 * Description:��ӡ��VIPData
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
