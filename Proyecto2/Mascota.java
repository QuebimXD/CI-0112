public class Mascota{
    /**
     * Esta clase esta compuesta por tres referencias: la id de la mascota, el nombre y su especie.
     */
    private int id;
    private String nombre;
    private String especie;

    /**
     * Con el constructor se crea una nueva mascota.
     * @param id con la id de cada mascota, la cual es unica para cada una.
     * @param nombre que define el nombre de la mascota. Este, evidentemente se puede repetir.
     * @param especie contiene la especie de cada mascota. Es comun que las especies se repitan reiteradas veces; pueden llegar muchos perros, gatos o hamsters a la Veterinaria.
     */
    public Mascota(int id, String nombre, String especie){
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
    }
    
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getEspecie(){
        return especie;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setEspecie(String especie){
        this.especie = especie;
    }
}