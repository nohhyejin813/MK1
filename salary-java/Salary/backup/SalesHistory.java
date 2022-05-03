package gchc.salary.insurance.domain;

import java.util.Date;

import gchc.salary.insurance.domain.SalesHistory;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesHistory {
	private int SalesHistoryID;
	private int SalesCompanyID;
	private int MemberID;
	private String SalesDate;
	private int SalesType;
	private int ProposalTarget;
	private String SalesMemo;
	private String SalesMemoDetail;
	private int IsDelete;
	private Date LastUpdateDateTime;
	private Date CreateDateTime;
	private String Name;

}
