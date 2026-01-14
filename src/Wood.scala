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
//method to check if the frog is on the wood and moves the frog with the wood if it's the case
  def checkFrog(frog: Frog):Unit = {
    if(frog.x == x && frog.y == y){
      frog.draw()
    }
    if(frog.x == actualX && frog.y == y){
      var directionNew:String = frog.frogDirection
      if(direction){
        grid(actualX)(y).drawBackground()
        frog.moveLeft()
        frog.frogDirection = directionNew
        frog.draw()
      }
      else {
        grid(actualX)(y).drawBackground()
        frog.moveRight()
        frog.frogDirection = directionNew
        frog.draw()
      }
    }
  }

//method to move the wood and that the wood appear in the other side on the screen
  def move(frog: Frog):Unit = {
    actualX = x
    var futureX = 0
    var oldX = 0

    //check if it's going right to left
    if (direction){
      //next cell
      futureX = x-1
      //old cell
      oldX = x+1
      //if it reaches the end of the screen
      if(futureX < 0) futureX = grid.length-1
      //if it's at the last cell of the screen
      if(oldX > grid.length-1) oldX = 0
    }
    else {
      //same thing but for the other direction
      futureX = x+1
      oldX = x-1
      if(oldX < 0) oldX = grid.length-1
      if(futureX > grid.length-1) futureX = 0
    }

    //if the last cell ws water the actual is going to become water too
    if(grid(oldX)(y).typeCell == "water") {
      grid(x)(y).typeCell = "water"
      grid(x)(y).drawBackground()
    }

    //changes the actual coordinates for the next one
    x = futureX
    //make it so the program doesn't redraw the platform if it already exists (in the middle of the platform)
    if(grid(x)(y).typeCell != "wood"){
      grid(x)(y).typeCell = "wood"
      grid(x)(y).drawBackground()
    }

    //checks if the frog is on the platform
    checkFrog(frog)
    //changing the last time this enemy moved
    lastMoved = System.currentTimeMillis()
  }
}
