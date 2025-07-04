# Proyecto 2: Clínica Veterinaria

## Instrucciones básicas

Posterior a la ejecución de `InterfazGrafica.java`, se mostrarán tres espacios principales:

1. **Registro:** donde podrá asignar datos a las mascotas (Nombre y Especie).
2. **Historial de Mascotas** donde podra ver las mascotas registradas.
3. **Cola de Mascotas** donde podra ver la fila de atencion de las mascotas. 

Piense en este sistema como un registro hospitalario. Tal que, primero registra a su mascota, y luego puede colocarla en la fila de atención (la cola).

---

## Registro

- **Nombre:** Espacio donde el usuario puede digitar el nombre de su mascota.
- **Especie:** Espacio para escribir la especie de la mascota.

> **Importante:**  
> La **especie no debe contener números**.  
> Para registrar una mascota, ambos campos deben estar correctamente llenos.  
> Luego, dé clic en el botón **Registrar**.  
> La mascota se añadirá al **Historial de Mascotas**.

---

## Historial de Mascotas

Aquí se visualizarán las mascotas registradas, ordenadas automáticamente por su **Id** usando un recorrido **inorden**. Este se trabaja de forma interna en el codigo.

- Puede eliminar una mascota del historial usando el botón **Eliminar Registro**.
- Para ello, debe ingresar el **Id** tal como aparece en el historial.
- Si el Id no esta en el historial, no podrá eliminar la mascota.

> Una vez eliminada la mascota, esta desaparecerá del historial.

---

## Cola de Mascotas

Este espacio muestra la fila de mascotas que esperan ser atendidas, usando un orden **FIFO** (primero en entrar, primero en salir), como en una fila de banco.

### Botones de Cola de Mascotas:

- **Agregar a la Cola:**  
  Permite agregar una mascota del historial a la cola de espera.  
  > Ingrese el **Id** tal y como aparece en el historial.  
  > Si el Id no esta en el historial, no se agregará.

- **Atender:**  
  Atiende a la primera mascota en la cola y la elimina de la fila.  
  > Si no hay mascotas en espera, el boton no realizara nada.

---

Y sin más preámbulo, mucha suerte con la interfaz :3
