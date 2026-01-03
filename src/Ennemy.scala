import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Ennemy (var x:Int, var y:Int, var direction:Boolean, var display:FunGraphics, var grid:Array[Array[Cell]]) {
  var lastMoved:Long = System.currentTimeMillis()
  var ennemyPicture = new GraphicsBitmap("/ennemyR.png")

  if(direction){
    ennemyPicture = new GraphicsBitmap("/ennemyL.png")
  }

  def draw() = {
    var coordinates:Array[Int] = grid(x)(y).getCoordinatesMiddle()
    display.drawPicture(coordinates(0), coordinates(1), ennemyPicture)
  }
  def move():Unit = {
    if (direction){
      grid(x)(y).isDangerous = false
      grid(x)(y).drawBackground()
      x-=1
      if(x<0){x = grid.length-1}
      grid(x)(y).isDangerous = true
    }
    else {
      grid(x)(y).isDangerous = false
      grid(x)(y).drawBackground()
      x+=1
      if(x>0){x = 0}
      grid(x)(y).isDangerous = true
    }
    lastMoved = System.currentTimeMillis()
  }
}
