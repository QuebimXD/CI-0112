public class ArbolMascota{
    private NodoMascotaABB raiz; 
    public ArbolMascota(){//Aca establecemos el nodo raiz del arbol.
        this.raiz = null;
    }
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
    public void insertarGui(Mascota datoMascota){//Este metodo es para hacer referencia del insertar recursivo en la GUI
        this.raiz= this.insertarMascotaRec(datoMascota, this.raiz);
    }

    //Metodo de borrar nodos:
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
    private NodoMascotaABB encontrarMinimo(NodoMascotaABB nodoMascotaABBMin){
        while(nodoMascotaABBMin.getIzquierdo()!= null){
            nodoMascotaABBMin = nodoMascotaABBMin.getIzquierdo();
        }
        return nodoMascotaABBMin;
    }

    //Metodo para llamar elimiar desde la gui.
    public void eliminarGui(Mascota datoMascota){
        this.raiz = eliminarMascotaRec(datoMascota, this.raiz);
    }

    //Metodo de buscar mascotas por nombre, en este caso lo voy a realizar por el id pues es como ordene el arbol (tengo el visto bueno por el profe :)): 
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
    public NodoMascotaABB buscarMascotaGui(int id){
        Mascota mascotaBuscada = new Mascota(id, "", "");//Vea que el nombre y la especie no son relevantes para el metodo por ello estan rellenos con "".

        return buscarMascotaIdABBRec(mascotaBuscada, this.raiz);
    }

    //Metodo para mostrar recorrido: en este caso desarrolle los tres posibles casos en el recorrido de un arbol que serian los inorder, postorden y preorden 
    //Metodo de recorrido, inorden:
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
    public String recorridoInOrdenGui(){
        return recorridoInOrdenABBRec(this.raiz);//Ojo aca usamos el .trim() para evitar saltos de linea no esperados al utilizar el metodo recursivo de alli que los busquemos elimiar cuando se visualizan como espacios en blanco 
    }

    //Metodo para saber si el arbol esta vacio:
    public boolean estaVacio() {
        return this.raiz == null;
    }
}