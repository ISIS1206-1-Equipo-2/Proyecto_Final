package model.logic;

public class REQ4 implements Comparable <REQ4> {
	

	private String nombreCompania;
	
	private int numTaxis;
	
	public REQ4(String nombreCompania, int numTaxis) {
		super();
		this.nombreCompania = nombreCompania;
		this.numTaxis = numTaxis;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public int getNumTaxis() {
		return numTaxis;
	}

	public void setNumTaxis(int numTaxis) {
		this.numTaxis = numTaxis;
	}

	@Override
	public int compareTo(REQ4 e) {
		if (e.getNumTaxis()>numTaxis) {
			return 1;
		}else if (e.getNumTaxis()==numTaxis) {
			return 0;
		}else {
			return -1;
		}
	}


}
