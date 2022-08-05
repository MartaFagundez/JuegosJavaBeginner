package juego;

import java.util.Scanner;

public class RuletaCuantica {

	public static void main(String[] args) {
		// Crear arrays de los cuales extraer datos al azar
		String[] paises = {"Kiribati", "Tuvalu", "Seychelles", "Andorra", "Comoras", "Brunéi", "Chad", "Eritrea", "Fiyi", "Lesoto", "Moldavia", "Samoa", "Suazilandia", "Vanuatu", "Togo", "Tanzania", "Sri Lanka", "Senegal", "Polonia", "Palaos", "Nepal"};
		String[] nombres = {"Khalil", "Josefa", "Anastacia", "Alexa", "Sandolio", "Electra", "Asdrúbal", "Eleuterio", "Eustaquio", "Anacleto", "Cosmo", "Argus", "Astra", "Perséfone", "Guido", "Hilario", "Pancracio", "Ruperta", "Tiburcio", "Gertrudis", "Úrsula", "Filomena", "Fausto", "Hércules", "Zeus", "Afrodita"};
		String[] comidas = {"es la paella", "es la pizza", "es el pollo tandoori", "son los tacos", "es el sushi", "es el escargot", "es el dim sum", "es el spanakopita", "son las empanadas", "es el ramen", "es el hummus", "es el baklava", "son las ostras", "es el ratatouille", "es la mousakka", "es la sopa de cebollas", "es el pan de ajo", "son los grillos fritos"};
		String[] actividades = {"levantas pesas", "miras anime", "pintas un cuadro", "tejes crochet", "escribes tu biografía", "bailas flamenco", "tarareas La Cumparsita", "escribes código Java", "peinas a tu gato", "conversas con tu perro", "escuchas ópera"};
		String[] lugares = {"el comedor", "tu dormitorio", "el baño", "el garage", "el patio", "el living", "el balcón", "la terraza", "el sótano", "el ático", "la cocina"};
		String[] virtudes = {"voz aterciopelada", "cara angelical", "dientes resplandecientes", "sonrisa encantadora", "andar elegante", "liderazgo innato", "alegría contagiosa", "fuerza prodigiosa", "inteligencia extraordinaria", "voluntad de hierro", "belleza natural", "mirada luminosa", "sabiduría milenaria"};
		
		Scanner input = new Scanner(System.in);
		
		// Invitar al usuario a jugar
		System.out.println("***¡Esta es una Ruleta Cuántica!***");
		System.out.println("Ingresa 1 y da enter para dar un vistazo a cómo es tu vida en alguno de los muchos universos paralelos:");
		int comienzo = input.nextInt();
		input.close();
		
		if (comienzo == 1) {
			// Extraer datos de los arrays al azar
			/*
			 * Math.random() devuelve un número aleatorio entre 0.0 y 1.0, excluido este último valor, es decir, puede devolver 0.346442, 0.2344234, 0.98345,.... 
			 * Math.floor() devuelve un valor tipo double igual a el entero más grande que es menor o igual que el argumento 
			 * ¿Por qué multiplico Math.random()? Porque si no Math.floor siempre va a devolver 0
			 * ¿Cómo establecer el rango del valor que se quiere pasar a Math.floor? 
			 *     Para conseguir un número entero entre Min y Max, ambos incluídos, existe esta fórmula:
			 *     
			 *     int valorEntero = (int) Math.floor(Math.random()*(Max-Min+1)+Min); 
			 *     
			 *     Para un array Min es 0 y Max es (longuitd del array - 1)
			 *     Ej:
			 *     String pais = paises[(int) Math.floor(Math.random() * ((paises.length - 1) - 0 + 1) + 0 )];
			 *     Como resto y sumo 1, y resto y sumo 0, la expresión anterior es equivalente a:
			 *     String pais = paises[(int) Math.floor(Math.random() * paises.length)]; 
			 */
			
			String pais = paises[(int) Math.floor(Math.random() * paises.length)];
			String nombre = nombres[(int) Math.floor(Math.random() * nombres.length)];
			String comida = comidas[(int) Math.floor(Math.random() * comidas.length)];
			String actividad = actividades[(int) Math.floor(Math.random() * actividades.length)];
			String lugar = lugares[(int) Math.floor(Math.random() * lugares.length)];
			
			// Para asegurarme que virtud1, virtud2 y virtud3 no coincidan, utilizo bucles while hasta que las 3 sean diferentes
			String virtud1 = virtudes[(int) Math.floor(Math.random() * virtudes.length)];
			String virtud2 = virtudes[(int) Math.floor(Math.random() * virtudes.length)];
			while (virtud1.equals(virtud2)) {
				virtud2 = virtudes[(int) Math.floor(Math.random() * virtudes.length)];
			}
			String virtud3 = virtudes[(int) Math.floor(Math.random() * virtudes.length)];
			while (virtud3.equals(virtud1) || virtud3.equals(virtud2)) {
				virtud3 = virtudes[(int) Math.floor(Math.random() * virtudes.length)];
			}
			
			// Genero números aleatorios
			int universoNum = (int) Math.floor(Math.random() * 100000 + 1000);
			int hermanosNum = (int) Math.floor(Math.random() * 11 + 2);
			
			// Imprimo los mensajes con elementos generados al azar
			System.out.println("En el universo paralelo nº " + universoNum + ", el azar te ha llevado a " + pais + ", país donde naciste junto a otros " + hermanosNum + " hermanos.");
			System.out.println("Por alguna razón, tus padres pensaron que " + nombre + " era el nombre ideal para ti...");
			System.out.println("Tu comida favorita " + comida + " y aunque la gente te mire raro, te encanta comer mientras " + actividad + " en " + lugar + ".");
			System.out.println("A diario recibes muchos halagos, pero los que más se repiten son acerca de tu " + virtud1 + ", tu " + virtud2 + " y tu " + virtud3 + ".");
		} else {
			System.out.println("Lo siento, la ruleta sólo se activa con el número 1.");
		}

	}

}
