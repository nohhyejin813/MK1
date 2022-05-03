/*result용*/
package gchc.salary.insurance.domain;

import java.util.Date;

import gchc.salary.insurance.domain.SalesUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesUser {
	private int MemberId;
	private String ID;
	private String Password;
	private String Name;
	private String Email;
	private int Grade;
	private String IsDelete;
	private Date CreateDateTime;
	
	
//	public void SalesUser2(String passwd, String id, String password, String name, String email) {
//		private String Passwd;
//		// TODO Auto-generated constructor stub
//		this.Passwd = passwd;
//		this.ID = id;
//	    this.Password = password;
//	    this.Name = name;
//	    this.Email = email;
//	}	
	
}
