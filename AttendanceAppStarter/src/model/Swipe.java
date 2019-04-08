package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author mga
 */
public class Swipe implements Comparable<Swipe>{

    /**
     *  
     */
    protected final int id;

    /**
     *
     */
    protected String cardId;

    /**
     *
     */
    protected String room;

    /**
     *
     */
    protected final Calendar swipeDateTime;
    
    private static int lastSwipeIdUsed = 0;
    static final char EOLN='\n';       
    static final String QUOTE="\"";    
    
    /**
     *
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }
    
    /**
     *
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeDateTime = getNow();
    }    
    
    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }     
    
    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * 
     * @return Card ID
     */
        
    public String getCardId() {
        return cardId;
    }    

    /**
     * @param cardId 
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    /**
     * @return Room
     */
    public String getRoom() {
        return room;
    }

    /**
     * 
     * @param room 
     */
    public void setRoom(String room) {
        this.room = room;
    }
    /**
     * 
     * @return Calendar of Swipe Date
     */
    public Calendar getSwipeDateTime() {
        return swipeDateTime;
    }
    
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    /**
     *
     * @param calendar
     * @return
     */
    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();  
        return dateFormat.format(calendar.getTime());
    }    

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId +            
                " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }
    public String toString(char delimiter) {
        final String QUOTE = "\"";
        final String EOLN = "\n";
        String output =  Integer.toString(id) + delimiter +
                         QUOTE + cardId + QUOTE + delimiter +
                         QUOTE + room + QUOTE + delimiter +
                         QUOTE + formatSwipeDateTime(swipeDateTime) + QUOTE + EOLN;
        return output;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Swipe other = (Swipe) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.cardId, other.cardId)) {
            return false;
        }
        if (!Objects.equals(this.room, other.room)) {
            return false;
        }
        if (!Objects.equals(this.swipeDateTime, other.swipeDateTime)) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.cardId);
        hash = 83 * hash + Objects.hashCode(this.room);
        hash = 83 * hash + Objects.hashCode(this.swipeDateTime);
        return hash;
    }

     public static Comparator<Swipe> swipeDateTimeattribute 
            = (Swipe s1, Swipe s2) -> s1.compareTo(s2);

    @Override
    public int compareTo(Swipe compareSwipe) {
        
        return compareSwipe.getSwipeDateTime().compareTo(swipeDateTime);
    }

   
 
    
             
}
