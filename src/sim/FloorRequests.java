package sim;

/**
 * This is how all of the Call Buttons and Arrival Signals talk to the rest
 * of the program. This creates all the buttons, signals, and corresponding
 * drivers for them.
 */
public class FloorRequests
{
  // index n maps to direction of requests at floor n
  private CallButton [] callButtons;
  private ArrivalSignal[] arrivalSignals;

  /**
   * FloorRequests Constructor that creates all of the arrivalSignals and
   * callButtons and assigns their corresponding drivers to them.
   * @param floors
   */
  public FloorRequests(final int floors)
  {
    arrivalSignals = new ArrivalSignal[floors];
    callButtons = new CallButton[floors];
    for (int i = 0; i < floors; i++)
    {
      callButtons[i] = new CallButton(new CallButtonDriver(floors, i));
      arrivalSignals[i] = new ArrivalSignal(new ArrivalSignalDriver(floors, i));
    }
  }

  /**
   * Returns the request based on several params that are taken by the method.
   * @param floor which floor
   * @param above above or below?
   * @param direction up or down?
   * @param first service first?
   * @return Request object which holds the floor and the direction.
   */
  public Request getRequest(int floor, boolean above, Direction direction, boolean first)
  {
    Request returnRequest = null;
    
    int reqFloor = -1;
    int startFloor;
    int limit;
      
    if (above)
    {
      limit = callButtons.length;
      startFloor = floor;
    }
      
    else
    {
      limit = floor+1;
      startFloor = 0;
    }
      
    int diff;
    int diff0;
  
    for (int i = startFloor; i < limit; i++)
    {
      if(callButtons[i].checkDirection(direction))
      {
        if(first)
        {
          diff = Math.abs(i - floor);
          diff0 = Math.abs(reqFloor - floor);
        }
        
        else
        {
          diff0 = Math.abs(i - floor);
          diff = Math.abs(reqFloor - floor);
        }
        
        if (diff < diff0 || reqFloor == -1) reqFloor = i;
      }
    }
  
    if(reqFloor != -1) returnRequest = new Request(reqFloor, direction);
    
    return returnRequest;
  }

  /**
   * Called when the cabin arrives at the floor and turns off the call button
   * and turns on the arrival signal.
   * @param request Request object
   */
  public void arrival(Request request)
  {
    arrivalSignals[request.floor].turnOnSignal(request.direction);
    callButtons[request.floor].clearRequest(request.direction);
  }

  /**
   * Turns off the arrival signal.
   * @param floor floor that is being departed from
   * @param direction which direction that floor is
   */
  public void departure(int floor, Direction direction)
  {
    arrivalSignals[floor].turnOffSignal();
  }

  /**
   * Returns call buttons array
   * @return CallButtons array
   */
  public CallButton[] getCallButtons()
  {
    return callButtons;
  }

  /**
   * Returns arrival signal array
   * @return ArrivalSignal array
   */
  public ArrivalSignal[] getArrivalSignals()
  {
    return arrivalSignals;
  }
}
