package application;

public class tableDocteur {
	
	private int docid;
	private String doccin;
	private String docnom;
	private String docprenom;
	private String doctel;
	private String docemail;
	private String docgrade;
	private String docdep;
	private String docspe;
	private String docdate;
	
	
	public tableDocteur(int docid, String doccin, String docnom, String docprenom, String doctel, String docemail,
			String docgrade, String docdep, String docspe, String docdate) {
		this.docid = docid;
		this.doccin = doccin;
		this.docnom = docnom;
		this.docprenom = docprenom;
		this.doctel = doctel;
		this.docemail = docemail;
		this.docgrade = docgrade;
		this.docdep = docdep;
		this.docspe = docspe;
		this.docdate = docdate;
	}


	public tableDocteur(String doccin, String docnom, String docprenom, String doctel, String docemail, String docgrade,
			String docdep, String docspe, String docdate) {
		super();
		this.doccin = doccin;
		this.docnom = docnom;
		this.docprenom = docprenom;
		this.doctel = doctel;
		this.docemail = docemail;
		this.docgrade = docgrade;
		this.docdep = docdep;
		this.docspe = docspe;
		this.docdate = docdate;
	}


	public int getDocid() {
		return docid;
	}


	public void setDocid(int docid) {
		this.docid = docid;
	}


	public String getDoccin() {
		return doccin;
	}


	public void setDoccin(String doccin) {
		this.doccin = doccin;
	}


	public String getDocnom() {
		return docnom;
	}


	public void setDocnom(String docnom) {
		this.docnom = docnom;
	}


	public String getDocprenom() {
		return docprenom;
	}


	public void setDocprenom(String docprenom) {
		this.docprenom = docprenom;
	}


	public String getDoctel() {
		return doctel;
	}


	public void setDoctel(String doctel) {
		this.doctel = doctel;
	}


	public String getDocemail() {
		return docemail;
	}


	public void setDocemail(String docemail) {
		this.docemail = docemail;
	}


	public String getDocgrade() {
		return docgrade;
	}


	public void setDocgrade(String docgrade) {
		this.docgrade = docgrade;
	}


	public String getDocdep() {
		return docdep;
	}


	public void setDocdep(String docdep) {
		this.docdep = docdep;
	}


	public String getDocspe() {
		return docspe;
	}


	public void setDocspe(String docspe) {
		this.docspe = docspe;
	}


	public String getDocdate() {
		return docdate;
	}


	public void setDocdate(String docdate) {
		this.docdate = docdate;
	}
	
	
	
	
}
