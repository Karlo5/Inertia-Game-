package edu.uoc.nertia.model.exceptions;


/**
 * 
 * @author 
 * @version 1.0
 */
public class LevelException extends Exception {

    public static final String POSITION_ROW_ERROR ="";
    public static final String POSITION_COLUMN_ERROR ="";

    public LevelException(String message){
        super(message);
    }
}
