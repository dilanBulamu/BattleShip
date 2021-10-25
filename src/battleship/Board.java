package battleship;
/*
 * Класс поля для битвы.
 */
public final class Board {
    // Размеры поля.
    private int sizeN;
    private int sizeM;
    // Флот расположенный на поле.
    private Fleet myFleet;
    // Поле.
    private Cell[][] board;

    // Переменная для скрытия непотопленных частей корблей.
    private static final Cell fakeCell = new EmptyCell(new Point(-1, -1));

    // Конструктор с размером поля и флотом.
    Board(int n, int m, Fleet myFleet) {
        sizeN = n;
        sizeM = m;
        this.myFleet = myFleet;
        // Размешение флота на поле.
        create(myFleet);
    }

    // Размешение флота на поле.
    private void create(Fleet fleet) {
        board = new Cell[sizeN][sizeM];

        for (int i = 0; i < sizeN; ++i) {
            for (int j = 0; j < sizeM; ++j) {
                Cell cell = new EmptyCell(new Point(i, j));
                board[i][j] = cell;
            }
        }

        for(var ship : fleet.Ships()) {
            for (var cell : ship.Cells()) {
                board[cell.point.X()][cell.point.Y()] = cell;
            }
        }
    }

    // Возвращает клетку поля.
    public Cell getCell(Point point) { return board[point.X()][point.Y()]; }

    // Возвращает информацию о флоте.
    public Fleet MyFleet() { return myFleet; }

    // Рисовка поля.
    public void show() {
        System.out.print("  \t");
        for (char i ='a'; i < 'a' + sizeM; ++i) {
            System.out.print(i + " \t");
        }
        System.out.print('\n');
        for (int i = 0; i < sizeN; ++i) {
            System.out.print(i + " \t");
            for (int j = 0; j < sizeM; ++j) {
                System.out.print(board[i][j] + " \t");
            }
            System.out.print('\n');
        }
    }
}
