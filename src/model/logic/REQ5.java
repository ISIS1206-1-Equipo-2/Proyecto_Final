package model.logic;

public class REQ5 implements Comparable <REQ5> {
	

	private String nombreCompania;
	
	private int NumServicios;
	
	public REQ5(String nombreCompania, int NumServicios) {
		super();
		this.nombreCompania = nombreCompania;
		this.NumServicios = NumServicios;
	}

	public String getNombreCompania() {
		return nombreCompania;
	}

	public void setNombreCompania(String nombreCompania) {
		this.nombreCompania = nombreCompania;
	}

	public int getNumServicios() {
		return NumServicios;
	}

	public void setNumServicios(int NumServicios) {
		this.NumServicios = NumServicios;
	}

	@Override
	public int compareTo(REQ5 e) {
		if (e.getNumServicios()>NumServicios) {
			return 1;
		}else if (e.getNumServicios()==NumServicios) {
			return 0;
		}else {
			return -1;
		}
	}


}