# Ejercicio 2
**Para cada una de las siguientes situaciones, realice en forma iterativa los siguientes pasos:**
1. indique el mal olor, <br/>
2. indique el refactoring que lo corrige, <br/> 
3. aplique el refactoring, mostrando el resultado final (código y/o diseño según corresponda). <br/>
<br/>Si vuelve a encontrar un mal olor, retorne al paso (i).

## 2.2 Juego
```java
public class Juego {
    // ......
    public void incrementar(Jugador j) {
        j.puntuacion = j.puntuacion + 100;
    }

    public void decrementar(Jugador j) {
        j.puntuacion = j.puntuacion - 50;
    }

    public class Jugador {
        public String nombre;
        public String apellido;
        public int puntuacion = 0;
    }

} (...)
```
---
# RESOLUCIÓN:
## bad smell 1:
  los atributos de la clase Juego no deberían ser públicos, esto rompe el encapsulamiento

## refactoring 1:
  cambiar visibilidad de atributos y crear getters y setters para los mismos:

```java
public class Juego {
    // ......
    public void incrementar(Jugador j) {
        j.puntuacion = j.puntuacion + 100;
    }

    public void decrementar(Jugador j) {
        j.puntuacion = j.puntuacion - 50;
    }

    public class Jugador {
        private String nombre;
        private String apellido;
        private int puntuacion = 0;

        public String getNombre(){
          return this.nombre;
        }

        public void setNombre(String nombre){
          this.nombre=nombre;
        }

        public String getApellido(){
          return this.apellido;
        }

        public void setApellido(String apellido){
          this.apellido=apellido;
        }

        public int getPuntuacion(){
          return this.puntuacion;
        }

        public void setPuntuacion(int puntuacion){
          this.puntuacion=puntuacion;
        }
    }

} (...)
```
## bad smell 2:
los nombres de los métodos son poco descriptivos: no dejan en claro qué incrementan o a qué hace referencia
## refactoring 2:
reemplazar los nombres poco descriptivos por uno más eficiente.

```java
public class Juego {
    // ......
    public void incrementarPuntuacion(Jugador j) {
        j.puntuacion = j.puntuacion + 100;
    }

    public void decrementarPuntuacion(Jugador j) {
        j.puntuacion = j.puntuacion - 50;
    }
  (...)
} (...)
```
---
## bad smell 3:
envidia de métodos: los métodos que incluye la clase `Juego` deberían estar en la clase `Jugador` ya que modifican sus atributos. 


ESTO COMO SE DEBE RESOLVER? FEATURE ENVY

