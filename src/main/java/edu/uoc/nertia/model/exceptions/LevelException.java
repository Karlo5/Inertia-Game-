package edu.uoc.nertia.model.exceptions;


/**
 * 
 * @author 
 * @version 1.0
 */
public class LevelException extends Exception {

    public static final String PARSING_LEVEL_FILE_ERROR ="";
    public static final String PLAYER_LEVEL_FILE_ERROR ="";
    public static final String SIZE_ERROR ="";
    public static final String MIN_GEMS_ERROR ="";
    public static final String INCREASE_NUM_GEMS_GOT_ERROR ="";
    public static final String INCREASE_NUM_LIVES_ERROR ="";
    public static final String INCORRECT_CELL_POSITION ="";

    public LevelException(String message){
        super(message);
    }
}
