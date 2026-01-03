import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}

class Game (var sizeX:Int, var sizeY:Int, var display:FunGraphics,var sizeOfcell:Int) {
  //check for key release (so that you can't hold down a key)
  var isReleased: Boolean = true
  //grid of Cell
  val grid:Array[Array[Cell]] = Array.ofDim(sizeX,sizeY)
  for(x<-0 until sizeX){
    for(y<-0 until sizeY){
      grid(x)(y) = new Cell(x, y, sizeOfcell, display)
      if(y == 0)grid(x)(y).backgroundColor = new Color(0,204,0)
      if(y == sizeY-1)grid(x)(y).backgroundColor = new Color(102,0,153)
      grid(x)(y).drawBackground()
    }
  }
  //level
  var level:Int = 1

  def play(): Unit = {
    //frog sprite at base cell
    var frog:Frog = new Frog(7,13,display, grid)
    frog.draw(frog.frogDirection)

    //KEY LISTENER WOOOOOOOOOOOOOOOOOOOOOOOOOOOOORKS
    var e:KeyListener = new KeyListener {
      override def keyTyped(e: KeyEvent): Unit = {
      }

      override def keyPressed(e: KeyEvent): Unit = {
        if(e.getKeyChar == 'w' && frog.y > 0 && isReleased) {
          frog.moveForward()
        }
        if(e.getKeyChar == 's' && frog.y < sizeY-1 && isReleased) {
          frog.moveBackward()
        }
        if(e.getKeyChar == 'a' && frog.x > 0 && isReleased) {
          frog.moveLeft()
        }
        if(e.getKeyChar == 'd' && frog.x < sizeX-1 && isReleased) {
          frog.moveRight()
        }
        isReleased = false
        if(grid(frog.x)(frog.y).isDangerous)gameOver()
      }

      override def keyReleased(e: KeyEvent): Unit = {
        isReleased = true
      }
    }
    display.setKeyManager(e)
    //creating ennemy "true" for left, "false" for right
    var ennemy1:Ennemy = new Ennemy(1,5,true,display,grid)

    //making the ennemy move
    while (true){
      var milTimeNow:Long = System.currentTimeMillis()
      if(milTimeNow - ennemy1.lastMoved > 250){
        ennemy1.move()
        frog.draw(frog.frogDirection)
        ennemy1.draw()
      }
      if(grid(frog.x)(frog.y).isDangerous)gameOver()

    }
  }

  def gameOver() = {
    //do something here when frog is
    println("DEAD")
  }
}
