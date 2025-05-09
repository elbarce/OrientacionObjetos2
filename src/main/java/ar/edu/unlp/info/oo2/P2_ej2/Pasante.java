package ar.edu.unlp.info.oo2.P2_ej2;

public class Pasante extends Trabajador{
	
	private int adicional=2000;
	private int examenesRendidos;
	
	public double getSueldoBasico() {
		return this.basico;
	}
	public double getAdicional() {
		return adicional*this.examenesRendidos;
	}

}
