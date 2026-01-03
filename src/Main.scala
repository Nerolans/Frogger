import hevs.graphics.FunGraphics

object Main extends App{
  //size of screen
  val GRAPHICS_WIDTH: Int = 750
  val GRAPHICS_HEIGHT: Int = 700
  //size of each cell. Is a square (so same size x and y)
  val sizeCell:Int = 50
  //creating the display
  val display: FunGraphics = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT)

  //creating the game and launching it
  var game1:Game = new Game(GRAPHICS_WIDTH/sizeCell, GRAPHICS_HEIGHT/sizeCell, display,sizeCell)
  game1.play()
  //test
}
