/*등록 수정시 파라미터로 받는 부분
 * response / parameter 분리
 * */
package gchc.salary.insurance.parameter;

import java.util.Date;

import lombok.Data;

@Data
public class SalesUserParameter {
	private int memberId;
	private String id;
	private String password;
	private String name;
	private String email;
	private int grade;
	private String isDelete;
//	private Date CreateDateTime;
	
}
