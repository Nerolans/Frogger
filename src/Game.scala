import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}

class Game (var sizeX:Int, var sizeY:Int, var display:FunGraphics,var sizeOfcell:Int) {
  //check for key release (so that you can't hold down a key)
  private var isReleased: Boolean = true
  //creating the grid that contains all the Cells with the right size
  val grid:Array[Array[Cell]] = Array.ofDim(sizeX,sizeY)
  //creating each Cell inside of the grid
  for(x<-0 until sizeX){
    for(y<-0 until sizeY){
      //creating the cell
      grid(x)(y) = new Cell(x, y, sizeOfcell, display)
      //putting the right Color for the Cell (green Line and Purple Line)
      if(y == 0)grid(x)(y).backgroundColor = new Color(0,204,0)
      if(y == sizeY-1)grid(x)(y).backgroundColor = new Color(102,0,153)
      //drawing the Cell so the background is drawn when the game starts
      grid(x)(y).drawBackground()
    }
  }
  //level TO USE FOR LATER (makes the enemies go faster the higher the level is) ///////////////////////////////////////////////////////////////////////
  var level:Int = 1

  def play(): Unit = {
    //creating the Frog and drawing it at the Start
    var frog:Frog = new Frog(7,13,display, grid)
    frog.draw(frog.frogDirection)

    //KEY LISTENER -> goes here whenever the keys listed under are typed
    var e:KeyListener = new KeyListener {
      override def keyTyped(e: KeyEvent): Unit = {
      }

      override def keyPressed(e: KeyEvent): Unit = {
        //when w is pressed the frog goes forward
        //also checks the isReleased Boolean (so that you can't hold down a key)
        //the game is played with "wasd"
        if(isReleased) {
          if (e.getKeyChar == 'w') {
            frog.moveForward()
          }
          if (e.getKeyChar == 's') {
            frog.moveBackward()
          }
          if (e.getKeyChar == 'a') {
            frog.moveLeft()
          }
          if (e.getKeyChar == 'd') {
            frog.moveRight()
          }
          //goes to false when the key is pressed
          isReleased = false
          //checks if the frog hit a car when he moved
          if(grid(frog.x)(frog.y).isDangerous)gameOver()
        }
      }

      override def keyReleased(e: KeyEvent): Unit = {
        //goes to true when the key is released
        isReleased = true
      }
    }
    display.setKeyManager(e)
    //creating the ennemies "true" for left, "false" for right -> the number will probably be the same for each level just the speed and direction will change so we may need a function for this
    var ennemy1:Ennemy = new Ennemy(1,5,true,display,grid)
    //add others



    //making the ennemies move -> we may need as function for this
    while (true){
      //gets The time
      var milTimeNow:Long = System.currentTimeMillis()
      //checks if this ennemy hasn't moved for more than a certain value (in ms)
      if(milTimeNow - ennemy1.lastMoved > 250){
        //ennemy moves
        ennemy1.move()
        //ennemy is drawn
        ennemy1.draw()
        //we may move that in a single function
      }
      //checks if the ennemy has it the frog
      if(grid(frog.x)(frog.y).isDangerous)gameOver()

    }
  }

  //do something here when the frog and an ennemy came into contact
  def gameOver():Unit = {
    //do something here when frog is
    println("DEAD")
  }
}
