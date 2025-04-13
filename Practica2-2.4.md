# 2.4 Carrito de compras
```java
public class Producto {
  private String nombre;
  private double precio;
    
  public double getPrecio() {
    return this.precio;
  }
}

public class ItemCarrito {
  private Producto producto;
  private int cantidad;
        
  public Producto getProducto() {
    return this.producto;
  }
    
  public int getCantidad() {
    return this.cantidad;
  }

}

public class Carrito {
  private List<ItemCarrito> items;
    
  public double total() {
    return this.items.stream()
      .mapToDouble(item -> item.getProducto().getPrecio() * item.getCantidad())
      .sum();
    }
}

```
---
## Bad Smell: middle man
arriesgo, para consultar si será que ItemCarrito es un middle man que se puede quitar del medio ya que únicamente pasa la pelota <b/>
a la clase producto. 
### refactoring: la propuesta para el middle man es eliminar esta clase. 
Para ello se debe reorganizar el código de Carrito considerando que la lista es ahora de productos, y que estos deben tener una <b/>
cantidad asociada que se usará para calcular el total <b/>

```java
public class Producto {
  private String nombre;
  private double precio;
  private int cantidad;
    
  public double getPrecio() {
    return this.precio;
  }
  public int getCantidad(){
    return this.cantidad;
  }
}

public class Carrito {
  private List<Producto> items;
    
  public double total() {
    return this.items.stream()
      .mapToDouble(item -> item.getPrecio() * item.getCantidad())
      .sum();
    }
}

```

