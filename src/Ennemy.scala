import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Ennemy (var x:Int, var y:Int, var direction:Boolean, var display:FunGraphics, var grid:Array[Array[Cell]]) {
  var lastMoved:Long = System.currentTimeMillis()
  var ennemyPicture = new GraphicsBitmap("/ennemyR.png")

  if(direction){
    ennemyPicture = new GraphicsBitmap("/ennemyL.png")
  }

  def draw() = {
    display.drawPicture(grid(x)(y).getCoordinates()(0), grid(x)(y).getCoordinates()(1), ennemyPicture)
  }
  def move():Unit = {
    if (direction){
      grid(x)(y).isDangerous = false
      x-=1
      if(x<0){x = grid.length-1}
      grid(x)(y).isDangerous = true
    }
    else {
      grid(x)(y).isDangerous = false
      x+=1
      if(x>0){x = 0}
      grid(x)(y).isDangerous = true
    }
    lastMoved = System.currentTimeMillis()
  }
}
