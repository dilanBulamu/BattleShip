package battleship;

/*
 * Описание корабля в зависимости от переданных параметров.
 */
public final class Ship {
    private ShipCell[] myCells;
    private int health;
    private int myNumber;

    private static int number = 0;

    Ship(ShipParams myParams) {
        this.health = myParams.Size();
        myCells = new ShipCell[myParams.Size()];
        myNumber = number++;
        getCells(myParams);
    }

    public static void changeNumber() { number = 0; }
    // Создаем клетки с кораблями для дальнейшей установки на поле.
    private void getCells(ShipParams myParams) {
        if (myParams.IsHorizontally()) {
            for (int i = 0; i < myParams.Size(); ++i) {
                myCells[i] = new ShipCell(new Point(myParams.Point().X() + i, myParams.Point().Y()), myNumber);
            }
        } else {
            for (int i = 0; i < myParams.Size(); ++i) {
                myCells[i] = new ShipCell(new Point(myParams.Point().X(), myParams.Point().Y() + i), myNumber);
            }
        }
    }

    public ShipCell[] Cells() {
        return myCells;
    }

    // Если в одну из частей попали, то уменьшаем жизни.
    public boolean isAliveAfterShot() {
        if (--health == 0) {
            return false;
        }
        return true;
    }

    // Восстановление корабля.
    public void regenerate() {
        for (int i = 0; i < myCells.length; ++i) {
            myCells[i].regenerate();
        }
        health = myCells.length;
    }
}
