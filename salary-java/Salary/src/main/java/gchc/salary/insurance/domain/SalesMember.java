/*result용*/
package gchc.salary.insurance.domain;

import java.util.Date;

import gchc.salary.insurance.domain.SalesMember;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesMember {
	private int MemberId;
	private String ID;
	private String Password;
	private String Name;
	private String Email;
	private int Grade;
	private String IsDelete;
	private Date CreateDateTime;
}
