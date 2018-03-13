package sim;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Scanner;

/**
 * The text driver for the call button, which is used to gather the call
 * requests and check for correct input of those call requests in text form.
 *
 * Extends CallButtonAbstractDriver
 */
public class CallButtonDriver extends CallButtonAbstractDriver
{
  private int floors;
  private int floor;
  private boolean upLightOn;
  private boolean downLightOn;
  private boolean hasUpButton = true;
  private boolean hasDownButton = true;
  private Circle upButton;
  private Circle downButton;

  /**
   * CallButtonDriver Constructor. Takes the amount of floors and the floor
   * it is assigned to. Sets some default values as well.
   * @param floors total floors
   * @param floor the floor this driver is on
   */
  public CallButtonDriver(int floors, int floor)
  {
    this.floors = floors;
    this.floor = floor;
    upLightOn = downLightOn = false;
    if(floor == floors-1)
    {
      hasUpButton = false;
    }
    
    if(floor == 0)
    {
      hasDownButton = false;
    }
  }

  protected boolean callUp()
  {
//    int random = (int)(Math.random() * 2);
//    if (random == 0 && hasUpButton)
//    {
//      upLightOn = true;
//      callButtonOn(Direction.UP);
//    }
    
    return upLightOn;
  }

  protected boolean callDown()
  {
//    int random = (int)(Math.random() * 2);
//    if (random == 0 && hasDownButton)
//    {
//      downLightOn = true;
//      callButtonOn(Direction.DOWN);
//    }
    
    return downLightOn;
  }

  /**
   * Clears the up light when called.
   */
  public void clearUp()
  {
    upLightOn = false;
    upButton.setFill(Color.BLUE);
    callButtonOff(Direction.UP);
  }

  /**
   * Clears the down light when called.
   */
  public void clearDown()
  {
    downLightOn = false;
    downButton.setFill(Color.BLUE);
    callButtonOff(Direction.DOWN);
  }

  private void callButtonOn(Direction direction)
  {
    System.out.println("Call button " + floor + " "+ direction.toString() + " turned on");
  }

  private void callButtonOff(Direction direction)
  {
    System.out.println("Call button " + floor + " " + direction.toString() +
            " turned off");
  }

  /**
   * Returns a GridPane that has the floor's JavaFX display for the call button.
   * @return GridPane with the callButton
   */
  public GridPane getButtonPane()
  {
    GridPane buttonPane = new GridPane();
    buttonPane.setPadding(new Insets(20));
    Label floorLabel = new Label(floor + ": ");
    upButton = new Circle(10);
    downButton = new Circle(10);
    
    upButton.setFill(Color.BLUE);
    downButton.setFill(Color.BLUE);
    upButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
    {
      @Override
      public void handle(MouseEvent e)
      {
        pressUp();
      }
    });
    
    downButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
    {
      @Override
      public void handle(MouseEvent e)
      {
        pressDown();
      }
    });
    
    buttonPane.add(floorLabel, 0, 0);
    if(hasUpButton) buttonPane.add(upButton, 1, 0);
    if(hasDownButton) buttonPane.add(downButton, 1, 1);
    return buttonPane;
  }

  /**
   * Sets the colour of the button to yellow when pushed.
   */
  public void pressUp()
  {
    if(hasUpButton)
    {
      upLightOn = true;
      upButton.setFill(Color.YELLOW);
      callButtonOn(Direction.UP);
    }
  }

  /**
   * Sets the colour of the button to yellow when pushed.
   */
  public void pressDown()
  {
    if(hasDownButton)
    {
      downLightOn = true;
      downButton.setFill(Color.YELLOW);
      callButtonOn(Direction.DOWN);
    }
  }
}
