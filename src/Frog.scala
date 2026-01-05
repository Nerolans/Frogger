import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Frog(var x:Int, var y:Int, var display:FunGraphics, var grid:Array[Array[Cell]]){
  //base direction for the frog
  var frogDirection = "frogW"
  //putting the sprite in a picture (just to have the picture preloaded)
  var frogPicture:GraphicsBitmap = new GraphicsBitmap("/frogW.png")

  //draws the frog in the correct direction
  def draw(direction:String):Unit = {
    //changing the sprite
    frogPicture = new GraphicsBitmap("/"+direction+".png")
    //storing the coordinates
    val coordinates:Array[Int] = grid(x)(y).getCoordinatesMiddle()
    //drawing the frog
    display.drawPicture(coordinates(0), coordinates(1), frogPicture)
  }

  def moveForward():Unit = {
    //checks if the frog is not going outside the screen
    if(y>0){
      //the cell draws his background color over the frog (deletes the frog from the screen)
      grid(x)(y).drawBackground()
      //changing the coordinate of the frog
      y-=1
      //changing the direction of the frog
      frogDirection = "frogW"
      //draws the frog at the new coordinates
      draw(frogDirection)
    }
  }
  def moveBackward():Unit = {
    if(y<grid(0).length-1) {
      grid(x)(y).drawBackground()
      y += 1
      frogDirection = "frogS"
      draw(frogDirection)
    }
  }
  def moveLeft():Unit = {
    if(x>0) {
      grid(x)(y).drawBackground()
      x -= 1
      frogDirection = "frogA"
      draw(frogDirection)
    }
  }
  def moveRight():Unit = {
    if(x<grid.length-1) {
      grid(x)(y).drawBackground()
      x += 1
      frogDirection = "frogD"
      draw(frogDirection)
    }
  }
  def isDead():Boolean = {
    if(grid(x)(y).isDangerous)true
    else{
      false
    }
  }
}
