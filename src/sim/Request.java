package sim;

/**
 * A data type that is used to for elevator requests from the call buttons
 * throughout the program.
 */
public class Request
{
  Direction direction;
  int floor;

  /**
   * Request contructor that takes a floor and a direction and stores each of
   * them.
   * @param floor floor of the request
   * @param direction direction that the request is requesting.
   */
  public Request(int floor, Direction direction)
  {
    this.direction = direction;
    this.floor = floor;
  }
}
