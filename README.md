# CI-0112
Repositorio de Programación 1

En este bloc de notas se propone, mediante un sistema de fechas, una breve documentación de avances/comentarios que tenga con los proyectos que sean subidos a este mismo repositorio. De esta manera, se le facilita al profesor comprender en qué fecha exacta fue que se creó/modificó un proyecto/código.

27 de abril del 2025
Se trabaja el laboratorio #2. La primera parte consistió en instalar el Git y registrar un usuario en el GitHub. Afortunadamente, hace tiempos ya había creado uno en GitHub, solo tocaba instalar el Git, lo cual no fue muy difícil, solo tuve limitaciones con el idioma, pero al final logré instalarlo.
Posteriormente, accedí desde mi CMD al repositorio local, y aquí estoy editando el README.md, para luego, mediante los comandos que dejó el profe, subir mi nuevo README al repositorio.
Además, he logrado terminar el laboratorio numero 2, aquí mis principales comentarios

1-Tuve complicaciones para entender si inicialmente se tenía o no que incluir la clase Scanner, pero eventualmente comprendí que sí era indispensable; así el usuario podría digitar desde la terminal la cantidad de robots, y la información de cada atributo del mismo.

2- Con respecto a la clase Robot
	
	2.a- Tuve que recordar el orden de cómo declarar los atributos de la Robot
	private / Constructor / getters / setters / métodos
	
	2.b - para incluir el mecanismo de defensa, añadi el int daño hecho, que le resta los puntos de daño a los	puntos de defensa, en el método atacar:

		int danoHecho = this.puntosDano - otroRobot.getPuntosDefensa();
	
3- Con respeto a JuegoBatalla, aquí algunos detalles que debo de comentar, de acuerdo al desempeño de este lab:
	
	3.a - lo primero, fue entender que puedo declarar un array de clase; tipo, un array de la clase Robot:
		
		private Robot[] robots;

	3.b- añadí que, si la cantidad de robots fuese mayor a 10, se agregaran 10 robots por default en el scanner:

		cantRobots = 10; //Se asignan 10 por exceder el limite

	3.c- añadí saltos de linea posterior a cada scanner con entero, para evitar que se pegase el texto

		scanner.nextLine(); //Limpiamos las lineas de entrada de la terminall

	3.d- añadi un método que me ayude a contar a los robots vivos antes de la batalla, así puedo aplicar un while con la variable robotsVivos

	3.e- utilicé Math.random() para poder elegir aleatoriamente un robot, el cual sería atacado. Luego, llamo a la función de atacar de la clase Robot, para que el robotAtacante, haga la acción.

	3.f- finalmente, llamo al main, para que se apliquen las funciones definidas en la clase JuegoBatalla.