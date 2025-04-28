public class Robot{
    
    // Atributos de Robot
    private String nombre;
    private int puntosVida;
    private int puntosDano;
    private int puntosDefensa;

    //Constructor 
    public Robot(String nombre, int puntosVida, int puntosDano, int puntosDefensa) {
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.puntosDano = puntosDano;
        this.puntosDefensa = puntosDefensa;
    }

    //Getters
    public String getNombre(){
        return nombre;
    }
    public int getPuntosVida(){
        return puntosVida;
    }
    public int getPuntosDano(){
        return puntosDano;
    }
    public int getPuntosDefensa(){
        return puntosDefensa;
    }

    //Setters
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setPuntosVida(int puntosVida){
        this.puntosVida = puntosVida;
    }
    public void setPuntosDano(int puntosDano){
        this.puntosDano = puntosDano;
    }
    public void setPuntosDefensa(int puntosDefensa){
        this.puntosDefensa = puntosDefensa;
    }

    //Métodos

    //Atacar a otro robot
    public void atacar(Robot otroRobot){
        //Tomar en cuenta los puntosDefensa
        int danoHecho = this.puntosDano - otroRobot.getPuntosDefensa();

        //Calcular nueva vida del robot atacado
        int nuevaVida = otroRobot.getPuntosVida() - danoHecho;
        if (nuevaVida < 0){
            nuevaVida = 0; //No puede haber vida negativa
        }

        //Actualizar vida del robot atacado
        otroRobot.setPuntosVida(nuevaVida);

        //Mostrar ataque
        System.out.println(this.nombre + " ataca a " + otroRobot.getNombre() + " y le hace " + danoHecho + " de dano. La vida de " + otroRobot.getNombre() + " ahora es: " + otroRobot.getPuntosVida());
    }

    //Verificar si el robot esta vivo
    public boolean estaVivo(){
        return this.puntosVida > 0;
    }
}