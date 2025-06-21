public class ColaMascota{
    private NodoMascotaCola head;
    private NodoMascotaCola tail;
    private int tamanoCola;

    /**
     * Este es el constructor de la Cola, donde agregamos la cabeza y la cola de la clase NodoMascotaCola.
     */
    public ColaMascota(){
        this.head = null;
        this.tail = null;
        this.tamanoCola = 0;
    }

    /**
     * Este metodo se asegura de revisar si en la cola no se encuentran mascotas.
     * @return true si no se encuentra nadie en la cabeza de la cola. No hace falta revisar el tail, puesto q simplemente no habria nadie mas detras de la cabeza.
     * False si se encuentran mascotas en la cabeza de la cola.
     */

    public boolean estaVacia(){
        if (head == null){
            return true;
        }
        return false;
    }

    /**
     * El metodo de encolar agrega una mascota de NodoMascotaCola a la misma. Si esta vacia, entonces esta mascota del nodo sera la cola y cabeza a la vez.
     * Si no, se agrega esta mascota a la cola, con el metodo this.tail.setSiguiente(nodo);.
     * Luego, se aumenta el tamano de la cola, pues estamos agregando mascotas a la misma.
     * @param mascota , pues el parametro es la mascota que vamos a meter a la cola.
     */
    public void enqueue(Mascota mascota){
        NodoMascotaCola nodo = new NodoMascotaCola(mascota);
        if (estaVacia()){
            this.head = nodo;
            this.tail = nodo;
        }else{
            this.tail.setSiguiente(nodo);
            this.tail = nodo;
        }
        this.tamanoCola++;
    }

    /**
     * Este metodo hace lo contrario al anterior. Se encarga de quitar la mascota ya atendida de la cabeza de la cola, y de reordenar la cola; para que la cabeza sea el siguiente NodoMascotaCola.
     * @return atendida a la mascota que se encuentra en la cabeza de la cola, null si esta vacia.
     */
    public Mascota dequeue(){
        if(estaVacia()){
            return null;
        }
            Mascota atendida = head.getMascota(); //Guarda la mascota que ya ha sido atendida y saldra de la cola.
            this.head = head.getSiguiente(); //la mascota siguiente del NodoMascotaCola es la nueva en la cabeza de la cola.
            tamanoCola--;
            if(head == null){ //Si ya no quedan mascotas en la cabeza de la cola, pues en la cola misma tampoco van a quedar.
                tail = null;
            }
        
        return atendida;
    }

    /**
     * Este metodo muestra la mascota en la cabeza de la cola que va a ser atendida, sin ser necesariamente atendida.
     * @return la mascota que se encuentra en la cabeza. Null si no hay mascotillas en la cabeza, o sea, en la cola como tal.
     */

    public Mascota peek(){
        if(head == null){
            return null;
        }
    return head.getMascota();
    }


    /**
     * Este metodo es al que se llama para la clase de la GUI
     * @return recursivo, este llama al metodo privado recursivo, que retorna todos los datos de las mascotas presentes en la cola.
     */
    public String mostrarCola(){
        return recursivo(this.head);
    }

    /**
     * Este es el metodo recursivo. El caso base es cuando un mascota (nodo) en la cola es vacia, se acaba el metodo.
     * @return todos los datos de todas las mascotas presentes en la cola. Null si llega al caso base, o que directamente la cola este vacia.
     */
    private String recursivo(NodoMascotaCola nodo){
        if (nodo == null){
            return "";
        }
        Mascota mascota = nodo.getMascota();
        return "Nombre:" + mascota.getNombre() + " | Id: " + mascota.getId() + " | Especie: " + mascota.getEspecie() + "\n" + recursivo(nodo.getSiguiente());
    }
    
}