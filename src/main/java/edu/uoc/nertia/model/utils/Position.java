package edu.uoc.nertia.model.levels;

/**
 * Level class.
 * @author 
 * @version 1.0
 */
public class Position throws PositionException {

  private int column;
  private int rown;

  public Position(int row, int column){
    setRow(row);
    setColumn(column);
  }

  @Override
  public boolean equals(Object o){
    Position posi;
    if(!o.instanceof(Position)) return false
    else{ 
        posi = (Position) o;
        if(this.getRow()!=posi.getRow()||this.getColumn()!=posi.getColumn()||
            posi.getRow()==null||posi.getColumn()==null) return false;
        else return true;
    }
  }

  @Override
  public int hashCode(){
    return Objects.hash(int row, int column);
  }

  public int getColumn(){
    return column;
  }

  public int getRow(){
    return row;
  }

  public Position offsetBy(int dRow, int dColumn){      
        Position result;
        int newRow = this.getRow()+dRow;
        int newColumn = this.getColumn()+dColumn;
        if(newRow<0||newColumn<0) result = null;
        else result = new Position(newRow, newColumn);
        return result; 
  }

  public Position offsetBy(int dRow, int dColumn, int size){
        if(getRow()+dRow>=size) dRow = size -getRow();
        if(getColumn()+dColumn>=size) dColumn = size -getColumn();
        return offsetBy(dRow, dColumn);
  }

  private void setColumn(int column){
    if(column<0) {
        throw new PositionException(PositionException.POSITION_COLUMN_ERROR);
    } else this.column = column;
  }

  private void setRow(int row){
    if(row<0) {
        throw new PositionException(PositionException.POSITION_ROW_ERROR);
    } else this.row = row;
  }

}
