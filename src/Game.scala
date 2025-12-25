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

  def drawBackground():Unit = {
    display.drawPicture(sizeX*sizeOfcell/2, sizeY*sizeOfcell/2, background)
  }

  def play(): Unit = {
    //display background //values are fixed because it depends on the file size
    drawBackground()
    //frog sprite at base cell
    var frog:Frog = new Frog(7,13,display)
    frog.draw("frogW")

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
      }

      override def keyReleased(e: KeyEvent): Unit = {
        isReleased = true
      }
    }
    display.setKeyManager(e)
  }
}
