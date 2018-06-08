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
	public static int TAILLE_CASE = 100; //case de 100 pixels sur 100 pixels
	public static int NB_TILE_WIDTH_MAP = 100; // Largeur de la MAP
	public static int NB_TILE_HEIGHT_MAP = 100;// Longueur de la MAP
	
	// Aide debug
	public static final boolean ECHO_MOUSE = true;
	public static final boolean ECHO_MOUSE_MOTION = true;
	public static final boolean ECHO_KEYBOARD = true;
	
	public static final int LASER_MS = 200;
	public static final int PLAYER_MS = 100;
	public static final int MINION_MS = 200;

	//Cooldown
	public static final long laserCD = 300L;
	public static final long powerCD = 300L;
	public static void evalPosition(int x, int y, int x_res, int y_res, Direction d, Orientation o) {
		throw new RuntimeException("eval Position NYI");
	}

}

