public class Mascota{
    private int id;
    private String nombre;
    private String especie;

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