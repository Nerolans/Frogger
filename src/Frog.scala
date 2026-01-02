import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Frog(var x:Int, var y:Int, var display:FunGraphics, var grid:Array[Array[Cell]]){
  var frogDirection = "frogW"
  var frogPicture:GraphicsBitmap = new GraphicsBitmap("/frogW.png")


  def draw(direction:String):Unit = {
    frogPicture = new GraphicsBitmap("/"+direction+".png")
    display.drawPicture(grid(x)(y).getCoordinates()(0), grid(x)(y).getCoordinates()(1) ,frogPicture)
  }

  def moveForward() = {
    if(y>0){
      y-=1
      frogDirection = "frogW"
      draw(frogDirection)
    }
  }
  def moveBackward() = {
    y+=1
    frogDirection = "frogS"
    draw(frogDirection)
  }
  def moveLeft() = {
    x-=1
    frogDirection = "frogA"
    draw(frogDirection)
  }
  def moveRight() = {
    x+=1
    frogDirection = "frogD"
    draw(frogDirection)
  }
}
