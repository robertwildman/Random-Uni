
package dsacw;


/**
 *
 * @author jde
 */

public class CourseWork {
    
    CourseWork() {
        // there might be some initialisations needing done
    }
    
    public void run() {
        System.out.println("DSA Coursework started\n");
        doPart1();
        doPart2();
        doPart3();
        doPart4();
        doPart5();
        System.out.println("\nDSA Coursework completed");
    }
    
    private void doPart1() {
        // put your test harness code for part 1 here
        new TestPart1().run();
   }
        
    private void doPart2() {
        // put your test harness code for part 2 here
        new TestPart2().run();
    }
        
    private void doPart3() {
        new TestPart3().run();
    }

    private void doPart4() {
        new TestPart4().run();
    }
    
    private void doPart5() {
        new TestPart5().run();
    }       
 
}
