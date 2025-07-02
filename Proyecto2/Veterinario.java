import java.io.*;

public class Veterinario{
    private ColaMascota cola;
    private ArbolMascota arbol; 
    private int id;
    public static final String archivo = "mascotas.txt";

    /**
     *En este constructor, instanciamos nuevos objetos de tipo ColaMascota, y ArbolMascota y el metodo de cargarDesdeArchivo, que conforman a la clase Veterinario; va a tener estos tres atributos.
     */
    public Veterinario(){
        this.cola = new ColaMascota();
        this.arbol = new ArbolMascota();
        cargarArchivo();
    }

    /**
     * Este metodo registra una nueva mascota y la agrega al registro del arbol.
     * Tambien le da un id aleatorio entre 1000 y 9999 a la nueva mascota ingresada al arbol.
     * @param nombre El nombre de la mascota.
     * @param especie la especie de la mascota que va a ser registrada.
     * @return a la mascota guardada en el arbol, null si la mascota ya se encuentra en el arbol, esto se verifica con el metodo de buscar a la mascota por la id.
     * tambien guarda a la mascota registrada en el txt que instanciamos, con el metodo guardarEnArchivo.
     */

    public Mascota registrar(String nombre, String especie){

        //Declaramos un int que nos va a ayudar a darle un id random a la mascota
        id = (int)(Math.random() * 9000) + 1000;

        //Mientras existan nodos de mascotas en el arbol con la misma id, se randomiza de nuevo la misma.
        while(arbol.buscarMascotaGui(id) != null){
            id = (int)(Math.random() * 9000) + 1000;
        }
        
        Mascota nueva = new Mascota(id, nombre, especie);
        arbol.insertarGui(nueva);
        guardarEnArchivo(nueva);
        return nueva;
    }

    /**
     * Este metodo mete a la cola las mascotas que ya se encuentran en el arbol y no en la cola.
     * @param id ID de la mascota que se desea ingresar a la cola.
     * @return true si se ingreso exitosamente a la cola, false si no, o si ya se encontraba en la cola inicialmente.
     */

    public boolean ingresarCola(int id){
        NodoMascotaABB nodo = arbol.buscarMascotaGui(id);
        if(nodo == null){
            return false;
        }

        Mascota mascota = nodo.getDatoMascota();
        if(cola.contiene(mascota)){
            return false;
        }

        cola.enqueue(mascota);
        return true;
    }


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

    /**
     * Este metodo muestra el historial del arbol en el modo de recorrido del arbol binario inOrden.
     * @return una lista recursiva de todas las mascotas que se encuentran en los nodos del arbol.
     */
    public String verHistoria(){
        return arbol.recorridoInOrdenGui();
    }
    
    /**
     * Este metodo busca la mascota por la id, util para la GUI.
     * @return la mascota en el arbol por la ID, null si no se encuentra a la mascota.
     */
    public Mascota mostrarMascota(int id){
        NodoMascotaABB nodoMascotaIdBuscada = arbol.buscarMascotaGui(id);
        if(nodoMascotaIdBuscada != null){
            return nodoMascotaIdBuscada.getDatoMascota();
        } else {
            return null; 
        }
        
    }

    /**
     * Elimina una mascota del arbol. Este metodo va a ser util para la GUI.
     * Si se elimina la mascota, el lector de textos tambien lo elimina del txt
     */
    public void eliminarMascota(Mascota mascotaPorElim){
        arbol.eliminarGui(mascotaPorElim);
        guardarArbol();
    }
    
    /**
     * Este metodo busca una mascota mediante la ID. Tiene relacion directa con el metodo anterior, esto para eliminar el registro de una mascota en el arbol para la GUI.
     */
    public NodoMascotaABB buscarMascotaArbol(int idArbol){
        return arbol.buscarMascotaGui(idArbol);
    }

    /**
     * Este metodo verifica si el arbol esta incialmente vacio. Esto para el metodo de eliminar el registro para la GUI
     */
    public boolean arbolVacio(){
        if(arbol.estaVacio()){
            return true;
        }
        return false; 
    }
    
    //Los dos siguientes metodos se encargan de funcionar como el manejo de los archivos con respecto a las mascotas que guardamos en el arbol.

    /**
     * Este metodo fijo, se encarga de guardar la mascota en archivo, ademas atrapa a todos los errores posibles mediante bloque try-catch.
     * @param mascota la mascota que estamos guardando en archivo.
     */

    private void guardarEnArchivo(Mascota mascota){
        try(PrintWriter pw =  new PrintWriter(new FileWriter(archivo, true))){
            pw.println(mascota.getId() + "," + mascota.getNombre() + "," + mascota.getEspecie());
        }catch(IOException e){
            System.err.println("Error al guardar mascota: " +  e.getMessage());
        }
    }

    /**
     * Este metodo fijo, se encarga de cargar el archivo cada vez que abrimos el programa.
     * Sl programa se detiene si el archivo no existe. Si existe, se encarga de leer e imprimir cada mascota registrada en el historial.
     */
    private void cargarArchivo(){
        File archivo = new File("mascotas.txt");
        if(!archivo.exists()){
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))){
            String lineas;
            while ((lineas = reader.readLine()) != null) {
                String[] partes = lineas.split(",");
                if(partes.length == 3){
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String especie = partes [2];
                    Mascota mascota  = new Mascota(id, nombre, especie);
                    arbol.insertarGui(mascota);
                }
            }
        }catch(IOException e){
            System.err.println("Error al cargar historial:" + e.getMessage());
        }
    }

    /**
     * Este tercer metodo se encarga de guardar todo el arbol en el archivo, sobreescribiendo los datos ya existentes, lo cual nos ayuda por si se decide eliminar una mascota del registro.
     */
    public void guardarArbol(){
        try(PrintWriter impresor = new PrintWriter(new FileWriter(archivo, false))){
            guardarRecursivo(arbol.getRaiz(), impresor);
        }  catch(IOException e){
            System.err.println("Error al guardar historial: " + e.getMessage());
        }
    }

    /**
     * Este cuarto y ultimo metodo para leer archivos se encarga de guardar de manera recursiva todo el arbol en el archivo de texto.
     * @param nodo los nodos del arbol
     * @param impresor el PrintWriter que se encarga de que se imprima todo en archivo.
     */
     private void guardarRecursivo(NodoMascotaABB nodo, PrintWriter impresor){
        if(nodo != null){
            guardarRecursivo(nodo.getIzquierdo(), impresor);
            Mascota mascotaDato = nodo.getDatoMascota();
            impresor.println(mascotaDato.getId() + "," + mascotaDato.getNombre() + "," + mascotaDato.getEspecie());
            guardarRecursivo(nodo.getDerecho(), impresor);
        }
     }
}