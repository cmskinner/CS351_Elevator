package sim;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;

/**
 * The Driver for the Doors class.
 */
public class DoorDriver
{
  private BooleanProperty open = new SimpleBooleanProperty();

  /**
   * Takes the ChangeLister as the param and assigns it to the
   * BooleanProperty to control the door state.
   * @param listener
   */
  public DoorDriver(ChangeListener listener)
  {
    open.addListener(listener);
    changeDoorState(true);
  }

  /**
   * changes the door state to true or false (open or closed)
   * @param open true or false
   */
  public void changeDoorState(boolean open)
  {
    this.open.set(open);
  }
}
