package edu.uoc.nertia.model.levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;

import edu.uoc.nertia.model.exceptions.PositionException;
import edu.uoc.nertia.model.utils.Position;
import edu.uoc.nertia.model.cells.Cell;
import edu.uoc.nertia.model.cells.CellFactory;
import edu.uoc.nertia.model.cells.Element;
import edu.uoc.nertia.model.exceptions.LevelException;
import edu.uoc.nertia.model.stack.StackItem;
import edu.uoc.nertia.model.stack.UndoStack;

/**
 * Level class.
 * @author David García Solórzano
 * @version 1.0
 */
public class Level {

    /**
     * Minimum size of the board in one direction, the board will be sizexsize
     */
    private static final int MIN_SIZE = 3;

    /**
     * Number representing unlimited number of lives for a player.
     */
    private static final int UNLIMITED_LIVES = -1;

    /**
     * Number of rows and columns in the game board. A board is a square of size x size.
     */
    private final int size;

    /**
     * Difficulty of the level
     */
    private LevelDifficulty difficulty;

    /**
     * 2D array representing each cell in the game board.
     */
    private Cell[][] board;

    /**
     * The number of moves performed by the player (excluding invalid moves).
     */
    private int numMoves = 0;

    /**
     * The number of lives the player has.
     */
    private int numLives;

    /**
     * The number of gems the player has got.
     */
    private int numGemsGot = 0;

    /**
     * The number of gems initially on the game board when a {@link Level} instance was created.
     */
    private final int numGemsInit;

    /**
     * Data structure that allows us to undo moves and manage its information.
     */
    private final UndoStack undoStack;

    /**
     * Constructor
     *
     * @param fileName Name of the file that contains level's data.
     * @throws LevelException When there is any error while parsing the file.
     */
    public Level(String fileName) throws LevelException{
        size = parse(fileName);

        numGemsInit = (int)
                Arrays.stream(getBoard()).flatMap(Arrays::stream)
                        .filter(cell -> cell.getElement() == Element.GEM)
                        .count();

        //Uncomment when you create UndoStack class.
        //undoStack = new UndoStack();
    }

    /**
     * Parses/Reads level's data from the given file.<br/>
     * It also checks which the board's requirements are met.
     *
     * @param fileName Name of the file that contains level's data.
     * @return The size of the board in one direction (i.e. row or column). The board is {@code size x size}.
     * @throws LevelException When there is any error while parsing the file
     * or some board's requirement is not satisfied.     *
     */
    private int parse(String fileName) throws LevelException{
        String line;
        int size = 0;

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = Objects.requireNonNull(classLoader.getResourceAsStream(fileName));

        try(InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader)){

            line = getFirstNonEmptyLine(reader);

            if (line != null) {
                setNumLives(Integer.parseInt(line));
            }

            line = getFirstNonEmptyLine(reader);

            if (line  != null) {
                size = Integer.parseInt(line);
                if(size < MIN_SIZE){
                    throw new LevelException(LevelException.SIZE_ERROR);
                }
            }

            line = getFirstNonEmptyLine(reader);

            if (line != null) {
                setDifficulty(LevelDifficulty.valueOf(line));
            }

            board = new Cell[size][size];

            for (int row = 0; row < size; row++) {
                char[] rowChar = Objects.requireNonNull(getFirstNonEmptyLine(reader)).toCharArray();
                for (int column = 0; column < size; column++) {
                    board[row][column] = CellFactory.getCellInstance(row, column,rowChar[column]);
                }
            }

            //Checks if there are more than one finish cell
            if(Stream.of(board).flatMap(Arrays::stream).filter(x -> x.getElement() == Element.PLAYER).count()!=1){
                throw new LevelException(LevelException.PLAYER_LEVEL_FILE_ERROR);
            }

            //Checks if there are one gem at least.
            if(Stream.of(board).flatMap(Arrays::stream).filter(x -> x.getElement() == Element.GEM).count()<1){
                throw new LevelException(LevelException.MIN_GEMS_ERROR);
            }

        }catch (IllegalArgumentException | IOException | PositionException e){
            throw new LevelException(LevelException.PARSING_LEVEL_FILE_ERROR);
        }

        return size;
    }

    /**
     * This is a helper method for {@link #parse(String fileName)} which returns
     * the first non-empty and non-comment line from the reader.
     *
     * @param br BufferedReader object to read from.
     * @return First line that is a parsable line, or {@code null} there are no lines to read.
     * @throws IOException if the reader fails to read a line.
     */
    private String getFirstNonEmptyLine(final BufferedReader br) throws IOException {
        do {

            String s = br.readLine();

            if (s == null) {
                return null;
            }
            if (s.isBlank() || s.startsWith("/")) {
                continue;
            }

            return s;
        } while (true);
    }


}
