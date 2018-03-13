package sim;

/**
 * The doors for the Elevator with the open and close methods for the doors
 * with checking for the doors open or closing status when told to open or
 * close.
 */
public class Doors
{
  private boolean open = true;  // doors start open
  private DoorDriver driver;

  /**
   * Opens the doors to the cabin.
   */
  public void open()
  {
    System.out.println("Opening doors");
    if (!open)
    {
      open = true;
      driver.changeDoorState(open);
    }
    else System.err.println("The doors are already open!");
  }

  /**
   * Closes the doors to the cabin.
   */
  public void close()
  {
    System.out.println("Closing doors");
    if (open)
    {
      open = false;
      driver.changeDoorState(open);
    }
    else System.err.println(getClass() + " already closed!");
  }

  /**
   * Checks to see if the doors are open for security purposes.
   * @return true or false
   */
  public boolean isOpen()
  {
    return open;
  }

  /**
   * Sets the driver for the doors.
   * @param driver DoorDriver
   */
  public void setDriver(DoorDriver driver)
  {
    this.driver = driver;
  }
}
