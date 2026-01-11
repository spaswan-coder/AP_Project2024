AngryBirdGame
Afunandinteractive 2Dgameinspiredbytheclassic Angry Birds, created using Java
andtheLibGDXgamedevelopmentframework.
Features
● Physics-BasedGameplay:Realistic interactions powered by Box2D.
● RichGraphics:Includescolourful birds, pigs, blocks, and background art.
● Interactive Screens:
○ HomeScreen
○ LevelSelectionScreen
○ GameplayScreen
○ WinandLoseScreens
HowtoRuntheGame
RuntheGame:Usethefollowingcommandtobuildandlaunchthegame.
./gradlew lwjgl3:run
File Structure and Explanation
1. Core Module
Thecoremodulehandlesallthegameplaylogicanddefinesthemaincomponentsof
the game:
● Bird.java: Represents the birds that players launch. Includes functionality for
movement,animations,andinteractions with the gameenvironment.
● Pig.java:Definesthepig'sbehaviorandproperties. Pigs serve as the game's
targets and react to collisions.
● Block.java:Represents structural blocks in the game. Blocks can be destroyed
or knockedoverbythebirds.
● MyGame.java:Themainentrypointforthegamelogic.Setsupthegame
environment andinitialises various game screens.
● GameScreen.java:Managesthegameplayscene,renderingbirds,blocks,and
pigs. Handles user input for launching birds.
● HomeScreen.java:Displaysthetitle screen with options to start the game or
select levels.
● WinScreen.javaandLoseScreen.java: Screens displayed after a level ends,
indicating whether the player won or lost.
● PhysicsHandler.java: Managesphysicsinteractions using Box2D, simulating
realistic gravity, collisions, and object movements.
2. LWJGL3Module
TheLWJGL3modulehandlesdesktop-specificconfigurations,includingwindow
creation and platform-specific settings.
● Lwjgl3Launcher.java: Themainclassresponsible for launching the gameon
desktop platforms. Configures screen resolution, window title, and fullscreen
mode.
● Lwjgl3ApplicationConfiguration.java: Sets additional configurations, such as
framerate, VSync, and resource directories.
3. Assets Directory
Contains all the visual and audio resources for the game:
● images:Includesspritesfor birds, pigs, blocks, background elements, and the
slingshot.
● fonts:Containsanycustomfontsusedinthegameinterface.
● sounds:Optionaldirectoryfor soundeffectslike bird launches, block collisions,
andpigdefeat sounds.
● levels:Files defining thelayout of levels.
ExampleGameplay
1. Starting the Game:
○ ThegamebeginsontheHomeScreen.
○ TheplayertouchesanywhereonthescreentoproceedtotheMenu
Screen.
2. NavigatingtheMenu:
○ TheMenuScreenoffersoptionslike"Play"tostartthegame.
○ Selecting"Play"leadstotheLevelSelection Screen.
3. ChoosingaLevel:
○ TheLevelScreendisplaysavailablelevels.
○ Afterselecting alevel, the player is taken to the Main GameScreen.
4. Gameplay:
○ Playersaimandlaunchbirdstoknockdownpigsandstructures.
○ Thegoalistodefeatallpigstowinthelevel.
5. EndofLevel:
○ Iftheplayersuccessfully defeats all pigs, the Win Screen appears.
○ Iftheplayerfails, the Lose Screenappears.
Resources
● AngryBird'sOfficialWebsiteuseBirdsCreator
● AngryBirdFandomGallery
Sonu Paswan
2023530


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
