**GAME INSTALLATION**\
To install The game you'll need to have intelliJ installed and follow these steps as shown in this video:\
[![Watch the video](https://img.youtube.com/vi/bN8SZW8KxN8/maxresdefault.jpg)](https://www.youtube.com/watch?v=bN8SZW8KxN8)
\
\
If you can't see the video follow these steps:
- Download the Project as a zip from the repository
- Extract it and open it as a project on intelliJ\\
The next few steps are mandatory if you want to be able to be able to launch the game:\
- go in the project structure
- add a new module
- click on scala, choose the project and make sure to disable the "add sample code" setting
- go back on the project
- add fungraphic as a library
- right click on the "res" folder and mark it at the ressouce root folder

Congrats you're now able to launch the Game !

  


\
**GAME DESCRIPTION**\
 Frogger is an old platforming arcade game , the goal is to reach the end of the map by dodging the cars and jump on wood to pass a river.\
 We used the original as a reference but we added randomness like the speed of the cars, the wooden logs as well as the distance between them. When you passed the finished line the level goes up by 1, the number of car increases and the number of logs you can walk on decreases (the difficulty level in general increases).

This is the first level and the seventh to compare

<img width="500" height="1000" alt="image" src="https://github.com/user-attachments/assets/2020a706-0e9a-4a67-a57f-d31a06559120" />
<img width="500" height="1000" alt="Capture d&#39;écran 2026-01-14 091517" src="https://github.com/user-attachments/assets/b841e6cc-c1c0-4b2d-9657-ed4555688fac" />


\
\
**GAMEPLAY**
  - You can control the Frog with W/A/S/D
  - To pass the river you need to walk on the wooden logs and make your way to the finished line
  - Walking on the water or being hit by a car makes you die and lose 1 life (you have 5 at the beginning of the game)
  - When you use your 5 lives and you officially lose and you have 3 seconde to press "space" if you want to play again.

Good luck !

\
**CODE STRUCTURE**
- Main.scala -> starts the screen and launches the game. we also do the replayability here
- Game.scala -> the hearth of the game. this is were the grid and all the objects are created. this is also where the algorithm take place and where the movement is checked
- Cell.scala -> this is the body of a cell. we create an 2D array of them to render the whole game. it has a background and a type. The size is 50x50by default
- Frog.scala -> this is the class in which the frog exists it has the normal setings for it as well as the movements
- Enemy.scala -> this is for the cars in the game. it has the movement for the cars
- Wood.scala -> same thing as the enemy, but since the movements are different, we created a new class

  


     
     
