# MemeTeam2

Goo is a Slay The Sprie inspired card game built only with Java by programmers with about 6 months experience.

In Goo, you play a goo monster experiment trying to defeat the evil scientists and people that created you, which is a futile effort.
Perhaps there is a message that human invention will never be able to snuff out humanity.

Goo is primarily based around JPanel graphics and uses a convoluted web of class objects to run game functionality. Animations are
made using built in java timer tools.

Goo.java
--------
Program entry point, creation point of the JFrame, and also includes mouse click detection class (which in turn holds a lot of
functionality as effects are generally calculated after click).

Board.java
----------
JPanel object class that draws just about every visual element.

GooGame.java
------------
Class that represents the game as a whole for calculation purposes. The game object is created in the initialization of the board object.
Everything for the game is initialized here (Deck, Player and Monster stats).

Card.java
---------
Class for the individual card objects. Every card has a type that dictates its effect. These effects are triggered in this class' function
CardAction, which is called within the mouse click detection class inside of Goo.java after a valid click.

Player.java
-----------
Class representing the player (the goo monster). Holds statistics such as health and functions to modify them.

Combat.java
-----------
Class representing the "combat" between Player and Monster. Contains start function to initialize the combat; the current card arrays
for draw pile, hand, and discard pile; and a whole mess of functions related to the animation timers.

Monster.java
------------
Class representing the Monster (which is the scientist). Contains stats for the monster, the function to attack the player, and
a function to "level up" to make successive monsters stronger (which is really just the same monster with his health and image modified).


Graphics Dependent Gameplay - When creating this project we quickly identified what we viewed to be the absolute core of the computer gaming experience - the graphics. Games are inherently a visual experience, and while we can try to compliment this with tight gameplay and satisfying interactivity, at its core a game is played for the visceral optical ordeal. As such, we grounded our game in something we like to refer to as a "Graphics Dependent" gameplay loop. We built our entire system around the visual narrative we seeked to allow every single player to experience with their own eyes (or eye in some situations). From a more technical perspective, if the screen is not being updated graphically, nothing else in the program is proceeding, as all resources are invested into ensuring the graphics are ready and able to update in response to the next player input. While this certaintly lead to the game developing the unique optics that we had set out to create, it wasn't until we decided to compromise on our vision that we were able to take it further beyond anything that we had intially thought it could have been. With the help of one stackoverflow.com user named Tangens, sequences that brought real-time graphical updates and gameplay feedback began to percolate themselves throughout our code. As a result, we were able to remedy the very best aspects of our initial graphical vision with the functionality of a more real-life inspired time system.
