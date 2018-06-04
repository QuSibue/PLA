package ricm3.game.other;


import ricm3.game.automaton.Direction;
import ricm3.game.automaton.Orientation;

public abstract class Options {

	public static final boolean USE_DOUBLE_BUFFERING = true;// Prévention bugs affichage
	public static boolean MUSIC = true; // Posibilité musique
	public static boolean FRIENDLY_FIRE = false; // Possibilité de blesser et d'être blessé par les alliers
	public static int MAX_SBIRES = 10; // Nombre max de mignons
	public static int TIMER = 90; // Temps de la manche en seconde
	public static int CHOICE_MAP = 2;// Identifiant permettant de sélectonner la map
	public static int LIFESPAN_FARMER = 10;// Temps de vie du farmer en seconde
	public static int HEALTH_PROTECT = 2;// Point de vie du farmer
	public static int HEALTH_OTHERS_SBIRES = 1; // Point de vie autres mignons
	public static int TIMER_LASER= 10; //Temps de vie du laser
	public static int NB_TYPE_MINION = 6; //Nombre type de sbires
	public static int MS_MINION = 100 ; //l'entier n'a aucun sens
	public static int MS_PLAYER = 200 ; 
	public static int TAILLE_CASE = 100; //case de 100 pixels sur 100 pixels

	// Aide debug
	public static final boolean ECHO_MOUSE = true;
	public static final boolean ECHO_MOUSE_MOTION = true;
	public static final boolean ECHO_KEYBOARD = true;

	public static void evalPosition(int x, int y, int x_res, int y_res, Direction d, Orientation o) {
		throw new RuntimeException("eval Position NYI");
	}

}

