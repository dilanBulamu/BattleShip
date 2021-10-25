package battleship;

/*
 * Регенерация корабля.
 */
public final class Regeneration {
    private static boolean regenerationMod;
    private static Regeneration regeneration;
    private static Ship attackedShip;
    Regeneration() {
        regenerationMod = false;
    }

    public static synchronized Regeneration getRegeneration() {
        if (regeneration == null) {
            regeneration = new Regeneration();
        }
        return regeneration;
    }

    // Получаем корабль, который атакуют.
    public static void AttackedShip(Ship ship) { attackedShip = ship; }

    // Изменение мода регенерации.
    public static void changeRegenerationMod() { regenerationMod = !regenerationMod; }

    // Если есть что регенирировать, то регенирируем.
    public static void regenerate() {
        if (regenerationMod && attackedShip != null) {
            attackedShip.regenerate();
            attackedShip = null;
        }
    }
}
