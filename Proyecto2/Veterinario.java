public class Veterinario{
    private ColaMascota cola;
    private ArbolMascota arbol; 

    /**
     *En este constructor, instanciamos nuevos objetos de tipo ColaMascota, y ArbolMascota, que conforman a la clase Veterinario; va a tener estos dos atributos.
     */
    public Veterinario(){
        this.cola = new ColaMascota();
        this.arbol = new ArbolMascota();
    }

    /**
     * Este metodo registra una nueva mascota, la agrega al registro del arbol, y tambien la agrega a la cola.
     * @param nombre El nombre de la mascota.
     * @param id el id de la misma.
     * @param especie la especie de la mascota que va a ser registrada.
     * @return a la mascota registrada en la cola y guardada en el arbol, null si la mascota ya se encuentra en el arbol, esto se verifica con el metodo de buscar a la mascota por la id.
     */
    public Mascota registrar(String nombre, int id, String especie){
        if(arbol.buscarMascotaGui(id) != null){
            return null;
        }
        
        Mascota nueva = new Mascota(id, nombre, especie);
        //Verificar q no se encuentre ya en el arbol
        
        cola.enqueue(nueva);
        arbol.insertarGui(nueva);
        return nueva;
    }
   
    
    //Los siguientes metodos tienen que ver unicamente con la cola

    /**
     * Este metodo tiene una logica similar al metodo de dequeue de la clase ColaMascota. Se encarga de llamar al metodo de la clase mencionada, para retornar a la mascota atendida de la cabeza de la cola.
     * @return un atributo de tipo Masocta que corresponde a la mascota sacada de la cola con el metodo dequeue(). Null si esta misma no existe; i.e. la cola esta vacia.
     */

    public Mascota atender(){
        return cola.dequeue();
    } 

    /**
     * Este metodo muestra para la GUI la cola, definida ya en ColaMascota.
     * @return de forma recursiva, toda la cola actual formada por las mascotas que llegan a la veterinaria.
     */
    public String mostrarColaa(){
        return cola.mostrarCola();
    }
    

    /**
     * Este metodo funciona para mostrar a la mascota que se encuentra en la cabeza de la cola mediante el metodo peek de ColaMascota.
     * @return la mascota que se encuentra en la head.
     */
    public Mascota mostrarAtendida(){
        return cola.peek();
    }
    
    
    
    //Los siguientes metodos tienen que ver unicamente con el arbol.


    //public ArbolMascota verHistorial(){
    public String verHistoria(){
        return arbol.recorridoInOrdenGui();
    }
    
    //public ArbolMascota mostrarMascotas(){
    public Mascota mostraraMascota(int id){
        NodoMascotaABB nodoMascotaIdBuscada = buscarMascotaGui(id);
        if(nodoMascotaIdBuscada != null){
            return nodo.getDatoMascota();
        } else {
            return null; 
        }
        
    }
    

   

}