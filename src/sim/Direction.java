package sim;

/**
 * The direction enum just has the simple up and down directions, as well as
 * a null for when the Elevator isn't moving and a method to get the opposite
 * direction.
 */
public enum Direction
{
  UP, DOWN, NULL;

  private Direction opposite;

  static
  {
    UP.opposite = DOWN;
    DOWN.opposite = UP;
    NULL.opposite = NULL;
  }

  /**
   * Gets the opposite of whichever direction exists.
   *
   * @return Direction
   */
  public Direction getOpposite()
  {
    return opposite;
  }
}
