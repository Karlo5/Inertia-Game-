package edu.uoc.nertia.model.cells;

/**
 * @author 
 * @version 1.0
 */
public enum Element {

    EMPTY('-',"empty.png"), 
    EXTRA_LIFE('L',"live.png"), 
    GEM('*', "gem.png"), 
    MINE('X', "mine.png"), 
    PLAYER('@', "player.png"), 
    PLAYER_STOP('$', "player_stop.png"), 
    STOP('S', "stop.png"), 
    WALL('#', "wall.png");

    private String imageSrc;
    private char symbol;

    private Element(char symbol, String imageSrc){
        setSymbol(symbol);
        setImageSrc(imageSrc);
    }

    private setSymbol(char symbol){
        this.symbol = symbol;
    }
    private setImageSrc(String imageSrc){
        this.imageSrc = imageSrc;
    }

    public Element symbol2Element(char symbol){
            Element target = null;
            for(Element e: Element.values()){
                if(e.getSymbol().equals(symbol)) target = e;
            }
            return target;
    }
    public char getSymbol(){
        return symbol;
    }
    public String getImageSrc(){
        return imageSrc;
    }

}
