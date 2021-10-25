package battleship;

public final class GameParamsCreator {
    private static GameParamsCreator gameParamsCreator;
    private static GameParams gameParams = GameParams.getGameParams();

    private GameParamsCreator() { }

    public static GameParamsCreator getGameParamsCreator() {
        if (gameParamsCreator == null) {
            gameParamsCreator = new GameParamsCreator();
        }
        return gameParamsCreator;
    }

    public static void getCommand(String[] params) {
        switch (params[0]) {
            case Description.fieldFlag:
                changeFieldParams(params, gameParams);
                break;
            case Description.torpedoFlag:
                changeTorpedoParams(params, gameParams);
                break;
            case Description.regenerationFlag:
                changeRegenerationParams(params, gameParams);
                break;
            case Description.fleetFlag:
                changeFleetParams(params, gameParams);
                break;
            default:
                System.out.println("Введен неверный флаг.");
                break;
        }
    }

    public GameParams getGameParams() {
        return gameParams;
    }

    private static void changeFieldParams(String[] params, GameParams gameParams) {
        try {
            if (params.length != 3) {
                throw new IllegalArgumentException("Введенная команда не соответствует требованиям.");
            }
            gameParams.changeSize(Integer.valueOf(params[1]), Integer.valueOf(params[2]));
        } catch (ClassCastException e) {
            System.out.println("Введены неорректные параметры размера поля.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeTorpedoParams(String[] params, GameParams gameParams) {
        try {
            if (params.length != 2) {
                throw new IllegalArgumentException("Введенная команда не соответствует требованиям.");
            }
            gameParams.changeTorpedoes(Integer.valueOf(params[1]));
        } catch (ClassCastException e) {
            System.out.println("Введены неорректные параметры количества торпед.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeRegenerationParams(String[] params, GameParams gameParams) {
        try {
            if (params.length != 1) {
                throw new IllegalArgumentException("Введенная команда не соответствует требованиям.");
            }
            gameParams.changeRegenerationMod();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void changeFleetParams(String[] params, GameParams gameParams) {
        try {
            if (params.length != 3) {
                throw new IllegalArgumentException("Введенная команда не соответствует требованиям.");
            }
            gameParams.changeFleetParms(Integer.valueOf(params[1]), Integer.valueOf(params[2]));
        } catch (ClassCastException e) {
            System.out.println("Введены неорректные параметры количества торпед.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
