import ecs100.*;
import java.util.*;

/**
 * FSM Controller for a simulated Lift.
 * The core of the controller is the signal(String sensor) method
 * which is called by the lift every time a sensor
 * is signalled.
 */
public class LiftController {

    /**
     * The field that stores the current state of the FSM
     */
    private String state = "Door Closed";   
    // Note, you may wish to "factor" the state by having additional variables
    // to represent aspects of the state

    /**
     * The fields that store the flags used in the FSM
     */
    private boolean R1 = false;
    private boolean R2 = false;
    private boolean R3 = false;
    /**
     * if at floor 1, nextFloor = 2
     * if at floor 3, nextFloor = 4
     *      if at floor 2
     *          nextFloor = 3 if moving up
     *          nextFloor = 1 if moving down
     */
    private int nextFloor = 2;

    /**
     * The field containing the Lift.
     * The signal method will call methods on the intersection to
     * change the lights
     */
    private Lift lift;  // the lift that is being controlled.

    // possible actions on the lift that you can perform:
    // lift.moveUp()             to start the lift moving up
    // lift.moveDown()           to start the lift moving down
    // lift.stop()               to stop the lift
    // lift.openDoor()           to start the doors opening
    // lift.closeDoor()          to start the doors closing
    // lift.restartTimer(1000)   to set the time for 1000 milliseconds
    // lift.turnWarningLightOn() to turn the warning light on
    // lift.turnWarningLightOff()to turn the warning light off

    /**
     * The Constructor is passed the lift that it is controlling.
     */
    public LiftController(Lift lift) {
        this.lift = lift;
    }

    /**
     * Receives a change in a sensor value that may trigger an action and state change.
     * If there is a transition out of the current state associated with this
     * sensor signal, 
     * - it will perform the appropriate action (if any)
     * - it will transition to the next state
     *   (by calling changeToState with the new state).
     *
     * Possible sensor values that you can respond to:
     * (note, you may not need to respond to all of them)
     *   "request1"   "request2"   "request3"
     *   "atF1"   "atF2"   "atF3"
     *   "startUp"   "startDown"
     *    "doorClosed"   "doorOpened"   "doorMoving"
     *    "timerExpired"
     *    "doorSensor"
     *    "withinCapacity"   "overCapacity"
     * 
     * You can either have one big method, or you can break it up into
     * a separate method for each state 
     */
    public void signal(String sensor){
        UI.println("Sensor: "+ sensor);
        if (sensor.equals("request1") || sensor.equals("request2") || sensor.equals("request3")) {
            addFloor (sensor);      /*# Set flags*/
        } 
        else if (sensor.equals("doorSensor")) {
            lift.restartTimer(1200);
        } 
        else if (sensor.equals("timerExpired")) {
            lift.closeDoor();
        }

        if (state.equals("Door Closed") && nextFloor == 2){
            if (R2 || R3){
                lift.moveUp();
                nextFloor = 3;
                state = "Moving Up";
            }
            else if (R1){
                lift.openDoor();
                state = "Door Opening";
            }
        }
        else if (state.equals("Door Closed") && nextFloor == 4){
            if (R2 || R1){
                lift.moveDown();
                nextFloor = 1;
                state = "Moving Down";
            }
            else if (R3){
                lift.openDoor();
                state = "Door Opening";
            }
        }
        else if (state.equals("Door Closed") && (nextFloor == 1 || nextFloor == 3) && (R1 || R3)){
            if (nextFloor == 3 && R3) {
                lift.moveUp();
                state = "Moving Up";
            }
            else if (nextFloor == 1 && R1){
                lift.moveDown();
                state = "Moving Down";
            }
            else if (nextFloor == 1 && !R1){
                lift.moveUp();
                state = "Moving Up";
            }
            else if (nextFloor == 3 && !R3) {
                lift.moveDown();
                state = "Moving Down";
            }
        }
        else if (state.equals("Moving Up")){
            if (sensor.equals("atF2") && R2){
                state = "Door Opening";
            }
            else if (sensor.equals("atF3") && R3){
                state = "Door Opening";
            }
        }
        else if (state.equals("Moving Down")){
            if (sensor.equals("atF2") && R2){
                state = "Door Opening";
            }
            else if (sensor.equals("atF1") && R1){
                state = "Door Opening";
            }
        }
        else if (state.equals("Door Opening")){
            if (sensor.equals("doorOpened")){
                lift.restartTimer(2000);
                state = "Door Open";
            }
        }
        else if (state.equals("Door Open")){
            if (sensor.equals("doorSensor")){
                state = "Boarding and Lights Off";
            } else if (sensor.equals("timerExpired")){
                state = "Door Closing";
            }
        }
        else if (state.equals("Boarding and Lights Off")){
            if (sensor.equals("doorSensor")){
                state = "Door Open";
            } 
            else if (sensor.equals("timerExpired")){
                state = "Door Closing";
            }
            else if (sensor.equals("overCapacity")){
                lift.turnWarningLightOn();
                state = "Boarding and Lights On";
            }
        }
        else if (state.equals("Boarding and Lights On")){
            if (sensor.equals("withinCapacity")){
                lift.turnWarningLightOff();
                lift.closeDoor();
                state = "Boarding and Lights Off";
            }
        }        
        else if (state.equals("Door Closing")){
            if (sensor.equals("doorClosed")){
                state = "Door Closed";
                signal("doorClosed");
            }
        }

        if (sensor.equals("atF1") || sensor.equals("atF2") || sensor.equals("atF3")) {
            removeFloor (sensor);      /*# Undo flags*/
        }
    }

    private void addFloor (String sensor) {
        if (sensor.equals("request1")){
            R1 = true;
        }
        else if (sensor.equals("request2")){
            R2 = true;
        }
        else if (sensor.equals("request3")){
            R3 = true;
        }
    }

    private void removeFloor (String sensor) {
        if (sensor.equals("atF1")){
            if (R1) {
                lift.stop();
                lift.openDoor();
                R1 = false;
            }
            nextFloor = 2;
        }
        else if (sensor.equals("atF2")){
            if (R2) {
                lift.stop();
                lift.openDoor();
                R2 = false;
            }
        }
        else if (sensor.equals("atF3")){
            if (R3) {
                lift.stop();
                lift.openDoor();
                R3 = false;
            }
            nextFloor = 4;
        }
    }
}