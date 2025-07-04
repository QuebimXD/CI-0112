public class ArbolMascota{
    private NodoMascotaABB raiz; 
    
    /**
     * Este es el constructor, note que esta vacio pues asumimos que no agregamos ninguna mascota al iniciar el programa de alli que el arbol este vacio,
     */
    public ArbolMascota(){//Aca establecemos el nodo raiz del arbol.
        this.raiz = null;
    }

    //Metodo para insertar mascotas. 
    
    /**
     * Este es el metodo recursivo que se utiliza para insertar las mascotas en el arbol binario conforme a su id.
     * @param datoMascota Este parametro es basicamente la mascota que se esta asignando al nodo, hoja o hijo del arbol. 
     * @param nodoMascotaABB Este es el espacio que toma la mascota en el arbol, es decir, el nodo que ocupara, por ende se analiza con respecto a los ids de cada uno tanto del nodo como de la mascota.
     * @return Solamente retorna un nuevo nodo, que es el creado con la mascota ingresada al sistema. 
     */
    private NodoMascotaABB insertarMascotaRec(Mascota datoMascota, NodoMascotaABB nodoMascotaABB){//Aca estamos basicamente creando el arbol binario, para que se recorra a partir del id de las mascotas. 
        if(nodoMascotaABB== null){
            return new NodoMascotaABB(datoMascota);
        }
        if(nodoMascotaABB.getDatoMascota().getId() > datoMascota.getId()){
            nodoMascotaABB.setIzquierdo(insertarMascotaRec(datoMascota, nodoMascotaABB.getIzquierdo()));
        }
        if(nodoMascotaABB.getDatoMascota().getId() < datoMascota.getId()){
            nodoMascotaABB.setDerecho(insertarMascotaRec(datoMascota, nodoMascotaABB.getDerecho()));
        }
        return nodoMascotaABB; 
    }
    
    /**
     * Este metodo es el llamado del recursivo insertar para invocarlo desde diferentes clases.
     * @param datoMascota vea que nuevamente solo necesitamos los datos, es decir, la mascota que se desea insertar para que se pueda aplicar el metodo, pues este se analiza a partir del contenido del arbol. 
     */
    public void insertarGui(Mascota datoMascota){//Este metodo es para hacer referencia del insertar recursivo en la GUI
        this.raiz= this.insertarMascotaRec(datoMascota, this.raiz);
    }

    //Metodo de borrar nodos:
    
    /**
     * Este metodo se encarga de recorrer el arbol en busqueda de una mascota que se desea eliminar, ello con respecto a su id. 
     * @param datoMascota Nuevamente esta es la mascota que se desea eliminar, en especifico tomamos la mascota indicada y vemos su id para proceder con la eliminacion. 
     * @param nodoMascotaElimABB Este es un nodo base que nos ayuda a analizar el contenido del arbol, por end, en el metodo recursivo nos permite conocer el contenido del arbol y recorrerlo en busqueda de la mascota por eliminar. 
     * @return Retornar el nodo de la mascota por eliminar, para luego simplemente quitarla por el metodo recursivo y actualizar el arbol. 
     */
    private NodoMascotaABB eliminarMascotaRec(Mascota datoMascota, NodoMascotaABB nodoMascotaElimABB){
        if(nodoMascotaElimABB == null){
            return null; 
        }
        if(nodoMascotaElimABB.getDatoMascota().getId()>datoMascota.getId()){
            nodoMascotaElimABB.setIzquierdo(eliminarMascotaRec(datoMascota, nodoMascotaElimABB.getIzquierdo()));
        }else if(nodoMascotaElimABB.getDatoMascota().getId()<datoMascota.getId()){
            nodoMascotaElimABB.setDerecho(eliminarMascotaRec(datoMascota, nodoMascotaElimABB.getDerecho()));
        }else{
            //Eliminacion de un nodo sin hijos, es decir, un nodo hoja. 
            if(nodoMascotaElimABB.getDerecho() == null && nodoMascotaElimABB.getIzquierdo()== null){
                return null; 
            } 
            //Eliminacion de un nodo con un hijo:
            if(nodoMascotaElimABB.getDerecho() == null){
                return nodoMascotaElimABB.getIzquierdo();
            } else {
                if(nodoMascotaElimABB.getIzquierdo() == null){
                return nodoMascotaElimABB.getDerecho();
                }
            }
            //Eliminacion de nodo con dos hijos
            NodoMascotaABB sucesor = encontrarMinimo(nodoMascotaElimABB.getDerecho());//Este metodo lo realizo despues de este metodo, es para buscar el minimo de la subrama derecha del arbol, pues es el "sustituto" del nodo padre.
            nodoMascotaElimABB.setDatoMascota(sucesor.getDatoMascota());//Vea que aca lo que hacemos es remplazar los datos del nodo en el que estamos por el sucesor, es decir el minimo de su subrama derecha.
            nodoMascotaElimABB.setDerecho(eliminarMascotaRec(sucesor.getDatoMascota(), nodoMascotaElimABB.getDerecho()));//Note que no hace falta verificar el Id pues el arbol ya se encuentra ordenado. 
        }
        return nodoMascotaElimABB; 
    }

    //Metodo para encontrar el minimo de la subrama derecha:
    
    /**
     * Este metodo es auxiliar al de eliminar pues sabemos que para eliminar nodos con dos hijos buscamos el minimo de su subrama derecha, para encontrar al sucesor que asignaremos como nodo padre.
     * @param nodoMascotaABBMin Aca unicamente solicitamos el nodo padre para poder tomar a su nodo sucesor. 
     * @return Retornamos el nodo mas pequeno de la subrama derecha, que es lo que requeriamos. 
     */
    private NodoMascotaABB encontrarMinimo(NodoMascotaABB nodoMascotaABBMin){
        while(nodoMascotaABBMin.getIzquierdo()!= null){
            nodoMascotaABBMin = nodoMascotaABBMin.getIzquierdo();
        }
        return nodoMascotaABBMin;
    }

    //Metodo para llamar elimiar desde la gui.
    
    
    /**
     * Este es el metodo de eliminar publico para llamarlo de otras clases que llama al recursivo para eliminar la mascota indicada.
     * @param datoMascota Aca simplemente resivimos la mascota que deseamos eliminar para proceder con el recursivo. 
     */
    public void eliminarGui(Mascota datoMascota){
        this.raiz = eliminarMascotaRec(datoMascota, this.raiz);
    }

    
    //Metodo de buscar mascotas por nombre, en este caso lo voy a realizar por el id pues es como ordene el arbol (tengo el visto bueno por el profe :)): 
    
    
    /**
     * Este es el metodo recursivo para buscar una mascota por su id en el arbol. 
     * @param datoMascota Necesitamos la mascota que se desea buscar. 
     * @param nodoMascotaBuscadaABB Requerimos el nodo que se encuentra en el arbol, bueno en realidad si ve el metodo publico lo que obtenemos es el nodo raiz del arbol y a partir de ello buscamos en los nodos hijos.
     * @return Vea que retornarmos el nodo con el id correspondiente para saber a que mascota corresponde dicho id, es decir, encontrar la mascota buscada. 
     */
    private NodoMascotaABB buscarMascotaIdABBRec(Mascota datoMascota, NodoMascotaABB nodoMascotaBuscadaABB){
        if(nodoMascotaBuscadaABB == null || nodoMascotaBuscadaABB.getDatoMascota() == null){
            return null; 
        }else if(datoMascota.getId() == nodoMascotaBuscadaABB.getDatoMascota().getId()){
            return nodoMascotaBuscadaABB;
        }else if(datoMascota.getId() > nodoMascotaBuscadaABB.getDatoMascota().getId()){
            return buscarMascotaIdABBRec(datoMascota, nodoMascotaBuscadaABB.getDerecho());
        }else{//Vea que para este punto el unico caso posible es que sea menor el datoMascota.getId(). 
            return buscarMascotaIdABBRec(datoMascota, nodoMascotaBuscadaABB.getIzquierdo());
        }
    }

    //Metodo de buscar para llamar en la GUI:
    
    
    /**
     * Este es el metodo publico de buscar par llamar de otras clases. Note que "hacemos" una nueva mascota con solo el id, pues es lo que recibimos para buscarla en el arbol. 
     * @param id Vea que unicamente necesitamos un id, que es el de la mascota buscada. 
     * @return Y aca retornarmos la mascota con el id ingresado. bueno el nodo de dicha mascota. 
     */
    public NodoMascotaABB buscarMascotaGui(int id){
        Mascota mascotaBuscada = new Mascota(id, "", "");//Vea que el nombre y la especie no son relevantes para el metodo por ello estan rellenos con "".

        return buscarMascotaIdABBRec(mascotaBuscada, this.raiz);
    }

    //Metodo para mostrar recorrido: en este caso desarrolle los tres posibles casos en el recorrido de un arbol que serian los inorder, postorden y preorden 
    //Metodo de recorrido, inorden:
    
    
    /**
     * Este es el metodo recursivo para recorrer el arbol inorder 
     * @param nodoRecorridoABB Unicamente requerimos el nodo raiz del arbol, note que asi lo asignamos en el metodo publico, para luego buscar los demas nodos con sus respectivos datos.
     * @return Retronarmos en un orden donde tomamos los datos del nodo Izquierdo, el Nodo Visitado y luego el derecho para mostrar todos los datos de las mascotas que tenga el arbol. 
     */
    private String recorridoInOrdenABBRec(NodoMascotaABB nodoRecorridoABB){
        if(nodoRecorridoABB == null){
            return "";//Vea que aca es necesario retornar un string por ello la utilidad del "". 
        }
        
        Mascota mascotaNodoRecorrido = nodoRecorridoABB.getDatoMascota();
        
        String salto = "\n";
        return recorridoInOrdenABBRec(nodoRecorridoABB.getIzquierdo()) +
            "------------------------------------" + salto +
            " Id: " + mascotaNodoRecorrido.getId() + salto + 
            " Nombre: " + mascotaNodoRecorrido.getNombre() + salto + 
            " Especie: " + mascotaNodoRecorrido.getEspecie() + salto +
            recorridoInOrdenABBRec(nodoRecorridoABB.getDerecho());
    }


    //Metodo para invocar el recorrido en la gui:
    
    /**
     * Este es el metodo publico que llamamos de otras clases para recorrer el arbol o mostrar su recorrido y lo que contiene. 
     * @return Retorna en un recorrido inorder las mascotas dentro del arbol y las muestra al usuario. 
     */
    public String recorridoInOrdenGui(){
        return recorridoInOrdenABBRec(this.raiz);//Ojo aca usamos el .trim() para evitar saltos de linea no esperados al utilizar el metodo recursivo de alli que los busquemos elimiar cuando se visualizan como espacios en blanco 
    }

    
    //Metodo para saber si el arbol esta vacio:
    
    /**
     * Este metodo es un clasico, simplemente verifica si el arbol esta vacio. 
     * @return false si no esta vacio, true si esta vacio. 
     */
    public boolean estaVacio() {
        return this.raiz == null;
    }

    /**
     * Este metodo simplemente es un gettter del arbol que se requeria para veterinario, pues a la hora de guardar las mascotas en el archivo txt lo ocupabamos
     * @return El arbol en cuestion, con las mascotas que tuviera en el momento. 
     */
    public NodoMascotaABB getRaiz(){
        return this.raiz;
    }
}