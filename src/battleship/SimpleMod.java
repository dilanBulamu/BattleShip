package battleship;

/*
 * Простой мод стрельбы.
 */
public final class SimpleMod implements IShot {
    public void attack(Board board, Point point) {
        Cell cell = board.getCell(point);
        if (cell.myType == Cell.types.DEFAULT || cell.myType == Cell.types.SHIP) {
            cell.shot(this);
            if (board.getCell(point) instanceof ShipCell &&
                    !board.MyFleet().Ships()[((ShipCell) board.getCell(point)).ShipNumber()].isAliveAfterShot()) {
                System.out.println("Крабль потоплен!");
                board.MyFleet().changeAliveCount();
                Regeneration.AttackedShip(null);
            } else if (board.getCell(point) instanceof EmptyCell) {
                Regeneration.regenerate();
            } else {
                Regeneration.AttackedShip(board.MyFleet().Ships()[((ShipCell) board.getCell(point)).ShipNumber()]);
            }
            if (board.getCell(point) instanceof ShipCell) {
                GameParams.changeShots(GameParams.Shots() + 1);
            }
        } else {
            System.out.println("Вы уже стреляли по этим координатам.");
        }
    }

    @Override
    public String toString() { return "simple"; }
 }
