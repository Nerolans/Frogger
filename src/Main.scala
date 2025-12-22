import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap


import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}

object Main extends App{
  //size of screen
  val GRAPHICS_WIDTH: Int = 700
  val GRAPHICS_HEIGHT: Int = 700
  //size of each cell
  val widthCell:Int = 50
  val heightCell:Int = 50
  //grid of Cell
  val grid:Array[Array[Cell]] = Array.ofDim(widthCell,heightCell)
  //screen
  val display: FunGraphics = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT)
  //frog sprite //WOOOOORKS YEEEES
  val frogPicture:GraphicsBitmap = new GraphicsBitmap("/frog.png")
  display.drawPicture(25,25, frogPicture)






  //KEY LISTENER WORK IN PROGRESS
  var e:KeyListener = new KeyListener {
    override def keyTyped(e: KeyEvent): Unit = ???

    override def keyPressed(e: KeyEvent): Unit = ???

    override def keyReleased(e: KeyEvent): Unit = ???
  }
  display.setKeyManager(e)

  e.
  //if(e.keyPressed(KeyEvent)==KeyEvent.VK_K)println("no way this is working")









  //14 * 50
  //cellule 50 50

  //var grid: Cell = new Cell()
  for(x<-0 to widthCell){
    for(y<-0 to heightCell){
      grid(x)(y) = new Cell(x, y)
    }
  }



}
