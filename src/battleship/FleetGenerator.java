package battleship;

import java.util.ArrayList;

/*
 * Генерация флота.
 */
public final class FleetGenerator {
    private int fieldSizeN;
    private int fieldSizeM;
    int[][] board;

    FleetGenerator(int n, int m) {
        fieldSizeN = n;
        fieldSizeM = m;
        fillBoard();
    }

    // Заполняем поле пустыми ячейками.
    private void fillBoard() {
        board = new int[fieldSizeN][fieldSizeM];
        for (int i = 0; i < fieldSizeN; ++i) {
            for (int j = 0; j< fieldSizeM; j++) {
                board[i][j] = 0;
            }
        }
    }

    // Расставляем корабли по заданным параметрам кол-ва.
    public Fleet getFleet(int[] fleetParams) {
        ArrayList<Ship> ships = new ArrayList<Ship>();
        int x, y, direction;
        for (int i = 0; i < fleetParams.length; ++i) {
            for (int j = 0; j < fleetParams[i];) {
                y = (int) (Math.random() * fieldSizeM);
                x = (int) (Math.random() * fieldSizeN);
                direction = (int) (Math.random() * 4);

                if (checkSetting(x, y, direction, fleetParams.length - i)) {
                    placeShip(x, y, direction, fleetParams.length - i);
                    ships.add(getShip(x, y, direction, fleetParams.length - i));
                    ++j;
                }
            }
        }
        return new Fleet(ships.toArray(Ship[]::new));
    }

    // Проверяем можно ли поставить корабль.
    private boolean checkSetting(int x, int y, int direction, int shipSize) {
        for (int i = shipSize; i > 0; --i) {
            if (x < 0 || y < 0 || x >= fieldSizeN || y >= fieldSizeM) {
                return false;
            }
            if (board[x][y] == 1 ||
                (y + 1 < fieldSizeM && board[x][y + 1] == 1) ||
                (x + 1 < fieldSizeN && board[x + 1][y] == 1) ||
                (y - 1 >= 0 && board[x][y - 1] == 1) ||
                (x - 1 >= 0 && board[x - 1][y] == 1) ||
                (y + 1 < fieldSizeM && x + 1 < fieldSizeN && board[x + 1][y + 1] == 1) ||
                (y - 1 >= 0 && x - 1 >= 0 && board[x - 1][y - 1] == 1) ||
                (y + 1 < fieldSizeM && x - 1 >= 0 && board[x - 1][y + 1] == 1) ||
                (y - 1 >= 0 && x + 1 < fieldSizeN && board[x + 1][y - 1] == 1)) {
                return false;
            }

            switch (direction) {
                case 0:
                    x++;
                    break;
                case 1:
                    y++;
                    break;
                case 2:
                    x--;
                    break;
                case 3:
                    y--;
                    break;
            }
        }

        return true;
    }

    // Установка корабля на поле.
    private void placeShip(int x, int y, int direction, int shipSize) {
        for (int i = shipSize; i > 0; --i) {
            switch (direction) {
                case 0:
                    board[x++][y] = 1;
                    break;
                case 1:
                    board[x][y++] = 1;
                    break;
                case 2:
                    board[x--][y] = 1;
                    break;
                case 3:
                    board[x][y--] = 1;
                    break;
            }
        }
    }

    // Получение готового корабля для флота.
    private Ship getShip(int x, int y, int direction, int shipSize) {
        ShipParams shipParams = null;
        switch (direction) {
            case 0:
                shipParams = new ShipParams(shipSize, new Point(x, y), true);
                break;
            case 1:
                shipParams = new ShipParams(shipSize, new Point(x, y), false);
                break;
            case 2:
                shipParams = new ShipParams(shipSize, new Point(x - shipSize + 1, y), true);
                break;
            case 3:
                shipParams = new ShipParams(shipSize, new Point(x, y - shipSize + 1), false);
                break;
        }

        return new Ship(shipParams);
    }
}
