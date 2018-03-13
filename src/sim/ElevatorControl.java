package sim;


/**
 * The main controller of the Elevator which gathers requests from the call
 * buttons and then tells the cabin where to move.
 *
 * @implements Runnable
 */
public class ElevatorControl implements Runnable
{
  private final static int FLOORS = 10;
  Cabin cabin;
  Doors doors;
  FloorRequests floorRequests;
  private boolean running = true;

  /**
   * Constructor for Elevator Control that takes the amount of floors as a
   * param. Creates the Cabin, Doors, and the FloorRequests objects.
   * @param floors The amount of floors
   */
  public ElevatorControl(final int floors)
  {
    floorRequests = new FloorRequests(floors);
    cabin = new Cabin();
    doors = new Doors();
  }

  /**
   * Overridden run method for the Runnable to create the mutlithread.
   */
  @Override
  public void run()
  {
    cabin.cabinStatus();
    while (running)
    {
      serviceNextRequest();
    }
  }

  /**
   * Used to move the cabin to the floor within the request.
   * @param request A Request object
   */
  private void moveCabin(Request request)
  {
    if (doors.isOpen())
    {
      System.err.println("THE DOORS ARE NOT CLOSED!");
    }

    cabin.moveToFloor(request.floor);
  }

  private void serviceNextRequest()
  {
    Request request = getNextRequest();

    if (request != null)
    {
      doors.close();
      floorRequests.departure(cabin.getCurrentFloor(), cabin.currentDirection());
      moveCabin(request);
      floorRequests.arrival(request);
      doors.open();
      sleep(3);

      cabin.setDirection(request.direction);
    }
    
    else
    {
      cabin.setDirection(Direction.NULL);
    }
  }
  
  private Request getNextRequest()
  {
    Request request = null;
    Direction currentDirection = cabin.currentDirection();
    int currentFloor = cabin.getCurrentFloor();
    
    if(currentDirection != Direction.NULL)
    {
      boolean above = true;
      
      if(currentDirection == Direction.DOWN) above = false;
      
      request = floorRequests.getRequest(currentFloor, above, currentDirection, true);
      if (request == null) request = floorRequests.getRequest(currentFloor, above, currentDirection.getOpposite(), false);
    }

    else
    {
      Request aboveReq = floorRequests.getRequest(currentFloor, true, Direction.UP, true);
      Request belowReq = floorRequests.getRequest(currentFloor, false, Direction.DOWN, true);
  
      if(aboveReq == null) aboveReq = floorRequests.getRequest(currentFloor, true, Direction.DOWN, false);
  
      if(belowReq == null) belowReq = floorRequests.getRequest(currentFloor, false, Direction.UP, false);
  
      if(aboveReq == null || belowReq == null)
      {
        if(aboveReq != null) request = aboveReq;
        if(belowReq != null) request = belowReq;
      }
  
      else
      {
        int aboveDif = aboveReq.floor - currentFloor;
        int belowDif = belowReq.floor - currentFloor;
  
        if(aboveDif < belowDif) request = aboveReq;
        else if(aboveDif > belowDif) request = belowReq;
        else request = aboveReq;
      }
    }
    
    return request;
  }

  /**
   * Returns the cabin.
   * @return Cabin
   */
  public Cabin getCabin()
  {
    return cabin;
  }
  
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
  
  public static void main(String[] args)
  {
    ElevatorControl elevatorControl = new ElevatorControl(FLOORS);
    elevatorControl.run();
  }

}