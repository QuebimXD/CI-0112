public class NodoMascotaCola{
   private Mascota mascota;
   private NodoMascotaCola siguiente;

   /**
    * Crea un nuevo nodo con una mascota en especifico. El nodo siguiente se inicializa como nulo, puesto que es un espacio libre para ordenar a otra mascota, luego de la inicial.
    *@param mascotaP La mascota que se almacena en este nodo. 
    */
   public NodoMascotaCola(Mascota mascotaP){
    this.mascota = mascotaP;
    this.siguiente = null;
   }

   /**
    * Todos los metodos siguientes son los getters y los setters, que ya son bien conocidos; se encargan de retornar y cambiar el valor de cada nodo.
    */
   public Mascota getMascota(){
    return mascota;
   }
   public NodoMascotaCola getSiguiente(){
    return siguiente;
   }
   public void setMascota(Mascota mascotaP){
    this.mascota = mascotaP;
   }
   public void setSiguiente(NodoMascotaCola siguienteP){
    this.siguiente = siguienteP;
   }
}