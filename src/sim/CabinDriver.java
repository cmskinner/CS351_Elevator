package sim;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This is the driver for the Cabin class.
 */
public class CabinDriver
{
  private IntegerProperty currentFloor = new SimpleIntegerProperty();

  /**
   * CabinDriver constructor. Takes a ChangeListener as a param and adds it
   * to the current floor.
   * @param listener ChangeListener
   */
  public CabinDriver(ChangeListener listener)
  {
    currentFloor.addListener(listener);
  }

  /**
   * Sets the current floor when it is called.
   * @param currentFloor floor that the cabin is currently on
   */
  public final void setCurrentFloor(int currentFloor)
  {
    this.currentFloor.setValue(currentFloor);
  }
}
