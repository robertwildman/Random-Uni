
package dsacw;

/**
 *
 * @author jde
 */
public class PiggyBank implements IDisplayable, ICollectable<Coin> {
    
    @Override
    public void displayClassInfo( ) {
        // put your code here
    }


    @Override
    public String getClassInfo( ) {
        // put your code here    
        return "";
    }
    
    @Override
    public boolean putAway( Coin coin ) {
        return true;
    }

    PiggyBank() {
        // put your constructor code here to initialise any class fields etc
    }
        
    // put your class members here
}
