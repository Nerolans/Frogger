import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Frog(var x:Int, var y:Int, var display:FunGraphics){
  var frogPicture:GraphicsBitmap = new GraphicsBitmap("/frogW.png")
  def getCoordinates():Array[Int] = {
    var xCoord:Int = x*50+25
    var yCoord:Int = y*50+25
    var arr:Array[Int] = Array(xCoord,yCoord)

    return arr
  }

  def draw(direction:String):Unit = {
    frogPicture = new GraphicsBitmap("/"+direction+".png")
    display.drawPicture(getCoordinates()(0), getCoordinates()(1) ,frogPicture)
  }

  def moveForward() = {
    if(y>0){
      y-=1
      draw("frogW")
    }
  }
  def moveBackward() = {
    y+=1
    draw("frogS")
  }
  def moveLeft() = {
    x-=1
    draw("frogA")
  }
  def moveRight() = {
    x+=1
    draw("frogD")
  }
}
