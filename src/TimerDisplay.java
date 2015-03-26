import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.util.TimerTask;

public class TimerDisplay extends JLabel
{
    private final static long STEPS = 1000; 
    private int seconds=-1;
    private int minutes=0;
    private java.util.Timer timer;
  
    public TimerDisplay() {
        setForeground(Color.red);
        setHorizontalAlignment(JLabel.CENTER);
        setText(""+0);
    }

    public void start() {
        timer=new java.util.Timer();
            TimerTask task=new TimerTask() {
                public void run() {
                    if(minutes > 60){
                        setText("Seriously?");
                    } else {
                        if(seconds > 60){
                            minutes++;
                            seconds = 0;
                        } else {
                            seconds++;                       
                        }
                        if(seconds > 10){
                            setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));  
                        } else {
                            setText(String.valueOf(minutes) + ":" + "0" + String.valueOf(seconds));
                        }
                                          
                        //setText(""+nbSeconds);                      
                    }

                }
            };
        timer.scheduleAtFixedRate(task,0,STEPS);
    }
  
    public void stop() {
        timer.cancel();
    }
  
    public void reset() {
        timer.cancel();
        seconds=-1;
        setText(""+0);    
    }
  
    public int getSeconds() {return seconds;}

   
}
