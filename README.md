# LiftSimulator

## Overview

### The Lift

The lift has three levels and can `move up and down`, and can `open and close its doors`.

Inside the lift are three buttons that passengers can press to specify which floor they want to go to.

On each floor, outside the lift, is a button to call the lift to that floor.

In addition to the lift buttons and call buttons, the lift has several other sensors:

- The lift will signal when it arrives at a floor. Note that although it will automatically stop when it reaches the top or bottom floor, it will not automatically stop at the middle floor, though it will signal when it gets there.

- There are sensors on the door that signal when the door is fully open, when it is fully closed, and when the door starts to move (opening or closing). There is also a sensor that signals whenever a person walks through the doors (in or out of the lift).

- There is a weight sensor that triggers a signal when the lift has just become overloaded (and also triggers a complementary signal when it has just ceased being overloaded.)

- The lift has a timer which can be set for some period, and the timer will signal when the timer has expired.

The lift is a simplification of a real lift. In particular, it can stop and start instantaneously, and it does not have any of the safety features of a real lift, such as a speed cutout, or door sensors to prevent it from squashing people.

### The simulation program

The program shows the lift moving between floors, and opening/closing its doors. It displays all the buttons (both on the floors and in the lift itself. The program will also generate people who will appear on the floors wanting to get to one of the other floors. The people will press the buttons automatically when waiting, and when they get on the lift. If a person has to wait too long, they get angry (and turn red). People will also get off the lift if the overload warning light is on.

The simulation will also keep track of the average time it takes for the people to complete their trips, and the fraction of them that get angry

## Compiling Java files using Eclipse IDE

1. Download this repository as ZIP
2. Create new `Java Project` in `Eclipse`
3. Right click on your `Java Project` --> `Import`
4. Choose `General` --> `Archive File`
5. Put directory where you downloaded ZIP in `From archive file`
6. Put `ProjectName/src` in `Into folder`
7. Click `Finish`

### Linking the UI Library

8. Right click on your `Java Project` --> `Build Path` --> `Add External Archives`
9. Select `ecs100.jar` and link it to the project. That JAR will be in the directory where you downloaded ZIP

## Running the program

1. Right click on your `Java Project` --> `Run As` --> `Java Application` --> `Lift Simulation`
2. `Start/Stop Generating` to auto-generate people. Be mindful of the controller being used. You can also pause or continue the simulation

## Controllers

- Built-in (from professor)
- UML-based (mine)

# Features

- `Call buttons` - buttons outside of lift
- `Floor Buttons` - buttons inside of lift
- `New Person` - generate new person and put waiting on random floor

## Goal

The goal is to minimise the fraction of riders who get angry
