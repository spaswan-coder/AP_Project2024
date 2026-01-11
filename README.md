🎮 AngryBirdGame
📌 Overview
AngryBirdGame is a fun and interactive 2D game inspired by the classic Angry Birds, developed using Java and the LibGDX game development framework.
The game features physics-based mechanics, colorful graphics, and multiple interactive screens.
________________________________________
✨ Features
•	Physics-Based Gameplay – Realistic interactions powered by Box2D
•	Rich Graphics – Includes colorful birds, pigs, blocks, and background art
•	Interactive Screens
o	Home Screen
o	Level Selection Screen
o	Gameplay Screen
o	Win & Lose Screens
________________________________________
⚙️ How to Run the Game
Run Command
Use the following command to build and launch the game:
./gradlew lwjgl3:run
________________________________________
📂 File Structure & Explanation
1. Core Module
The core module handles all gameplay logic and defines the main components of the game:
File	Description
Bird.java	Represents birds launched by the player
Pig.java	Defines pig behavior and reactions to collisions
Block.java	Represents destructible blocks
MyGame.java	Main entry point and screen initialization
GameScreen.java	Handles main gameplay and user input
HomeScreen.java	Displays the home screen
WinScreen.java	Screen shown when player wins
LoseScreen.java	Screen shown when player loses
PhysicsHandler.java	Handles Box2D physics interactions
________________________________________
2. LWJGL3 Module
Handles desktop-specific configurations:
File	Description
Lwjgl3Launcher.java	Launches the game and sets window properties
Lwjgl3ApplicationConfiguration.java	Sets FPS, VSync, fullscreen, and resources
________________________________________
3. Assets Directory
Contains all visual and audio resources:
•	images – Birds, pigs, blocks, backgrounds, slingshot
•	fonts – Custom fonts
•	sounds – Sound effects
•	levels – Level layout files
________________________________________
🎯 Example Gameplay Flow
1.	Starting the Game
o	Game opens on the Home Screen
o	Player taps to continue
2.	Menu Navigation
o	Menu offers “Play” option
o	Selecting Play opens Level Selection
3.	Level Selection
o	Choose a level to start playing
4.	Gameplay
o	Aim and launch birds to destroy pigs and structures
o	Defeat all pigs to win the level
5.	End of Level
o	Win Screen appears if all pigs are defeated
o	Lose Screen appears if the player fails
________________________________________
📚 Resources
•	Angry Birds Official Website (Bird Creator)
•	Angry Birds Fandom Gallery




# KillerBEE

A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.

## Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.

## Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.
