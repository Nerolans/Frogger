import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Frog(var x:Int, var y:Int, var display:FunGraphics, var grid:Array[Array[Cell]]){
  var frogDirection = "frogW"
  var frogPicture:GraphicsBitmap = new GraphicsBitmap("/frogW.png")


  def draw(direction:String):Unit = {
    frogPicture = new GraphicsBitmap("/"+direction+".png")
    var coordinates:Array[Int] = grid(x)(y).getCoordinatesMiddle()
    display.drawPicture(coordinates(0), coordinates(1), frogPicture)
  }

  def moveForward() = {
    if(y>0){
      grid(x)(y).drawBackground()
      y-=1
      frogDirection = "frogW"
      draw(frogDirection)
    }
  }
  def moveBackward() = {
    grid(x)(y).drawBackground()
    y+=1
    frogDirection = "frogS"
    draw(frogDirection)
  }
  def moveLeft() = {
    grid(x)(y).drawBackground()
    x-=1
    frogDirection = "frogA"
    draw(frogDirection)
  }
  def moveRight() = {
    grid(x)(y).drawBackground()
    x+=1
    frogDirection = "frogD"
    draw(frogDirection)
  }
}
