/*등록 수정시 파라미터로 받는 부분
 * response / parameter 분리
 * */
package gchc.salary.insurance.parameter;

import java.util.Date;

import lombok.Data;

@Data
public class SalesHistoryParameter {
	private int salesHistoryId;
	private int salesCompanyId;
	private int memberId;
	private String salesDate;
	private int salesType;
	private int proposalTarget;
	private String salesMemo;
	private String salesMemoDetail;
	private int isDelete;
	private String name;
//	private Date LastUpdateDateTime;
//	private Date CreateDateTime;

	
}
