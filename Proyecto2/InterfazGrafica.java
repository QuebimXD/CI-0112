import javax.swing. *;

public class InterfazGrafica extends JFrame{
    private ColaMascota cola = new ColaMascota();

    public InterfazGrafica(){
        setTitle("Clinica Veterinaria");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

    }

    public static void main(String[] args){
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setVisible(true);
    }
}