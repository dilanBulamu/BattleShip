package battleship;

/*
 * Класс для работы с флагами и вывода информации об игре.
 */
public final class Description {
    // Допустимые флаги
    public final static String fieldFlag = "-field";
    public final static String torpedoFlag = "-torpedo";
    public final static String regenerationFlag = "-regeneration";
    public final static String fleetFlag = "-fleet";
    public final static String startFlag = "-start";
    public final static String endFlag = "-end";
    public final static String infoFlag = "-info";
    public final static String helpFlag = "-help";
    public final static String shootingModFlag = "-shootingMod";

    private static Description description;
    // Параметры игры
    private static GameParams gameParams;

    // Синглитон патерн.
    private Description() { }

    public static synchronized Description getDescription(GameParams params) {
        if (description == null) {
            description = new Description();
            gameParams = params;
        }
        return description;
    }

    // Информация об аспектах игры.
    private final static String regenerationMod = "Мод регенерации: %s";
    private final static String ships = "Количество кораблей: %d";
    private final static String torpedoes = "Осталось торпед: %d";
    private final static String gameMod = "Мод стрельбы: %s";
    private final static String filedSize = "Размер поля: %d x %d";
    private final static String fleetParams = "Состав флота: [%d, %d, %d, %d, %d]";

    // Параметры до начала игры.
    public static void showStartParams() {
        System.out.println("Текущие параметры игры: ");
        System.out.println(String.format(filedSize, gameParams.SiseN(), gameParams.SiseM()));
        System.out.println(String.format(ships, gameParams.Ships()));
        int[] fleet = gameParams.FleetParams();
        System.out.println(String.format(fleetParams, fleet[0], fleet[1], fleet[2], fleet[3], fleet[4]));
        System.out.println(String.format(torpedoes, gameParams.Torpedoes()));
        System.out.println(String.format(regenerationMod, gameParams.RegenerationMod()));
    }

    // Статистика боя.
    public static void showParams() {
        System.out.println("Статистика игры игры: ");
        System.out.println(String.format(torpedoes, gameParams.Torpedoes()));
        System.out.println(String.format(gameMod, gameParams.GameMod()));
    }

    // Вывод доступных флагов.
    public static void showAvailableFlags() {
        System.out.println("Доступные флаги:");
        System.out.println(startFlag + " - начало игры.");
        System.out.println(fieldFlag + " - задать размер поля.");
        System.out.println(regenerationFlag + " - изменить мод регенерации.");
        System.out.println(torpedoFlag + " - задать количество торпед.");
        System.out.println(fleetFlag + " - задать параметры флота.");
        System.out.println(infoFlag + " - вывод текущих параметров.");
        System.out.println(endFlag + " - выход.");
    }
}
