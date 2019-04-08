
package dsacw;

/**
 *
 * @author jde
 */
public class Part1 implements IDisplayable {
    
    @Override
    public void displayClassInfo( ) {
       System.out.println(message);
    }


    @Override
    public String getClassInfo( ) {   
        return message;
    }

    Part1() {
        // put your constructor code here to initialise any class fields           
    }
        
    // you need some class fields so put them here
    public String message = "This class has 2 fields a display Class info which displays a message to the user and a getclassinfo which returns a string verision of the message";     
}
