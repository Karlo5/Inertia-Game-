package edu.uoc.nertia.model.cells;

import edu.uoc.nertia.model.utils.*;

/**
 * Cell class.
 * @author 
 * @version 1.0
 */
public class Cell {

    private Element element;
    private Position position;

    public Cell(Position position, Element element){
        setPosition(position);
        setElement(element);
    }

    public final Element getElement(){
        return element;
    }

    public final Position getPosition(){
        return position;
    }

    public void setElement(Element element){
        this.element = element;
    }

    private void setPosition(Position position){
        this.position = position;
    }

    

}
