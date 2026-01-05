import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}

class Game (var sizeX:Int, var sizeY:Int, var display:FunGraphics,var sizeOfcell:Int) {
  //check if the game is on
  private var isOn:Boolean = true
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
          if(frog.y == 0)victory()
        }
      }

      override def keyReleased(e: KeyEvent): Unit = {
        //goes to true when the key is released
        isReleased = true
      }
    }
    display.setKeyManager(e)
    //creating the enemies "true" for left, "false" for right -> the number will probably be the same for each level just the speed and direction will change so we may need a function for this
    var enemies:Array[Enemy] = createEnemies(5, true, 3, 250)
    //add others



    while(isOn){
      moveEnemies(enemies)
    }
  }

  //do something here when the frog and an ennemy came into contact
  def gameOver():Unit = {
    //do something here when frog is
    println("DEAD")
    isOn = false
  }
  def victory():Unit = {
   //use when y == 0
    println("bien joué le sang")
    isOn = false

  }
  //to create the ennemies

  def createEnemies(line:Int, direction:Boolean, distance:Int, speed:Int):Array[Enemy] = {
    val pop: Int = sizeY / distance
    val arr:Array[Enemy] = new Array[Enemy](pop)
    for(i<-0 until pop){
      if(direction){
        //TOTEST
        arr(i) = new Enemy(i*distance,5,direction,display,grid)
      }
      else{
        arr(i) = new Enemy(sizeX-(i*distance),5,direction,display,grid)
      }
      arr(i).speed = speed
    }
    return arr
  }

  def moveEnemies(enemies:Array[Enemy]):Unit = {
    //gets The time
    var milTimeNow:Long = System.currentTimeMillis()
    //checks if this enemy hasn't moved for more than a certain value (in ms)
    for(i<- enemies.indices){
      if(milTimeNow - enemies(i).lastMoved > enemies(i).speed){
        enemies(i).move()
      }
    }



    //checks if the enemy has it the frog
    if(grid(frog.x)(frog.y).isDangerous)gameOver()
  }
}
