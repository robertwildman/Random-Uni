
package dsacw;
import java.lang.String;
/**
 *
 * @author jde
 */
public enum EnumCoin {
    ONEP(1),
    TWOP(2),
    FIVEP(5),
    TENP(10),
    TWENTYP(20),
    FIFTYP(50),
    ONEHUNDREDP(100),
    TWOHUNDREDP(200),
    FIVEHUNDREDP(500),
    UNKNOWNCOIN(0);
    // put you enum info here
    EnumCoin(int faceValue){
        
    }
    
    //Check to see if the number is in the array if not return null coin 
    public Coin isVaild(int facevalue,int minted)
    {
        Coin c = null;
        EnumCoin ec = EnumCoin.valueOf(facevalue);
        if(ec == null)
        {
            //Unknown Coin return right coin infomation
        }
        else
        {
            if(correctmint(minted))
            {
                 c = new Coin(ec.name(),minted);
            }else
            {
                //Return message to user
            }
        }
        return c;
    }
    public static EnumCoin valueOf(int i)
    {
    
    }

    private boolean correctmint(int minted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
