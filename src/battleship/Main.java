package battleship;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private final static GameParamsCreator gameParamsCreator = GameParamsCreator.getGameParamsCreator();
    private final static Description description = Description.getDescription(gameParamsCreator.getGameParams());


    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                GameParamsCreator.getCommand(Arrays.copyOfRange(args, 0, 3));
                GameParamsCreator.getCommand(new String[]{args[3], "5", args[4]});
                GameParamsCreator.getCommand(new String[]{args[3], "4", args[5]});
                GameParamsCreator.getCommand(new String[]{args[3], "3", args[6]});
                GameParamsCreator.getCommand(new String[]{args[3], "2", args[7]});
                GameParamsCreator.getCommand(new String[]{args[3], "1", args[8]});
                GameParamsCreator.getCommand(new String[]{args[9], args[10]});
                if (args[12].equals("true")) {
                    GameParamsCreator.getCommand(new String[]{args[11]});
                }
            } catch (Exception e) {
                System.out.println("Введена неверная команда.");
            }
        }
        getCommand();
    }

    private static void getCommand() {
        while (true) {
            System.out.print("Введите команду: ");
            String[] commandParams = scanner.nextLine().split(" ");
            switch (commandParams[0]) {
                case Description.startFlag:
                    stratGame();
                    break;
                case Description.endFlag:
                    System.out.println("Всего хорошего!");
                    System.exit(0);
                    break;
                case Description.helpFlag:
                    Description.showAvailableFlags();
                    break;
                case Description.infoFlag:
                    Description.showStartParams();
                    break;
                default:
                    GameParamsCreator.getCommand(commandParams);
                    break;
            }
        }
    }

    private static boolean checkCoordinates(String[] coordinates) {
        try {
            if (coordinates.length != 2 ||
                Integer.parseInt(coordinates[0]) < 0 ||
                Integer.parseInt(coordinates[0]) >= gameParamsCreator.getGameParams().SiseN()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static void stratGame() {
        GameParams params = gameParamsCreator.getGameParams();
        FleetGenerator fleetGenerator = new FleetGenerator(params.SiseN(), params.SiseM());
        Board board = new Board(params.SiseN(), params.SiseM(), fleetGenerator.getFleet(params.FleetParams()));
        Shooter shooter = new Shooter(board);
        boolean start = true;
        while (start) {
            try {
                board.show();
                System.out.print("Введите координаты: ");
                String[] coordinates = scanner.nextLine().split(" ");
                switch (coordinates[0]) {
                    case Description.endFlag:
                        start = false;
                        break;
                    case Description.shootingModFlag:
                        if (coordinates.length != 2) {
                            System.out.println("Введен несущетсвующий мод стрельбы.");
                            break;
                        }
                        switch (coordinates[1]) {
                            case "simple":
                                GameParams.getGameParams().changeShootingMod(new SimpleMod());
                                break;
                            case "torpedo":
                                GameParams.getGameParams().changeShootingMod(new TorpedoMod());
                                break;
                            default:
                                System.out.println("Введен несущетсвующий мод стрельбы.");
                                break;
                        }
                        break;
                    case Description.infoFlag:
                        Description.showParams();
                        break;
                    default:
                        shooter.changeShootingMod(GameParams.getGameParams().GameMod());
                        if (checkCoordinates(coordinates)) {
                            shooter.createShot(new Point(Integer.parseInt(coordinates[0]),
                                           (int) coordinates[1].charAt(0) - 97),
                                               gameParamsCreator.getGameParams());
                        } else {
                            System.out.println("Введены неверные координаты.");
                        }
                        if (board.MyFleet().AliveCount() == 0) {
                            start = false;
                            System.out.println("Поздравляем, вы победили!");
                            System.out.println(String.format("Затрачено вытсрелов: %d", GameParams.Shots()));
                            GameParams.changeShots(0);
                            Ship.changeNumber();
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println(String.format("Ошибка: %s", e.getMessage()));
            }
        }
    }
}
