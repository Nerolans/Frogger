import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.event.{KeyEvent, KeyListener}

class Game (var sizeX:Int, var sizeY:Int, var display:FunGraphics, var background:GraphicsBitmap, var sizeOfcell:Int) {
  //check for key release (so that you can't hold down a key)
  var isReleased: Boolean = true
  //grid of Cell
  val grid:Array[Array[Cell]] = Array.ofDim(sizeX,sizeY)
  for(x<-0 until sizeX){
    for(y<-0 until sizeY){
      grid(x)(y) = new Cell(x, y, sizeOfcell)
    }
  }
  //level
  var level:Int = 1


  def drawBackground():Unit = {
    display.drawPicture(sizeX*sizeOfcell/2, sizeY*sizeOfcell/2, background)
  }

  def play(): Unit = {
    //display background //values are fixed because it depends on the file size
    drawBackground()
    //frog sprite at base cell
    var frog:Frog = new Frog(7,13,display, grid)
    frog.draw(frog.frogDirection)

    //KEY LISTENER WOOOOOOOOOOOOOOOOOOOOOOOOOOOOORKS
    var e:KeyListener = new KeyListener {
      override def keyTyped(e: KeyEvent): Unit = {
      }

      override def keyPressed(e: KeyEvent): Unit = {
        if(e.getKeyChar == 'w' && frog.y > 0 && isReleased) {
          drawBackground()
          frog.moveForward()
        }
        if(e.getKeyChar == 's' && frog.y < sizeY-1 && isReleased) {
          drawBackground()
          frog.moveBackward()
        }
        if(e.getKeyChar == 'a' && frog.x > 0 && isReleased) {
          drawBackground()
          frog.moveLeft()
        }
        if(e.getKeyChar == 'd' && frog.x < sizeX-1 && isReleased) {
          drawBackground()
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
        drawBackground()
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
