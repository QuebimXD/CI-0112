public class NodoMascotaABB{
    private Mascota datoMascota; 
    private NodoMascotaABB derecho;
    private NodoMascotaABB izquierdo;
    public NodoMascotaABB (Mascota datoMascota){
        this.datoMascota= datoMascota;
        this.derecho= this.izquierdo = null; 
    }
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