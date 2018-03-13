package sim;

/**
 * The Elevator cabin that moves between floors and is essentially the motors
 * as well.
 */
class Cabin
{

  private static final boolean TIME_EMULATION = true;

  private Direction direction = Direction.NULL;
  private int currentFloor = 0;                         // start at bottom floor
  private boolean stopped = true;
  private CabinDriver driver;

  /**
   * Returns the current direction that the cabin is moving.
   * @return Direction, up or down.
   */
  public Direction currentDirection()
  {
    return direction;
  }

  /**
   * Returns the current floor the cabin is on.
   * @return int which is the current floor.
   */
  public int getCurrentFloor()
  {
    return currentFloor;
  }

  /**
   * Sets which direction for the cabin to move.
   * @param direction direction for the cabin to move in
   */
  public void setDirection (Direction direction)
  {
    this.direction = direction;
  }

  /**
   * Moves the cabin from the current floor to the desired floor.
   * @param floor floor that is being moved to
   */
  public void moveToFloor(int floor)
  {
    if (floor > currentFloor)
      direction = Direction.UP;
    else if (floor < currentFloor)
      direction = Direction.DOWN;

    if (TIME_EMULATION)
    {
      sleep(1);
    }

    System.out.println("The Cabin is moving " + direction + " to floor " + floor);

    while(floor != currentFloor)
    {
      stopped = false;
      if (currentFloor < floor)
      {
        currentFloor++;
      }
      else if (currentFloor > floor)
      {
        currentFloor--;
      }
      cabinStatus();
      if (TIME_EMULATION)
      {
        sleep(2);
      }
    }

    stopped = true;

    if (TIME_EMULATION)
    {
      sleep(1);
    }
  }

  /**
   * Creates a message when the cabin has reached a floor.
   */
  public void cabinStatus()
  {
    System.out.println("Cabin is on floor " + currentFloor);
    driver.setCurrentFloor(currentFloor);
  }

  /**
   * Sets the cabin driver.
   * @param driver the CabinDriver
   */
  public void setDriver(CabinDriver driver)
  {
    this.driver = driver;
  }

  /**
   * Sleep method for simulating the delay for the cabin movement.
   * @param sec seconds
   */
  private void sleep(int sec)
  {
    try
    {
      Thread.sleep(500 * sec);
    } catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
