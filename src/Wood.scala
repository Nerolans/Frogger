import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Wood(var x:Int, var y:Int, var direction:Boolean, var display:FunGraphics, var grid:Array[Array[Cell]]) {
  //this stores the last time this enemy moved
  var lastMoved:Long = System.currentTimeMillis()
  //putting the sprite in a picture (just to have the picture preloaded)
  var picMovingObject = new GraphicsBitmap("/wood.png")
  //changing the sprite if it's the other direction
  var speed:Int = 250
  var actualX = x

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
    if (direction){
      var newX = x-1
      var oldX = x+1
      if(x-1 < 0) newX = grid.length-1
      if(x+1 > grid.length-1) oldX = 0
      if(grid(newX)(y).typeCell == "water"){
        grid(newX)(y).typeCell = "wood"
        grid(newX)(y).drawBackground()
      }
      if(grid(oldX)(y).typeCell == "water") {
        grid(x)(y).typeCell = "water"
        grid(x)(y).drawBackground()
      }
      x = newX
    }
    else {
      var newX = x+1
      var oldX = x-1
      if(x-1 < 0) oldX = grid.length-1
      if(x+1 > grid.length-1) newX = 0
      if(grid(newX)(y).typeCell == "water"){
        grid(newX)(y).typeCell = "wood"
        grid(newX)(y).drawBackground()
      }
      if(grid(oldX)(y).typeCell == "water") {
        grid(x)(y).typeCell = "water"
        grid(x)(y).drawBackground()
      }
      x = newX
    }
    if(y == 3){
      println(x +"  "+y)
      println(grid(x)(y).typeCell)
    }
    checkFrog(frog)
    //changing the last time this enemy moved
    lastMoved = System.currentTimeMillis()
  }
}
