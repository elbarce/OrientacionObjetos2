## 2.3 Publicaciones

```java
/**
* Retorna los últimos N posts que no pertenecen al usuario user
*/
public List<Post> ultimosPosts(Usuario user, int cantidad) {
        
    List<Post> postsOtrosUsuarios = new ArrayList<Post>();
    for (Post post : this.posts) {
        if (!post.getUsuario().equals(user)) {
            postsOtrosUsuarios.add(post);
        }
    }
        
   // ordena los posts por fecha
   for (int i = 0; i < postsOtrosUsuarios.size(); i++) {
       int masNuevo = i;
       for(int j= i +1; j < postsOtrosUsuarios.size(); j++) {
           if (postsOtrosUsuarios.get(j).getFecha().isAfter(postsOtrosUsuarios.get(masNuevo).getFecha())) {
              masNuevo = j;
           }    
       }
      Post unPost = postsOtrosUsuarios.set(i,postsOtrosUsuarios.get(masNuevo));
      postsOtrosUsuarios.set(masNuevo, unPost);    
   }
        
    List<Post> ultimosPosts = new ArrayList<Post>();
    int index = 0;
    Iterator<Post> postIterator = postsOtrosUsuarios.iterator();
    while (postIterator.hasNext() &&  index < cantidad) {
        ultimosPosts.add(postIterator.next());
    }
    return ultimosPosts;
}

```
---
# badsmell 1: long method
el método ultimosPosts() es extremadamente largo. Esto lo vuelve complejo de leer y de mantener. Esto se nota, además <b/>
en el hecho de que el método tiene funcionalidades adentro que están comentadas para explicar de qué se tratan.
## refactor: 
se propone hacer un extract method, para poder achicar la funcionalidad del mismo. Se crean los métodos: <b/>
`ordenarPosteosPorFecha`, `cargarUltimosNPostOtroUsuario`, `getPostOtrosUsuarios`.

```java
private List<post> ordenarPosteosPorFecha (List<post> posteos){
  for (int i = 0; i < posteos.size(); i++) {
       int masNuevo = i;
       for(int j= i +1; j < posteos.size(); j++) {
           if (posteos.get(j).getFecha().isAfter(posteos.get(masNuevo).getFecha())) {
              masNuevo = j;
           }    
       }
      Post unPost = posteos.set(i,posteos.get(masNuevo));
      posteos.set(masNuevo, unPost);    
   }
    return posteos;
}

private List<post> cargarUltimosNPostOtroUsuario (List<post> posteos, int cantidadN){
    List<Post> ultimosPosts = new ArrayList<Post>();
    int index = 0;
    Iterator<Post> postIterator = posteos.iterator();
    while (postIterator.hasNext() &&  index < cantidadN) {
        ultimosPosts.add(postIterator.next());
    }
    return ultimosPosts;
}

private List<post> getPostOtrosUsuarios (Usuario user){
    List<Post> postsOtrosUsuarios = new ArrayList<Post>();
    for (Post post : this.posts) {
        if (!post.getUsuario().equals(user)) {
            postsOtrosUsuarios.add(post);
        }
    }
    return postOtrosUsuarios;
}

```
De esta forma, el método original quedaría de manera sintética, como una secuencia de llamadas a otros métodos que resuelven <b/>
su propuesta original:

```java
public List<Post> ultimosPosts(Usuario user, int cantidad) {
  int cantidadN=0;
  (...)

  List<Post> posteosOtrosUsuarios = posteosOtrosUsuarios= getPostOtrosUsuarios(user);
  posteosOtrosUsuarios = ordenarPosteosPorFecha(posteosOtrosUsuarios);
  return cargarUltimosNPostOtroUsuario(poteosOtrosUsuarios, cantidadN);
  
}
```
---
# Bad smell 2: reinvención de la rueda
los métodos extraídos escriben formas de trabajo que ya existen como parte de las librerías de las clases que utiliza. 
## refactor: utilizar las bibliotecas nativas
por ejemplo, en el caso de las colecciones, los streams permiten hacer gran parte del proceso que indican los métodos <b/>
de forma manual. Se reemplaza los recorridos en loop con un stream

---

```java
private List<post> ordenarPosteosPorFecha (List<post> posteos){
    return posteos.stream()
        .sorted(Comparator.comparing(Post::getFecha()))
        .collect(Collectors.toList());
}

private List<post> cargarUltimosNPostOtroUsuario (List<post> posteos, int cantidadN){
        return posteos.stream()
                .limit(cantidadN)
                .collect(Collectors.toList());
}

private List<post> getPostOtrosUsuarios (Usuario user){
        return this.posts.stream()
                .filter(posts -> !post.getUsuario().equals(user))
                .collect(Collectors.toList());
}

```
