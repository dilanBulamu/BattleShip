package battleship;

/*
 * Класс для реализации стрельбы по кораблям.
 */
public final class Shooter {
    private  Board board;
    private  IShot currectMod;

    Shooter(Board board) {
        this.board = board;
        currectMod = new SimpleMod();
    }

    // Создание вытсрела.
    public void createShot(Point point, GameParams gameParams) {
        IShot shot;
        if (currectMod instanceof SimpleMod) {
            shot = new SimpleMod();
        } else {
            if (gameParams.Torpedoes() <= 0) {
                System.out.println("У вас закончились торпеды");
                return;
            } else {
                gameParams.changeTorpedoes(gameParams.Torpedoes() - 1);
                shot = new TorpedoMod();
            }
        }
        shot.attack(board, point);
    }

    // Изменение мода стрельбы.
    public void changeShootingMod(IShot newMod) {
        currectMod = newMod;
    }
}
