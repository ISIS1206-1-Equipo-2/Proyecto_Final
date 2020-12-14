package model.logic;

public class R6 implements Comparable <R6> {
	public R6(String idTaxi, double puntos) {
		super();
		this.idTaxi = idTaxi;
		this.puntos = puntos;
	}
	private String idTaxi;
	private double puntos;
	public String getIdTaxi() {
		return idTaxi;
	}
	public void setIdTaxi(String idTaxi) {
		this.idTaxi = idTaxi;
	}
	public double getPuntos() {
		return puntos;
	}
	public void setPuntos(double puntos) {
		this.puntos = puntos;
	}
	@Override
	public int compareTo(R6 e) {
		// TODO Auto-generated method stub
		if (e.getPuntos()>puntos) {
			return 1;
		}else if (e.getPuntos()==puntos) {
			return 0;
		}else {
			return -1;
		}
	}

}
