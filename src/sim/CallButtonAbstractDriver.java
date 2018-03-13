package sim;


/**
 * An Abstract class for Call Button Drivers. This is useful if other drivers
 * needed to be written.
 */
public abstract class CallButtonAbstractDriver
{
  protected abstract boolean callUp();

  protected abstract boolean callDown();

  public abstract void clearUp();

  public abstract void clearDown();

  public abstract void pressUp();

  public abstract void pressDown();
}
