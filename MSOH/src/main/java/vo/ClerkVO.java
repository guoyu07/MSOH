package vo;

import java.io.Serializable;
/**
 * 
 * @author zqh
 *
 */
public class ClerkVO extends UserVO implements Serializable{

	public String hotelName;

	public String hotelID;

	public ClerkVO(){}
	
	public ClerkVO(String name,String phone,String password,String ID,String picUrl,String hotelName,String hotelID){
		super(name,phone,password,ID,picUrl);
		this.hotelName=hotelName;
		this.hotelID=hotelID;
		this.picUrl = picUrl;
	}
}
