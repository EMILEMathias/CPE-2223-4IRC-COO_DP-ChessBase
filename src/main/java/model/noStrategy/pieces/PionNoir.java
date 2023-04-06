package model.noStrategy.pieces;

import shared.ModelCoord;
import shared.PieceSquareColor;

public class PionNoir extends Pion{

    /**
	 * @param couleur
	 * @param coord
	 */
    public PionNoir(PieceSquareColor couleur, ModelCoord coord) {  //M.Becker a dit qu'ne realité ce constructeur laisse place a l'incohérence
        super(couleur, coord);                                      //et donc que le constructeur ci-dessous serait plus juste dans l'éventualité ou 
    }                                                               //l'on ne veux pas réecrire le constructeur de chesspiecefactory

/*  public PionNoir(ModelCoord coord) {
*		super(PieceSquareColor.BLACK, coord);
*
*	}
*/
    @Override
    protected boolean byCorlorMove(int xFinal, int yFinal, boolean ret){
        
        if (yFinal - this.getY() > 0) { // mais non nul et positif (car noir)
            ret = true;
        }
        return ret;
    }

    @Override
	protected boolean byCorlorTake(int xFinal, int yFinal, boolean ret){
        if ((yFinal == this.getY()+1 && xFinal == this.getX()+1) || (yFinal == this.getY()+1 && xFinal == this.getX()-1)) {
            ret = true;
        }
        return ret;
    }
    
}
