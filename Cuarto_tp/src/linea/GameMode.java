package linea;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class GameMode {

    char gameModeName;

    public static final ArrayList<GameMode> availableGameModes = new ArrayList<>(Arrays.asList(
            new GameModeA(),
            new GameModeB(),
            new GameModeC()
    ));

    public static GameMode getGameModeByChar(char typeOfGame) {
        return availableGameModes.stream()
                .filter(game -> game.gameModeName == typeOfGame)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid game"));
    }
        abstract boolean validateWinCondition(Conecta4 table, int column);



}
