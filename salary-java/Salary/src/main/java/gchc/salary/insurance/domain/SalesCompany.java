package gchc.salary.insurance.domain;

import java.util.Date;

import gchc.salary.insurance.domain.SalesCompany;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesCompany {
	private int SalesCompanyID;
	private String SalesCompanyName;
	private int ProposalTarget;
	private String FirstRegDate;
	private int ExpectedSales;
	private int SalesGrade;
	private int SalesCompanyType;
	private int MemberCount;
	private int IndustryGroup;
	private String ZipCode;
	private String Address1;
	private String Address2;
	private int SalesType1;
	private int SalesType2;
	private int SalesLevel;
	private String SalesMemo;
	private int IsOffer;
	private int SalesResult;
	private String SalesResultMemo;
	private int IsProposal;
	private String ContactDate;
	private int IsCompanyIntro;
	private String OpenDate;
	private int Memo;
	private String FilePath;
	private int IsDelete;
	private Date LastUpdateDateTime;
	private Date CreateDateTime;

}
