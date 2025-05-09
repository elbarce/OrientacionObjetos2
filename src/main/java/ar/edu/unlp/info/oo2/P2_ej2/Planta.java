package ar.edu.unlp.info.oo2.P2_ej2;

public class Planta extends TrabajadorConFamilia {
	
	private int basico= 50000;
	private int adicionalAntiguedad= 2000;
	private int aniosAntiguedad;
	
	public double getSueldoBasico() {
		return this.basico;
	}
	
	public double getAdicional() {
		int devolver= this.cantidadHijos*this.adicionalHijo + this.aniosAntiguedad*this.adicionalAntiguedad; 
		if (this.casado) {
			devolver+=this.adicionalCasado;
		}
		return devolver;
	}

}
