import hevs.graphics.FunGraphics

import java.awt.event.{KeyEvent, KeyListener}

object Main extends App{
  //size of screen
  val GRAPHICS_WIDTH: Int = 750
  val GRAPHICS_HEIGHT: Int = 700
  //size of each cell. Is a square (so same size x and y)
  val sizeCell:Int = 50
  var restart:Boolean = true
  //creating the display
  val display: FunGraphics = new FunGraphics(GRAPHICS_WIDTH, GRAPHICS_HEIGHT)

  //creating the game and launching it
  do{
    println("vous avez relançé une partie")
    var game1: Game = new Game(GRAPHICS_WIDTH / sizeCell, GRAPHICS_HEIGHT / sizeCell, display, sizeCell)
    game1.play()
    //wait for the user to input if he wants to restart a new game or not
    Thread.sleep(3000)
    if(game1.yes_or_no()){restart=true}
    else{restart = false}
  }
  while(restart)
  display.clear()




}
