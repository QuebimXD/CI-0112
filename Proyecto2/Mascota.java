public class Mascota{
    private int id;
    private String nombre;
    private String especie;
    /**
     * Este es el constructor de la mascota, es decir, el metodo que estancia el contenido del objeto mascota. 
     * @param id Basicamente es el id que se le asignara aleatoriamente a cada mascota registrada. 
     * @param nombre Este es el nombre de las mascotas registradas por la gui.
     * @param especie Este es un atributo adicional que le colocamos, que designa la especie de cada una de las mascotas para que no se viera tan simple el programa :).
     */
    public Mascota(int id, String nombre, String especie){
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
    }
    
    /**
     * Estos son los setters y getters encargados de poder retornar y asignan datos con respecto a los atributos de las mascotas, clasicos ya conocidos.
     */
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getEspecie(){
        return especie;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setEspecie(String especie){
        this.especie = especie;
    }
}