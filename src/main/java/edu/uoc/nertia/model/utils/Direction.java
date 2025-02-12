package edu.uoc.nertia.model.utils;

/**
 * Direciton enum.
 * @author 
 * @version 1.0
 */
public enum Direction {

    LEFT(0, -1), UP(-1,0), RIGHT(0,1), DOWN(1,0);

    private int rowOffset;
    private int columnOffset;

    private Direction(int rowOffset, int columnOffset){

        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;

    }

    public int getRowOffset(){
        return rowOffset;
    }

    public int getColumnOffset(){
        return columnOffset;
    }
   

}
