package sim;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * The driver for the arrival signal in which it displays to the
 * standard output when the arrival light is turned on and off and handles
 * the JavaFX implementation.
 */
public class ArrivalSignalDriver
{
  private int floors;
  private int floor;
  private boolean hasUpSignal;
  private boolean hasDownSignal;
  private boolean upLightOn;
  private boolean downLightOn;
  private Polygon upSignal;
  private Polygon downSignal;

  /**
   * Arrival Signal Driver constructor. Takes the amount of floors and the
   * floor that it is assigned to as params. Stores those and sets member
   * variables to default states.
   * @param floors
   * @param floor
   */
  public ArrivalSignalDriver(int floors, int floor)
  {
    this.floors = floors;
    this.floor = floor;
    hasUpSignal = hasDownSignal = true;
    upLightOn = downLightOn = false;
    if(floor == floors-1)
    {
      hasUpSignal = false;
    }
  
    if(floor == 0)
    {
      hasDownSignal = false;
    }
  }

  /**
   * Takes a directions as a param. Turns the arrival Signal on based on
   * conditions.
   * @param direction direction in which the signal will turn on
   */
  public void arrivalSignOn(Direction direction){
    if(direction != Direction.NULL)
    {
      if(direction == Direction.UP && hasUpSignal)
      {
        upSignal.setFill(Color.YELLOW);
        upLightOn = true;
      }
      
      else if(direction == Direction.DOWN && hasDownSignal)
      {
        downSignal.setFill(Color.YELLOW);
        downLightOn = true;
      }
      
      System.out.println("Arrival sign " + floor + " " + direction.toString() + " turned on");
    }
  }

  /**
   * Turns off all arrival Signals on the particular floor when called.
   */
  public void arrivalSignOff(){
    if(upLightOn)
    {
      upLightOn = false;
      upSignal.setFill(Color.DARKGREY);
      System.out.println("Arrival sign " + floor + " UP turned off");
    }
    
    else if(downLightOn)
    {
      downLightOn = false;
      downSignal.setFill(Color.DARKGREY);
      System.out.println("Arrival sign " + floor + " DOWN turned off");
    }
  }

  /**
   * The display for JavaFX for the Arrival Signals. Creates the triangles
   * for each floor.
   *
   * @return GridPane of all the Arrival Signals.
   */
  public GridPane getArrivalSignalPane()
  {
    GridPane arrivalSignals = new GridPane();
    arrivalSignals.setPadding(new Insets(10));
    double sideLength = 20;
    if(hasUpSignal)
    {
      upSignal = new Polygon();
      upSignal.getPoints().addAll(0.0, (Math.sqrt(3)/2)*sideLength, sideLength, (Math.sqrt(3)/2)*sideLength, sideLength/2, 0.0);
      upSignal.setFill(Color.DARKGRAY);
      arrivalSignals.add(upSignal, 0, 0);
    }
  
    if(hasDownSignal)
    {
      downSignal = new Polygon();
      downSignal.getPoints().addAll(0.0, 0.0, sideLength, 0.0, sideLength/2, (Math.sqrt(3)/2)*sideLength);
      downSignal.setFill(Color.DARKGRAY);
      arrivalSignals.add(downSignal, 0, 1);
    }
    
    return arrivalSignals;
  }
}
