import javax.swing. *;
import javax.swing.JSpinner.NumberEditor;

public class InterfazGrafica extends JFrame{
    private Veterinario veterinario = new Veterinario();
    private JTextArea areaTextoCola;
    private JTextArea areaTextoArbol; 

    //El constructor donde va todito
    public InterfazGrafica(){
        setTitle("Clinica Veterinaria");
        setSize(500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        //PANEL PRINCIPAL

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

        //PANEL DE DATOS

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));

        //LO Q ESTA CONTENIDO EN EL PANELDATOS

        JLabel eNombre = new JLabel("Nombre: ");
        JTextField cNombre = new JTextField(15);
        JLabel eEspecie = new JLabel("Especie: ");
        JTextField cEspecie = new JTextField(15);
        
        //BOTON PARA REGISTRAR MASCOTAS
        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(e -> {

            //Agregamos los datos

            String nombre = cNombre.getText();
            String especie = cEspecie.getText();

            //Verificamos que no esten vacios y que el id no contenga letras.

            if(nombre.isEmpty() || especie.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los datos de la mascota a registrar.",  "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; //Paramos el codigo, para que no nos salga el siguiente mensaje de advertencia.
            }
            
            if(tieneEntero(especie)){
                JOptionPane.showMessageDialog(null, "Por favor, recuerde que la especie no puede contener numeros.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; 
            }

            try{
                Mascota mascotaRegistrada = veterinario.registrar(nombre, id, especie);
                

                if (mascotaRegistrada != null) {
                    JOptionPane.showMessageDialog(null, "Datos guardados exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    areaTextoCola.setText(veterinario.mostrarColaa());
                } else {
                    JOptionPane.showMessageDialog(null, "Ya existe una mascota con ese ID.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }


            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Error en registrar a la mascota.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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

        JLabel tituloCola = new JLabel("Cola de Mascotas:");
        areaTextoCola = new JTextArea(10, 30);
        areaTextoCola.setEditable(false);
        areaTextoCola.setLineWrap(true);
        areaTextoCola.setWrapStyleWord(true);
        areaTextoCola.setText(veterinario.mostrarColaa());

        // Scroll
        JScrollPane scrollCola = new JScrollPane(areaTextoCola);
        scrollCola.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollCola.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JButton botonAtender = new JButton("Atender Mascota");
        botonAtender.addActionListener(e -> {
        Mascota atendida = veterinario.atender();
        if (atendida != null) {
            JOptionPane.showMessageDialog(null, "Se atendio a: " + atendida.getNombre(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No hay mascotas en la cola", "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
        // Actualizar la vista de la cola
        areaTextoCola.setText(veterinario.mostrarColaa());
        });

        //Agregamos los elementos al panel de la cola
        panelCola.add(tituloCola);
        panelCola.add(scrollCola);
        panelCola.add(botonAtender);   
        
        //PANEL ARBOL

        JPanel panelArbolHistorial = new JPanel();
        panelArbolHistorial.setLayout(new BoxLayout(panelArbolHistorial, BoxLayout.Y_AXIS));

        JLabel etiquetaHistorial = new JLabel("Historial (Recorrido Inorden):");
        areaTextoArbol = new JTextArea(10, 30);
        areaTextoArbol.setEditable(false);
        areaTextoArbol.setText(veterinario.verHistoria());

        JScrollPane scrollArbol = new JScrollPane(areaTextoArbol);
        scrollArbol.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollArbol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        // Botón para actualizar el historial
        JButton botonVerHistorial = new JButton("Ver Historial");
        botonVerHistorial.addActionListener(e -> {
            String historial = veterinario.verHistoria();
            areaTextoArbol.setText(historial);
        });

        JButton botonEliminarRegistro = new JButton("Elimiar Registro");
        botonEliminarRegistro.addActionListener(e ->{
            if(veterinario.arbolVacio()){
                return; 
            }

            
            String mascotaBuscadaId = JOptionPane.showInputDialog(null, "Ingrese el Id de la mascota que desea eliminar del registro", "Eliminar mascota del registro", JOptionPane.QUESTION_MESSAGE);
            if(mascotaBuscadaId == null){
                return; 
            }
            if(mascotaBuscadaId.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "No se ingreso ningun ID", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; 
            }
            try{
                int idIngresado = Integer.parseInt(mascotaBuscadaId);

                NodoMascotaABB nodoDeIdBuscado = veterinario.buscarMascotaArbol(idIngresado);
                if(nodoDeIdBuscado == null){
                    JOptionPane.showMessageDialog(null, "No existe una mascota con ese id", "Incongruencia", JOptionPane.ERROR_MESSAGE);
                    return;  
                }

                int confirmacionElimMascota = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas eliminar el registro de tu mascota de: "+ nodoDeIdBuscado.getDatoMascota().getNombre() +"(ID: " + idIngresado + ")?", "Confirmacion", JOptionPane.YES_NO_OPTION);
                if(confirmacionElimMascota == JOptionPane.YES_OPTION){
                    veterinario.eliminarMascota(nodoDeIdBuscado.getDatoMascota());
                    areaTextoArbol.setText(veterinario.verHistoria());
                    JOptionPane.showMessageDialog(null, "Su mascota fue eliminada del registro de forma exitosa", "Perfecto!!!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Por favor ingrese un ID relleno de numeros, valido", "Incongruencia", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Agregar todo al panelHistorial

        panelArbolHistorial.add(etiquetaHistorial);
        panelArbolHistorial.add(scrollArbol);
        panelArbolHistorial.add(botonVerHistorial);
        panelArbolHistorial.add(botonEliminarRegistro);

        //Se agregan todos los paneles secundarios al principal

        panelPrincipal.add(panelDatos);
        panelPrincipal.add(panelArbolHistorial);
        panelPrincipal.add(panelCola);

        //Se agrega finalmente el panelPrincipal
        add(panelPrincipal);

    }  

    /**
     * Este metodo verifica si un texto contiene numeros enteros.
     * @return true si los contiene, false si no.
     */

    public boolean tieneEntero(String texto){
        try{
            Integer.parseInt(texto);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public static void main(String[] args){
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setVisible(true);
    }

    
}