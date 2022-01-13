package application;

public class tableLabo {
	private int pid;
	private String testname;
	private int testprix;
	
	public tableLabo(int pid, String testname, int testprix) {
		this.pid = pid;
		this.testname = testname;
		this.testprix = testprix;
	}
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTestname() {
		return testname;
	}
	public void setTestname(String testname) {
		this.testname = testname;
	}
	public int getTestprix() {
		return testprix;
	}
	public void setTestprix(int testprix) {
		this.testprix = testprix;
	}
	
	
}
