package application;

public class tableAnt {
	
	private int pid;
	private String typeant;
	private String ant;
	private String comm;
	
	
	public tableAnt(int pid, String typeant, String ant, String comm) {
		this.pid = pid;
		this.typeant = typeant;
		this.ant = ant;
		this.comm = comm;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getTypeant() {
		return typeant;
	}
	public void setTypeant(String typeant) {
		this.typeant = typeant;
	}
	public String getAnt() {
		return ant;
	}
	public void setAnt(String ant) {
		this.ant = ant;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
}
