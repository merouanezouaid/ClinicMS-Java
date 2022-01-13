package application;

public class tableRDV {
	private int pidrdv;
	private String daterdv;
	private String heurerdv;
	private String motifrdv;
	
	public tableRDV(int pidrdv, String daterdv, String heurerdv, String motifrdv) {
		this.pidrdv = pidrdv;
		this.daterdv = daterdv;
		this.heurerdv = heurerdv;
		this.motifrdv = motifrdv;
	}

	public int getPidrdv() {
		return pidrdv;
	}

	public void setPidrdv(int pidrdv) {
		this.pidrdv = pidrdv;
	}

	public String getDaterdv() {
		return daterdv;
	}

	public void setDaterdv(String daterdv) {
		this.daterdv = daterdv;
	}

	public String getHeurerdv() {
		return heurerdv;
	}

	public void setHeurerdv(String heurerdv) {
		this.heurerdv = heurerdv;
	}

	public String getMotifrdv() {
		return motifrdv;
	}

	public void setMotifrdv(String motifrdv) {
		this.motifrdv = motifrdv;
	}
	
	
}
