/*
 * file name: Score.java
 * programmer name: Nick McManus
 * date created: 11-08-2018
 * date of last revision: 
 * details of last revision: Create the score class with a few methods
 * short description: Describe what makes up a score
 */

package cardgame;

public class Score 
        implements java.io.Serializable, Comparable<Score>{
    final private int scoreAmt;
    final private java.util.Date time;
    
    public Score(int scoreAmt){
        this.scoreAmt = scoreAmt;
        time = new java.util.Date();
    }
    
    public int getScore(){
        return scoreAmt;
    }
    
    public java.util.Date getTime(){
        return time;
    }
    
    @Override
    public int compareTo(Score o){
        int compareInt = this.getScore() - o.getScore();
         
        if(compareInt != 0)
            return compareInt;
        
        return this.getTime().compareTo(o.getTime());
    }
}
