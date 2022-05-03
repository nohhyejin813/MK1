/*등록 수정시 파라미터로 받는 부분
 * response / parameter 분리
 * */
package gchc.salary.insurance.parameter;

import java.util.Date;

import lombok.Data;

@Data
public class SalesCompanyParameter {
	private int salesCompanyID;
	private String salesCompanyName;
	private int proposalTarget;
	private String firstRegDate;
	private int expectedSales;
	private int salesGrade;
	private int salesCompanyType;
	private int memberCount;
	private int industryGroup;
	private String zipCode;
	private String address1;
	private String address2;
	private int salesType1;
	private int salesType2;
	private int salesLevel;
	private String salesMemo;
	private int isOffer;
	private int salesResult;
	private String salesResultMemo;
	private int isProposal;
	private String contactDate;
	private int isCompanyIntro;
	private String openDate;
	private int memo;
	private String filePath;
	private int isDelete;
//	private Date LastUpdateDateTime;
//	private Date CreateDateTime;
	
}
