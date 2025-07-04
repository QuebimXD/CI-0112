public class NodoMascotaABB{
    private Mascota datoMascota; 
    private NodoMascotaABB derecho;
    private NodoMascotaABB izquierdo;
    
    /**
     * Este es el constructor de los nodos que iran en el arbol binario, basicamente la definicion de lo que contiene cada nodo. 
     * @param datoMascota Vea que los nodos requieren el parametro de la mascota pues es lo que los compone, por ende tiene que tenerlo de entrada en los contructores. 
     */
    public NodoMascotaABB (Mascota datoMascota){
        this.datoMascota= datoMascota;
        this.derecho= this.izquierdo = null; 
    }
    /**
     * Nuevamente, solo getters y setters para retornar y asignar los atributos deseados de esta clase y para funcionalidad del arbol los nodos izquierdos y derechos.
     */
    
    //Getters:
     public Mascota getDatoMascota(){
        return this.datoMascota; 
     }
     public NodoMascotaABB getIzquierdo(){
        return this.izquierdo; 
     }
     public NodoMascotaABB getDerecho(){
        return this.derecho; 
     }

     //Setters:
     public void setDatoMascota(Mascota datoMascota){
        this.datoMascota = datoMascota;
     }
     public void setIzquierdo(NodoMascotaABB izquierdo){
        this.izquierdo = izquierdo;
     }
     public void setDerecho(NodoMascotaABB derecho){
        this.derecho = derecho; 
     }
}