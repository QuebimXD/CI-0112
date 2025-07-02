import javax.swing.*;
import java.awt.*;
 
public class InterfazGrafica extends JFrame{
    private Veterinario veterinario = new Veterinario();
    private JTextArea areaTextoCola;
    private JTextArea areaTextoArbol; 
    Color fondoLineas = new Color(230, 240, 240); //Color gris
    Color fondoPaneles = new Color(174, 198, 207); //Color Azul pasel

    //El constructor donde va todo el asunto.
    public InterfazGrafica(){ 
        setTitle("Clinica Veterinaria");
        setSize(1200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        getContentPane().setBackground(fondoLineas); //Metodo para agregarle el color a la ventana principal.

        //PANEL PRINCIPAL (De manera horizontal)

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); //Esto le agrega espacio vacio a los bordes de cada panel
        panelPrincipal.setBackground(fondoPaneles); //Le ponemos el color azul al panel principal

        //PANEL DE DATOS

        JPanel panelDatos = new JPanel();
        panelDatos.setBackground(fondoPaneles); //Color azul al panel de los datos
        panelDatos.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); //Esto hace que los campos de texto sean mas reducidos.
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));  //Los elementos de este panel se ordenan de manera vertical.
        panelDatos.setBorder(BorderFactory.createTitledBorder("Registro")); //Este metodo nos permite agregarle un titulo al panel.

        //LO QUE ESTA CONTENIDO EN EL PANELDATOS

        JLabel eNombre = new JLabel("Nombre: ");
        JTextField cNombre = new JTextField(15);

        JLabel eEspecie = new JLabel("Especie: ");
        JTextField cEspecie = new JTextField(20);

        //BOTON PARA REGISTRAR MASCOTAS
        JButton botonRegistrar = new JButton("Registrar");

        //Logica del boton registrar

        botonRegistrar.addActionListener(e -> {

            //Agregamos los datos

            String nombre = cNombre.getText();
            String especie = cEspecie.getText();

            //Verificamos que los espacios no esten vacios y que la especie no contenga ningun numero.

            if(nombre.isEmpty() || especie.isEmpty()){
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los datos de la mascota a registrar.",  "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; //Paramos el codigo, para que no nos salga el siguiente mensaje de advertencia.
            }
            
            if(tieneEntero(especie)){
                JOptionPane.showMessageDialog(null, "Por favor, recuerde que la especie no puede contener numeros.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;  //Volvemos a parar el codigo
            }

            try{ //Try - catch para evitar caer en errores.

                Mascota mascotaRegistrada = veterinario.registrar(nombre, especie); //Registramos a la mascota.

                if (mascotaRegistrada != null) { //Si logramos agregar la mascota al arbol:
                    JOptionPane.showMessageDialog(null, "Datos guardados exitosamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                    areaTextoArbol.setText(veterinario.verHistoria()); //Se actualiza el gistorial
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Error en registrar a la mascota.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            
        });

        //Agregamos los elementos al panelDatos
        panelDatos.add(eNombre);
        panelDatos.add(cNombre);
        panelDatos.add(Box.createVerticalStrut(10)); //Esto crea un espacio vacio entre las etiquetas y los campos
        panelDatos.add(eEspecie);
        panelDatos.add(cEspecie);
        panelDatos.add(Box.createVerticalStrut(10)); //Espacio vacio entre los elementos anteriores y botonRegistrar
        panelDatos.add(botonRegistrar);
        

        //PANEL DE LA COLA

        JPanel panelCola = new JPanel();
        panelCola.setBackground(fondoPaneles); //Color al panelCola
        panelCola.setLayout(new BoxLayout(panelCola, BoxLayout.Y_AXIS));
        panelCola.setBorder(BorderFactory.createTitledBorder("Cola de Mascotas")); //Creamos un titulo para el panel
       

        areaTextoCola = new JTextArea(10, 25);
        areaTextoCola.setEditable(false); //Que no se pueda escribir sobre esta area de texto
        areaTextoCola.setLineWrap(true); //Ajusta automaticamente el texto en el campo de la cola; si el texto excede el campo, se alinea al siguiente reglon.
        areaTextoCola.setWrapStyleWord(true); //El ajuste se hace mediante palabras completas, por cuestion estetica.
        areaTextoCola.setText(veterinario.mostrarColaa()); //Se muestra la cola
        JScrollPane scrollCola = new JScrollPane(areaTextoCola); //barra para scrollear en la cola
      
        //PANEL PARA ALINEAR BOTONES A LA IZQUIERDA

        JPanel botonesCola = new JPanel(new FlowLayout(FlowLayout.LEFT));
        botonesCola.setBackground(fondoPaneles); //Color al panel de los botones.
        //BOTON PARA ENCOLAR MASCOTAS

        JButton botonEnqueue = new JButton("Agregar a la Cola");
        botonEnqueue.addActionListener(e -> {

            //No hay macotas registradas.
            if(veterinario.arbolVacio()){
                JOptionPane.showMessageDialog(null, "No hay mascotas registradas", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //Mensaje para pedirle el id al usuario de la mascota por encolar.
            String idTexto = JOptionPane.showInputDialog(null, "Ingrese el ID de la mascota para agregar a la cola :", "Agregar Mascota a la Cola", JOptionPane.QUESTION_MESSAGE);
            if(idTexto == null){ //Si el usuario le da a cancel en el input.
                return;
            }
            idTexto= idTexto.trim(); //Quitamos espacios para comparar ID con los del arbol.
            
            if(idTexto.isEmpty()){ //No pone nada y le da OK.
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            //No agrega ints.
            if(!idTexto.matches("\\d+")){
                JOptionPane.showMessageDialog(null, "El ID solo puede contener numeros", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //Aseguramos que el ID de la mascota sea un int.
            int idMascota = Integer.parseInt(idTexto);
            boolean ingresada = veterinario.ingresarCola(idMascota);

            //Se logra encolar a la mascota.
            if(ingresada){
                JOptionPane.showMessageDialog(null, "Mascota registrada exitosamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                areaTextoCola.setText(veterinario.mostrarColaa()); //Se actualiza la cola.
            }else{

                NodoMascotaABB nodo = veterinario.buscarMascotaArbol(idMascota);
                //Busca un ID de una mascota no registrada.
                if(nodo == null){
                    JOptionPane.showMessageDialog(null, "No existe una mascota con este ID", "Error", JOptionPane.ERROR_MESSAGE);
                //Busca un ID de una mascota ya encolada.
                }else{
                    JOptionPane.showMessageDialog(null, "La mascota ya se encuentra en la cola", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        //Boton para atender a las mascotas

        JButton botonAtender = new JButton("Atender Mascota");
        botonAtender.addActionListener(e -> {
        

            Mascota atendida = veterinario.atender();
            //Se logra atender/desencolar.
            if (atendida != null) {
                JOptionPane.showMessageDialog(null, "Se atendio a: " + atendida.getNombre(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            } else { //La cola esta vacia
                JOptionPane.showMessageDialog(null, "No hay mascotas en la cola", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            // Actualizar la vista de la cola
            areaTextoCola.setText(veterinario.mostrarColaa());
        
        });

        //Agregamos los botones al panel de los botones, para que se alineen de izquierda a derecha.
        botonesCola.add(botonEnqueue);
        botonesCola.add(botonAtender);

        //Aregamos los botones y el area de texto junto al scroll al panel de la cola.
        panelCola.add(scrollCola);
        panelCola.add(Box.createVerticalStrut(10)); //Espacio vacio entre el area de texto y los botones.
        panelCola.add(botonesCola);

      
        //PANEL ARBOL

        JPanel panelArbolHistorial = new JPanel();

        panelArbolHistorial.setBackground(fondoPaneles); //Color celeste al panel del arbol.

        panelArbolHistorial.setLayout(new BoxLayout(panelArbolHistorial, BoxLayout.Y_AXIS)); //Los elementos se ordenan de manera vertical.
        panelArbolHistorial.setBorder(BorderFactory.createTitledBorder("Historial de Mascotas (InOrder)"));//Agregarle el titulo del historial al arbol.
 
        areaTextoArbol = new JTextArea(10, 30); 
        areaTextoArbol.setEditable(false); //Que el campo del historial no se pueda editar.
        areaTextoArbol.setText(veterinario.verHistoria()); //Se muestra el historial, inicialmente vacio.
        JScrollPane scrollArbol = new JScrollPane(areaTextoArbol); //Barra para scrollear.

        //Logica del boton para eliminar el registro.

        JButton botonEliminarRegistro = new JButton("Eliminar Registro");
        botonEliminarRegistro.addActionListener(e ->{

            //Arbol vacio, no mascotas registradas.
            if(veterinario.arbolVacio()){
                JOptionPane.showMessageDialog(null, "No se encuentran mascotas registradas", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; 
            }
            
            //Mensaje para agregar el id de un mascota a eliminar en el registro.
            String mascotaBuscadaId = JOptionPane.showInputDialog(null, "Ingrese el Id de la mascota que desea eliminar del registro", "Eliminar mascota del registro", JOptionPane.QUESTION_MESSAGE);
            if(mascotaBuscadaId == null){ //Le da cancel.
                return; 
            }
            //No ingreso ninguna ID
            if(mascotaBuscadaId.trim().isEmpty()){
                JOptionPane.showMessageDialog(null, "No se ingreso ningun ID", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return; 
            }
            try{
                int idIngresado = Integer.parseInt(mascotaBuscadaId); //Se verifica que el id sean numeros

                //Se busca la mascota por id, con el metodo de Veterinario
                NodoMascotaABB nodoDeIdBuscado = veterinario.buscarMascotaArbol(idIngresado);

                //Se busca un ID de una mascota no registrada
                if(nodoDeIdBuscado == null){
                    JOptionPane.showMessageDialog(null, "No existe una mascota con ese id", "Incongruencia", JOptionPane.ERROR_MESSAGE);
                    return;  
                }

                //Verifica si el usuario realmente quiere eliminar a esa mascota del registro.
                int confirmacionElimMascota = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas eliminar el registro de tu mascota de: "+ nodoDeIdBuscado.getDatoMascota().getNombre() +"(ID: " + idIngresado + ")?", "Confirmacion", JOptionPane.YES_NO_OPTION);

                //Le da que si
                if(confirmacionElimMascota == JOptionPane.YES_OPTION){
                    veterinario.eliminarMascota(nodoDeIdBuscado.getDatoMascota());
                    areaTextoArbol.setText(veterinario.verHistoria()); //Se actualiza el historial.
                    JOptionPane.showMessageDialog(null, "Su mascota fue eliminada del registro de forma exitosa", "Perfecto!!!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(NumberFormatException ex){ //Si agrega un ID que no contenga numeros.
                JOptionPane.showMessageDialog(null,"Por favor ingrese un ID relleno de numeros, valido", "Incongruencia", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Agregamos el campo de texto con la barrea de scrollear, y el boton de eliminar el registro al panel del arbol.

        panelArbolHistorial.add(scrollArbol);
        panelArbolHistorial.add(Box.createVerticalStrut(10)); //Espacio vacio para separar scroll y boton.
        panelArbolHistorial.add(botonEliminarRegistro);
    
        //Se agregan todos los paneles secundarios al principal

        panelPrincipal.add(panelDatos);
        panelPrincipal.add(Box.createHorizontalStrut(20)); //Espacio para separar.
        panelPrincipal.add(panelArbolHistorial);
        panelPrincipal.add(Box.createHorizontalStrut(20)); //Espacio para separar
        panelPrincipal.add(panelCola);

        //Se agrega finalmente el panelPrincipal al la ventana de la GUI

        add(panelPrincipal);

    }  

    /**
     * Este metodo verifica si un texto contiene numeros enteros.
     * @return true si los contiene, false si no.
     */

    public boolean tieneEntero(String texto){
        for(char c : texto.toCharArray()){
            if(Character.isDigit(c)){
                return true;
            }
        }return false;
    }

    /**
     * El main, que corre el programa.
     */
    public static void main(String[] args){
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setVisible(true);
    }

    
}