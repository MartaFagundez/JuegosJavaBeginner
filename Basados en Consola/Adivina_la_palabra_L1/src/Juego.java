import java.text.Normalizer;
import java.util.Scanner;

public class Juego {

	public static void main(String[] args) {
		
		// Se crea un array con una serie de palabras
		String[] palabrasOriginal = { "VARIABLE", "BOOLEANO", "ENTERO", "PROCEDIMIENTO", "FUNCIÓN", "MÉTODO", "CLASE",
				"OBJETO", "STRING", "FLOAT", "HERENCIA", "ENCAPSULAMIENTO", "ABSTRACCIÓN", "POLIMORFISMO", "ARRAY",
				"ATRIBUTOS", "SINTAXIS", "SEMÁNTICA", "SENTENCIA", "BUCLE", "CONDICIONAL", "EXPRESIÓN", "ASIGNACIÓN",
				"OPERADORES", "ACUMULADOR", "CONTADOR", "DECLARACIÓN", "JAVA", "DOUBLE" };
		
		// Se elige una palabra al azar (del array original) y se guarda como palabra a ser adivinada
		String palabra = palabrasOriginal[(int) Math.floor(Math.random() * palabrasOriginal.length)];
		
		// Se genera la palabra que va a ser mostrada, sustituyendo todo caracter de la palabra por un asterisco
		String palabraMostrada = palabra.replaceAll(".", "*");
		
		// Mensaje de la "pantalla" inicial del juego
		String mensaje1 = "                  ¡ADIVINA LA PALABRA!                    ";
		String mensaje2 = " Pista: las palabras están relacionadas a la programación ";
		
		// Se declaran/ inicializan las variables
		String letraElegida;
		String letrasUtilizadas = "";
		int intentos = 10;
		int aciertos = 0;
		int puntos = 0;

		// Mientras el usuario tenga intentos disponibles:
		while (intentos > 0) {

			Scanner input = new Scanner(System.in);

			// Mientras la palabra no sea adivinada (palabra != palabraMostrada) y aún le queden intentos:
			while (!(palabra.equals(palabraMostrada)) && intentos > 0) {

				// Se invita al usuario a jugar (función para cargar la "pantalla" del juego)
				pantallaJuego(mensaje1, mensaje2, intentos, puntos, palabraMostrada, letrasUtilizadas);

				// Se guarda la letra ingresada como mayúscula
				letraElegida = (input.next()).toUpperCase();

				// También se guarda la letra ingresada en la lista de letras utilizadas
				letrasUtilizadas = guardarLetraUtilizada(letraElegida, letrasUtilizadas);

				// Se llama la función que buscará si la letra elegida está en la palabra. Se guarda el resultado en un array.
				String[] resultadoBusqueda = buscarLetra(palabra, letraElegida, palabraMostrada, intentos);

				// Esta función retorna un array de Strings.
				// En este array vienen los valores de: palabraMostrada, mensaje1, mensaje2 e intentos (como String); en ese orden.
				palabraMostrada = resultadoBusqueda[0];
				mensaje1 = resultadoBusqueda[1];
				mensaje2 = resultadoBusqueda[2];
				intentos = Integer.valueOf(resultadoBusqueda[3]); // Se transforma el String en entero

				limpiarConsola(); // Se insertan líneas para separar las impresiones anteriores de las siguientes

			} // Se sale del while cuando se adivinó la palabra (palabra.equals(palabraMostrada) o cuando se perdió (intentos == 0)

			// Si se adivinó la palabra:
			if (palabra.equals(palabraMostrada)) {
				// Se calculan los puntos ganados (10 puntos por cada caracter de la palabra adivinada) y se suman a los anteriores
				puntos = puntos + (palabra.length() * 10);
				// Se suma 1 a la cantidad de aciertos
				aciertos++;
				// Se limpia la lista de letras utilizadas
				letrasUtilizadas = "";
				// Se genera aleatoriamente una nueva palabra a ser adivinada
				palabra = palabrasOriginal[(int) Math.floor(Math.random() * palabrasOriginal.length)];
				// Se genera la palabra que va a ser mostrada, sustituyendo todo caracter de la palabra por un asterisco
				palabraMostrada = palabra.replaceAll(".", "*");
				// Se generan los mensajes correspondientes
				mensaje1 = "       ¡FELICITACIONES, ADIVINASTE LA PALABRA!            ";
				mensaje2 = "           ¡Continuemos con la siguiente!                 ";
			}
			// Si perdió:
			else {
				// Se cierra el Scanner abierto
				input.close();
				// Se llama la función que imprimirá la pantalla de Game Over
				gameOver(aciertos, puntos);
			}

		}

	}

	
	// MÉTODOS

	public static String limpiarTildes(String cadena) {
		cadena = Normalizer.normalize(cadena, Normalizer.Form.NFD);
		cadena = cadena.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
		return cadena;
	}

	public static void pantallaJuego(String mensaje1P, String mensaje2P, int intentosP, int puntosP,
			String palabraMostradaP, String letrasUtilizadasP) {
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(mensaje1P);
		System.out.println(mensaje2P);
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("VIDAS: " + intentosP + "                             PUNTOS: " + puntosP);
		System.out.println();
		System.out.println();
		System.out.println("                    ¿Qué palabra es?                   ");
		System.out.println();
		System.out.println(">>>>>>>>>>>>>>           " + palabraMostradaP + "           <<<<<<<<<<<<<<");
		System.out.println();
		System.out.println();
		// Sólo si ya se ha utilizado alguna letra se imprimirá la línea que muestra las letras utilizadas
		if (!(letrasUtilizadasP.isEmpty())) {
			System.out.println("Letras utilizadas (" + letrasUtilizadasP + ")");
			System.out.println(" ");
		}
		System.out.println("INGRESA UNA LETRA Y DA ENTER:");

	}

	public static void limpiarConsola() {
		// Agrega 2 líneas vacías para separar las impresiones anteriores de las siguientes
		System.out.println();
		System.out.println();
	}

	public static String guardarLetraUtilizada(String letraElegidaP, String letrasUtilizadasP) {
		// Se guarda la letra ingresada en la lista de letras utilizadas
		// Si es la primera letra se guarda tal cual
		if (letrasUtilizadasP.isEmpty()) {
			letrasUtilizadasP = letraElegidaP;
		}
		// Si ya hay letras guardadas, se agrega a la lista existente
		else {
			letrasUtilizadasP = letrasUtilizadasP + ", " + letraElegidaP;
		}

		return letrasUtilizadasP;
	}

	public static String[] buscarLetra(String palabraP, String letraElegidaP, String palabraMostradaP, int intentosP) {

		// Se crea una copia de la palabra pero sin tildes. Esta será la variable usada para las comparaciones
		String palabraSinTildes = limpiarTildes(palabraP);

		// Se crea un String array a partir de palabraMostrada, para ser utilizado en el bucle for
		char[] palabraMostradaArray = palabraMostradaP.toCharArray();

		// Se crean 2 variables para guardar los mensajes que se emitirán
		String mensaje1P = "";
		String mensaje2P = "";

		// Se busca la letra elegida en la palabra (en su versión sin tildes)
		if (palabraSinTildes.contains(letraElegidaP)) {

			// Se crea una variable para guardar la letra que se va a ir buscando
			String letraEvaluada = "";

			// Se busca la letra elegida en la palabra (iterando para evaluar cada caracter de la palabra)
			for (int i = 0; i < palabraSinTildes.length(); i++) {

				// Se asigna el valor para la variable letraEvaluada
				letraEvaluada = String.valueOf(palabraSinTildes.charAt(i));

				// Si la letra elegida es igual a la letra evaluada, en la palabra a mostrar se sustituye el asterisco por la letra
				if (letraEvaluada.equals(letraElegidaP)) {
					palabraMostradaArray[i] = palabraP.charAt(i);
				}

			}

			// Se actualizan los valores de los mensajes a ser mostrados en la siguiente "pantalla"
			mensaje1P = "                       ¡HAS ACERTADO!                      ";
			mensaje2P = "                   ¡Continúa adivinando!                   ";

		}
		// Si la letra elegida NO está en la palabra:
		else {
			// Se resta 1 al número de intentos disponibles
			intentosP--;
			// Se actualizan los valores de los mensajes a ser mostrados en la siguiente "pantalla"
			mensaje1P = "              ESA LETRA NO ESTÁ EN LA PALABRA              ";
			mensaje2P = "                   ¡Vuelve a intentarlo!                   ";
		}

		// Creo un array para retornar 4 resultados "encapsulados" dentro
		String[] resultadoBusquedaP = new String[4];
		resultadoBusquedaP[0] = String.valueOf(palabraMostradaArray); // El char array se transforma en String para devolver la palabraMostrada
		resultadoBusquedaP[1] = mensaje1P;
		resultadoBusquedaP[2] = mensaje2P;
		resultadoBusquedaP[3] = String.valueOf(intentosP); // int intentosP se transforma en String para poder "salir" en este array tipo String

		// Devuelve el array como resultado
		return resultadoBusquedaP;

	}

	public static void gameOver(int aciertosP, int puntosP) {
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println("                        G A M E                           ");
		System.out.println("                        O V E R                           ");
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println();
		System.out.println();
		System.out.println("                Has adivinado " + aciertosP + " palabras          ");
		System.out.println("                Has acumulado " + puntosP + " puntos            ");
		System.out.println();
		System.out.println();
		System.out.println("                    ¡VUELVE A JUGAR!                      ");
		System.out.println();
		System.out.println();

	}

}
