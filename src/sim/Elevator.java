package sim;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Elevator class that is where all the main display happens for the project.
 * JavaFX mostly resides here. The entry spot for the entire program.
 *
 * @extends Application
 */
public class Elevator extends Application
{
  public static Color cabinColor;
  public static final Color OPEN = Color.BLUE;
  public static final Color CLOSED = Color.RED;
  private static final int WINDOW_WIDTH = 500;
  private static final int WINDOW_HEIGHT = 500;
  private Timeline timeline = new Timeline();
  private CallButton [] callButtons;
  private ArrivalSignal [] arrivalSignals;
  private Rectangle [] doors;
  private ScrollPane floorPane;
  private final int FLOORS = 10;
  private ElevatorControl ec;
  
  private ChangeListener cabinListener = new ChangeListener()
  {
    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1)
    {
      doors[(int) o].setFill(Color.TRANSPARENT);
      doors[(int) t1].setFill(cabinColor);
    }
  };

  /**
   * This is the ChangeListener for the door.
   */
  private ChangeListener doorListener = new ChangeListener()
  {
    @Override
    public void changed(ObservableValue observableValue, Object o, Object t1)
    {
      try
      {
        if ((boolean) t1)
        {
          cabinColor = OPEN;
          doors[ec.cabin.getCurrentFloor()].setFill(cabinColor);
        } else
        {
          cabinColor = CLOSED;
          doors[ec.cabin.getCurrentFloor()].setFill(cabinColor);
        }
      } catch (Exception e) {}
    }
    
  };

  /**
   * The start method that is called as the program starts. This creates all
   * the basic things to make the program run, including the second thread to
   * run the simulation on while the JavaFX runs on the current thread.
   * @param stage
   */
  public void start(Stage stage)
  {
    ec = new ElevatorControl(FLOORS);
    Task<Runnable> elevatorControl = new Task<Runnable>()
    {
      @Override
      protected Runnable call() throws Exception
      {
        ec.run();
        return null;
      }
    };
    
    ec.getCabin().setDriver(new CabinDriver(cabinListener));
    ec.doors.setDriver(new DoorDriver(doorListener));
    doors = new Rectangle[FLOORS];
    floorPane = new ScrollPane();
    makeDoors();
    makeFloors();
    
    Scene scene = new Scene(floorPane, WINDOW_WIDTH, WINDOW_HEIGHT);
    new Thread(elevatorControl).start();
    stage.setScene(scene);
    stage.show();
  }

  /**
   * This makes the VBox for the floors, creates the callButtons, Rectangles
   * for the doors, and the arrivalSignals.
   */
  public void makeFloors()
  {
    VBox floorVBox = new VBox();
    floorVBox.setSpacing(10);
    callButtons = ec.floorRequests.getCallButtons();
    arrivalSignals = ec.floorRequests.getArrivalSignals();
  
    for (int i = FLOORS - 1; i >= 0; i -= 1)
    {
      FlowPane floor = new FlowPane();
      floor.getChildren().addAll(callButtons[i].getDriver().getButtonPane(), doors[i], arrivalSignals[i].getDriver().getArrivalSignalPane());
      floorVBox.getChildren().add(floor);
    }
    
    floorPane.setContent(floorVBox);
  }

  /**
   * Makes the Rectangles for the doors.
   */
  public void makeDoors()
  {
    for(int i = 0; i < doors.length; i++)
    {
      doors[i] = new Rectangle(100,150);
      doors[i].setStroke(Color.LIGHTGRAY);
      doors[i].setFill(Color.TRANSPARENT);
      
      if(ec.cabin.getCurrentFloor() == i) doors[i].setFill(cabinColor);
    }
  }

  /**
   * This is how JavaFX needs to run. This is the entry spot for the whole
   * program.
   * @param args
   */
  public static void main(String[] args)
  {
    launch(args);
  }
}
