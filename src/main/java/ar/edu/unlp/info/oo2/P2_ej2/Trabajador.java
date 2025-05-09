package ar.edu.unlp.info.oo2.P2_ej2;

public abstract class Trabajador {
	protected int basico = 20000;
	abstract protected double getSueldoBasico();
	abstract protected double getAdicional();
	
	private double getDescuento() {
		return (this.getSueldoBasico()*.13+this.getAdicional()*.05);
	}
	
	protected double sueldo() {
		return (this.getSueldoBasico()+this.getAdicional()-this.getDescuento());
	}
	
}
