# LiftSimulator

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
