package sim;

/**
 * The Call Button which is used to call the elevator from where ever it is
 * to the floor that the button was pressed on.
 */
public class CallButton
{
  private boolean requestUp;
  private boolean requestDown;
  CallButtonDriver driver;

  /**
   * CallButton constructor which takes the CallButtonDriver that it has to
   * tell it if it has a request or not as a param and stores it. It also
   * sets some member variables to default values.
   * @param driver CallButtonDriver
   */
  public CallButton (CallButtonDriver driver)
  {
    this.driver = driver;
    requestUp = requestDown = false;
  }

  /**
   * Checks the direction of the param and changes to whether the request is
   * up or down.
   * @param direction direction on which to check
   * @return true or false
   */
  public boolean checkDirection(Direction direction)
  {
    check();
    switch (direction)
    {
      case UP:
        return requestUp;
        
      case DOWN:
        return requestDown;
      
      default:
        return true;
    }
  }

  private void check()
  {
    callUp();
    callDown();
  }

  /**
   * Clears the request from the param direction.
   * @param direction direction to clear request
   */
  public void clearRequest(Direction direction)
  {
    if(direction == Direction.UP)
    {
      clearUp();
    }
    
    if(direction == Direction.DOWN)
    {
      clearDown();
    }
  }

  /**
   * Gets the CallButtonDriver
   * @return CallButtonDriver
   */
  public CallButtonDriver getDriver()
  {
    return driver;
  }

  private void callUp()
  {
    requestUp = driver.callUp();
  }
  
  private void callDown()
  {
    requestDown = driver.callDown();
  }
  
  private void clearUp()
  {
    requestUp = false;
    driver.clearUp();
  }
  
  private void clearDown()
  {
    requestDown = false;
    driver.clearDown();
  }
}
