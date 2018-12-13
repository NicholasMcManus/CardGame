/*
 * file name: Score.java
 * programmer name: Nick McManus
 * date created: 11-08-2018
 * date of last revision: 11-27-2018
 * details of last revision: Add java documentation
 * short description: Describe what makes up a score
 */

package cardgame;

public class Score 
        implements java.io.Serializable, Comparable<Score>{
    final private int scoreAmt; //Stores the score number
    final private java.util.Date time; //Creates a date data type to store the date/ used for time calculations
    
    /**
     * Constructor for a score object
     * @param scoreAmt The number used to represent the score
     */
    public Score(int scoreAmt){
        this.scoreAmt = scoreAmt;
        time = new java.util.Date();
    }
    
    /**
     * A getter to return the score held by the object
     * @return The score
     */
    public int getScore(){
        return scoreAmt;
    }
    
    /**
     * A getter to return the date held by the object
     * @return An object holding the time from the object
     */
    public java.util.Date getTime(){
        java.util.Date timeCopy = (java.util.Date)(time.clone());
        return timeCopy;
    }
    
    /**
     * Function to compare score objects and returns if int data type if from the comparation result
     * @param o The object to be compared to This
     * @return An integer representing the comparison between scores
     */
    @Override
    public int compareTo(Score o){
        int compareInt = this.getScore() - o.getScore();
         
        if(compareInt != 0)
            return compareInt;
        
        return this.getTime().compareTo(o.getTime());
    }
}
