import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap
import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}

object Main extends App{
  //size of screen
  val GRAPHICS_WIDTH: Int = 750
  val GRAPHICS_HEIGHT: Int = 700
  //size of each cell. Is a square (so same size x and y)
  val sizeCell:Int = 50
  //Display
  val display: FunGraphics = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT)

  //game ON
  var game1:Game = new Game(GRAPHICS_WIDTH/sizeCell, GRAPHICS_HEIGHT/sizeCell, display,sizeCell)
  println(GRAPHICS_WIDTH/sizeCell)
  game1.play()
}
