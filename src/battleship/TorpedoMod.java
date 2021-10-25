package battleship;

/*
 * Торпедный мод стрельбы.
 */
public final class TorpedoMod implements IShot {
    public void attack(Board board, Point point) {
        Cell cell = board.getCell(point);
        if (cell.myType == Cell.types.DEFAULT) {
            cell.shot(this);
        } else if (cell.myType == Cell.types.SHIP){
            if (board.getCell(point) instanceof ShipCell) {
                GameParams.changeShots(GameParams.Shots() + 1);
            }
            for (ShipCell shipCell : board.MyFleet().Ships()[((ShipCell) cell).ShipNumber()].Cells()) {
                shipCell.shot(this);
                if (!board.MyFleet().Ships()[((ShipCell) board.getCell(point)).ShipNumber()].isAliveAfterShot()) {
                    System.out.println("Крабль потоплен!");
                    board.MyFleet().changeAliveCount();
                }
            }
        } else {
            System.out.println("Вы уже стреляли по этим координатам.");
        }
    }

    @Override
    public String toString() { return "torpedo"; }
}
