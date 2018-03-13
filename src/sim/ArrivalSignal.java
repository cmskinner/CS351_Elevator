package sim;

/**
 * The arrival signal that gives the driver the correct information to
 * display and when to turn the lights on.
 */
public class ArrivalSignal
{
  private ArrivalSignalDriver driver;

  /**
   * Arrival Signal Constructor.
   *
   * Takes an ArrivalSignalDriver as a param and simply stores it in a member
   * variable.
   * @param driver
   */
  public ArrivalSignal(ArrivalSignalDriver driver)
  {
    this.driver = driver;
  }

  /**
   * Turns on the signal based on the direction of the param.
   * @param direction direction in which the signal will be on
   */
  public void turnOnSignal(Direction direction)
  {
    driver.arrivalSignOn(direction);
  }

  /**
   * Turns off the signal.
   */
  public void turnOffSignal()
  {
      driver.arrivalSignOff();
  }

  /**
   * Returns the ArrivalSignalDriver that the Arrival Signal holds.
   * @return
   */
  protected ArrivalSignalDriver getDriver()
  {
    return driver;
  }
}
