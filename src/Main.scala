import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import java.awt.event.KeyListener

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
  //frog sprite
  val frogPicture:GraphicsBitmap = new GraphicsBitmap("C:\\Users\\guigh\\Desktop\\IntelliJ\\Frogger\\res\\frog.png")


  //14 * 50
  //cellule 50 50

  //var grid: Cell = new Cell()
  for(x<-0 to widthCell){
    for(y<-0 to heightCell){
      grid(x)(y) = new Cell(x, y)
    }
  }

  display.drawPicture(0,0,frogPicture)

  display.setKeyManager(KeyListener)

}
