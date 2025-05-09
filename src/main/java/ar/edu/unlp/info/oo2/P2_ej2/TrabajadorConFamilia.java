package ar.edu.unlp.info.oo2.P2_ej2;

public abstract class TrabajadorConFamilia extends Trabajador {
	protected int cantidadHijos;
	protected int adicionalCasado=5000;
	protected int adicionalHijo=2000;
	protected boolean casado;
	
	abstract protected double getSueldoBasico();
	
	abstract protected double getAdicional();

}