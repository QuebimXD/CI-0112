import java.util.Scanner;
public class JuegoBatalla{
    //Declaramos el array que tendrá los robots
    private Robot[] robots;

    //Aquí, vamos a meter la cantidad de robots que el usuario desee (Max 10)
    public void iniciarRobots() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese cuantos robots desea ingresar a la batalla, con un maximo de 10");
        int cantRobots = scanner.nextInt();
        scanner.nextLine(); //Limpiamos las lineas de entrada de la terminall
        if (cantRobots > 10){
            System.out.println("La cantidad de robots no puede ser mayor a 10, asi que se asignaran 10");
            System.out.println(" "); //Espacio pa q se vea lindoo
            cantRobots = 10; //Se asignan 10 por exceder el limite
        }
        robots = new Robot[cantRobots]; //Aquí agregamos la cantidad de robots al array

        for (int i = 0; i < cantRobots; i++) {

            //Nombre
            System.out.println("Ingrese el nombre del robot: ");
            String nombre = scanner.nextLine();

            //Puntos de vida
            System.out.println("Ingrese los puntos de vida del robot, deben de estar entre 50 a 100: ");
            int puntosVida = scanner.nextInt();
            scanner.nextLine(); //Limpiamos las lineas de entrada de la terminall


            if(puntosVida < 50){
                System.out.println("Los puntos de vida deben de estar entre 50 a 100, se asignaran 50");
                System.out.println(" "); //Espacio pa q se vea lindoo
                puntosVida = 50; //Se asignan 50 por bajar el limite
            }else if (puntosVida > 100){
                System.out.println("Los puntos de vida deben de estar entre 50 a 100, se asignaran 100");
                System.out.println(" "); //Espacio pa q se vea lindoo
                puntosVida = 100; //Se asignan 100 por exceder el limite
            }
            
            //Puntos de daño
            System.out.println("Ingrese los puntos de dano del robot, debe de tener entre 10 a 20 puntos: ");
            int puntosDano = scanner.nextInt();
            scanner.nextLine(); //Limpiamos las lineas de entrada de la terminall

            if (puntosDano < 10){
                System.out.println("Los puntos de dano deben de estar entre 10 a 20, se asignaran 10");
                System.out.println(" "); //Espacio pa q se vea lindoo
                puntosDano = 10; //Se asignan 10 por bajar el limite
            }else if (puntosDano > 20){
                System.out.println("Los puntos de dano deben de estar entre 10 a 20, se asignaran 20");
                System.out.println(" "); //Espacio pa q se vea lindoo
                puntosDano = 20; //Se asignan 20 por exceder el limite   
            }

            //Puntos defensa
            System.out.println("Ingrese los puntos de defensa del robot, con un maximo de 10: ");
            int puntosDefensa = scanner.nextInt();
            scanner.nextLine(); //Limpiamos las lineas de entrada de la terminall

            if (puntosDefensa > 10){
                System.out.println("Los puntos de defensa no pueden ser mayores a 10, se asignaran 10");
                System.out.println(" "); //Espacio pa q se vea lindoo
                puntosDefensa = 10; //Se asignan 10 por exceder el limite
            }else if(puntosDefensa < 0){
                System.out.println("Los puntos de defensa no pueden ser menores a 0, se asignaran 0");
                System.out.println(" "); //Espacio pa q se vea lindoo
                puntosDefensa = 0; //Se asignan 0 por bajar el limite   
            }
            
            //Aquí se crea el objeto Robot y se agrega al array
            robots[i] = new Robot(nombre, puntosVida, puntosDano, puntosDefensa);
            System.out.println("Robot " + (i + 1) + " creado: Su nombre es " + robots[i].getNombre() + ", este tiene: " + robots[i].getPuntosVida() + " puntos de vida, " + robots[i].getPuntosDano() + " puntos de dano y " + robots[i].getPuntosDefensa() + " puntos de defensa.");
        }
        scanner.close();
    }
    
    //Contar los robots vivos para efectos de iniciar la batalla
    private int robotsVivos(){
        int vivos = 0;
        for (Robot robot : robots){
            if (robot.estaVivo()){
                vivos++; //Contamos los robots vivos
            }
        }
        return vivos;
    }
  
    //Iniciar la batalla
    public void iniciarBatalla(){
        System.out.println("Inicia la batalla!");

        //Aquí se hace un bucle, donde mientras haya un robot vivo, se ataquen entre ellos
        while(robotsVivos() > 1){
            for (int i = 0; i < robots.length; i++){
                if (robots[i].estaVivo()){ //Verificamos que el robot esté vivo, para que ataque

                    //Robot ataca a otro robot al azar
                    int robotAtacante = i; //del ciclo for
                    int robotAtacado; //Definimos al robot atacado
                    do{
                        robotAtacado = (int) (Math.random() * robots.length); //Generamos un numero random en el array de robots, asi se elige un robot al azar
                    } while (robotAtacante == robotAtacado || !robots[robotAtacado].estaVivo()); //es necesario que no ataque a si mismo y que el robot atacado esté vivo  
                    
                    //El atacante, ataca al robot atacado

                    robots[robotAtacante].atacar(robots[robotAtacado]); //utilizamos el metodo atacar de la clase Robot, para que "i" ataque al atacado

                    

                    
                    

                    
                    
                    
                } 
            }
        }

        System.out.println("Finaliza la batalla!"); //Cuando solo quede un robot vivo, se termina el pleito
    }

    //Robot ganador
    public void robotGanador(){
        for (Robot robot : robots){
            if (robot.estaVivo()){
                System.out.println("El robot ganador es: " + robot.getNombre());
            }
        }
    }

    //Finalmente, el main donde se ejecuta el juego
    public static void main(String[] args){
        JuegoBatalla juego = new JuegoBatalla(); //Definimos el objeto juego, que sera la partida
        
        juego.iniciarRobots(); //Que se definan en la terminal de acuerdo al gusto del usuario
        juego.iniciarBatalla(); //inicia el pleito
        juego.robotGanador(); //Se muestra el último robot vivo, que es el ganador
    }
    
}

