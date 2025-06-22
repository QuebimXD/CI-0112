import javax.swing. *;

public class InterfazGrafica extends JFrame{
    private Veterinario veterinario = new Veterinario();


    //El constructor donde va todito
    public InterfazGrafica(){
        setTitle("Clinica Veterinaria");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        //PANEL PRINCIPAL

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(3, 2));

        //PANEL DE DATOS

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));

        //LO Q ESTA CONTENIDO EN EL PANELDATOS

        JLabel eNombre = new JLabel("Nombre: ");
        JTextField cNombre = new JTextField(15);
        JLabel eId = new JLabel("Id: ");
        JTextField cId = new JTextField(15);
        JLabel eEspecie = new JLabel("Especie: ");
        JTextField cEspecie = new JTextField(15);
        
        //BOTON PARA REGISTRAR MASCOTAS
        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(e -> {

        //Agregamos los datos

        String nombre = cNombre.getText();
        String idTexto = cId.getText();
        String especie = cEspecie.getText();

            //Verificamos que no esten vacios y que el id no contenga letras.

            if(nombre.isEmpty() || idTexto.isEmpty() || especie.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los datos de la mascota a registrar.",  "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; //Paramos el codigo, para que no nos salga el otro mensaje de error.
            }
            try{
                int id = Integer.parseInt(idTexto);
                Mascota mascotaRegistrada = veterinario.registrar(nombre, id, especie);
                JOptionPane.showMessageDialog(null, "Datos guardados exitosamente.", "Mensahe", JOptionPane.INFORMATION_MESSAGE);

            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Rellene el id con numeros.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            
            
        });

        //Agregamos los elementos al panelDatos
        panelDatos.add(eNombre);
        panelDatos.add(cNombre);
        panelDatos.add(eId);
        panelDatos.add(cId);
        panelDatos.add(eEspecie);
        panelDatos.add(cEspecie);
        panelDatos.add(botonRegistrar);

        //PANEL DE LA COLA

        JPanel panelCola = new JPanel();
        panelCola.setLayout(new BoxLayout(panelCola, BoxLayout.Y_AXIS));

        String textoCola = veterinario.mostrarColaa();
        JTextArea areaTexto = new JTextArea(textoCola);
        areaTexto.setEditable(false);
        JScrollPane larry = new JScrollPane(areaTexto); 


        //Agregamos los elementos al panel de la cola
        panelCola.add(larry);

        
        
        //PANEL SMDSAKDMNAKSDMKA

        //Se agregan todos los paneles secundarios al principal
        panelPrincipal.add(panelDatos);
        panelPrincipal.add(panelCola);

        //Se agrega finalmente el panelPrincipal
        add(panelPrincipal);

    }  

    public static void main(String[] args){
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setVisible(true);
    }
}