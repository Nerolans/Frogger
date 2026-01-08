import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Wood(var x:Int, var y:Int, var direction:Boolean, var display:FunGraphics, var grid:Array[Array[Cell]]) {
  //this stores the last time this enemy moved
  var lastMoved:Long = System.currentTimeMillis()
  //putting the sprite in a picture (just to have the picture preloaded)
  var picMovingObject = new GraphicsBitmap("/wood.png")
  //changing the sprite if it's the other direction
  var speed:Int = 250
  var actualX = 0

  def checkFrog(frog: Frog):Unit = {
    if(frog.x == x && frog.y == y){
      frog.draw()
    }
    if(frog.x == actualX && frog.y == y){
      var directionNew:String = frog.frogDirection
      if(direction){
        frog.moveLeft()
        frog.frogDirection = directionNew
        frog.draw()
      }
      else {
        frog.moveRight()
        frog.frogDirection = directionNew
        frog.draw()
      }
    }
  }


  def move(frog: Frog):Unit = {
    actualX = x
    var futureX = 0
    var oldX = 0

    if (direction){
      futureX = x-1
      oldX = x+1
      if(x-1 < 0) futureX = grid.length-1
      if(x+1 > grid.length-1) oldX = 0
    }
    else {
      futureX = x+1
      oldX = x-1
      if(x-1 < 0) oldX = grid.length-1
      if(x+1 > grid.length-1) futureX = 0
    }

    if(grid(oldX)(y).typeCell == "water") {
      grid(x)(y).typeCell = "water"
      grid(x)(y).drawBackground()
    }

    x = futureX
    grid(x)(y).typeCell = "wood"
    grid(x)(y).drawBackground()
    checkFrog(frog)
    //changing the last time this enemy moved
    lastMoved = System.currentTimeMillis()
  }
}
