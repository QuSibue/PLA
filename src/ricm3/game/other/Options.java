package ricm3.game.other;

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

	// Aide debug
	public static final boolean ECHO_MOUSE = true;
	public static final boolean ECHO_MOUSE_MOTION = true;
	public static final boolean ECHO_KEYBOARD = true;

}
