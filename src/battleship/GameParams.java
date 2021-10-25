package battleship;

/*
 * Параметры игры.
 */
public final class GameParams {
    private static GameParams gameParams;
    private static boolean regenerationMod;
    private static int ships;
    private static int torpedoes;
    private static int shots;
    private static int sizeN;
    private static int sizeM;
    private static int maxTotalLengthOfShips;
    private static int maxCountOfShips;
    private static IShot gameMod;
    private static int[] fleetParams;

    private GameParams() { }

    public static synchronized GameParams getGameParams() {
        if (gameParams == null) {
            ships = 10;
            sizeN = 10;
            sizeM = 10;
            shots = 0;
            torpedoes = 0;
            regenerationMod = false;
            maxCountOfShips = 11;
            maxTotalLengthOfShips = 20;
            fleetParams = new int[] {0, 1, 2, 3, 4};
            gameMod = new SimpleMod();
            gameParams = new GameParams();
        }
        return gameParams;
    }

    public static int Shots() { return shots; }

    public int SiseN() { return sizeN; }

    public int SiseM() { return sizeM; }

    public int Ships() { return ships; }

    public int Torpedoes() { return torpedoes; }

    public boolean RegenerationMod() { return regenerationMod; }

    public int[] FleetParams() { return fleetParams; }

    public IShot GameMod() { return gameMod; }


    // Изменение размера.
    public void changeSize(int n, int m) {
        if (n < 8 || n > 15) {
            throw new IllegalArgumentException("Введены недопустимые параметры размера поля.");
        }
        if (m < 8 || m > 15) {
            throw new IllegalArgumentException("Введены недопустимые параметры размера поля.");
        }
        sizeN = n;
        sizeM = m;
        maxTotalLengthOfShips = (int) Math.ceil(0.2 * n * m);
        maxCountOfShips = (int) Math.ceil(0.1111 * n * m);
        ships = 0;
        fleetParams = new int[] {0, 0, 0, 0, 0};
    }

    // Изменение мода регенирации.
    public void changeRegenerationMod() {
        Regeneration.changeRegenerationMod();
        regenerationMod = !regenerationMod;
    }

    public static void changeShots(int count) { shots = count; }

    // Изменение кол-ва торпед.
    public void changeTorpedoes(int count) {
        if (count < 0 || count > ships) {
            throw new IllegalArgumentException("Количество торпед может быть >= 0 и <= количетва кораблей.");
        }
        torpedoes = count;
    }

    // Изменение размера поля.
    public void changeFleetParms(int length, int count) {
        if (length < 1 || length > 5) {
            throw new IllegalArgumentException("Задана неверная длина корабля.");
        }
        if (count < 0) {
            throw new IllegalArgumentException("Количество кораблей не может быть отрицательно.");
        }

        int maxCount = 0;
        int maxLength = 0;

        for (int i = 0; i < 5; ++i) {
            if (5 - length != i) {
                maxCount += fleetParams[i];
                maxLength += fleetParams[i] * (5 - i);
            } else {
                maxCount += count;
                maxLength += length * count;
            }
        }

        if (maxCount > maxCountOfShips) {
            throw new IllegalArgumentException("Количесвтво кораблей превышает максимальное допустимое число.");
        }
        if (maxLength > maxTotalLengthOfShips) {
            throw new IllegalArgumentException("Суммарная длина кораблей максимальное допустимое число.");
        }
        fleetParams[5 - length] = count;
        ships = maxCount;
        torpedoes = maxCount;
    }

    // Изменение мода стрельбы.
    public void changeShootingMod(IShot newMod) {
        gameMod = newMod;
    }
}
