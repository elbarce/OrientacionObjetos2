package ar.edu.unlp.info.oo2.P2_ej2;

public class Temporario extends TrabajadorConFamilia {
	private int horasTrabajadas;
	
	public double getSueldoBasico() {
		return (this.basico+horasTrabajadas*300);
	}
	
	public double getAdicional() {
		int devolver=this.cantidadHijos*this.adicionalHijo;
		if (this.casado) {
			devolver+=this.adicionalCasado;
		}
		return devolver;
	}
	
	
}	

