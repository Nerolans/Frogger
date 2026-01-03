import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Ennemy (var x:Int, var y:Int, var direction:Boolean, var display:FunGraphics, var grid:Array[Array[Cell]]) {
  //this stores the last time this ennemy moved
  var lastMoved:Long = System.currentTimeMillis()
  //putting the sprite in a picture (just to have the picture preloaded)
  var ennemyPicture = new GraphicsBitmap("/ennemyR.png")
  //changing the sprite if it's the other direction
  if(direction){
    ennemyPicture = new GraphicsBitmap("/ennemyL.png")
  }

  //draws the ennemy at the current coordinates
  def draw():Unit = {
    //storing the coordinates
    val coordinates:Array[Int] = grid(x)(y).getCoordinatesMiddle()
    //drawing the ennemy
    display.drawPicture(coordinates(0), coordinates(1), ennemyPicture)
  }
  //making the ennemy go to the next Cell
  def move():Unit = {
    //checking the direction
    if (direction){
      //make the old Cell not dangerous anymore
      grid(x)(y).isDangerous = false
      //the cell draws his background color over the ennemy (deletes the ennemy from the screen)
      grid(x)(y).drawBackground()
      //changes the coordinates
      x-=1
      //checks if the ennemy is going outside the screen and making him go back at the beginning if it's the case
      if(x<0){x = grid.length-1}
      //making the current cell dangerous
      grid(x)(y).isDangerous = true
    }
    else {
      grid(x)(y).isDangerous = false
      grid(x)(y).drawBackground()
      x+=1
      if(x>0){x = 0}
      grid(x)(y).isDangerous = true
    }
    //changing the last time this ennemy moved
    lastMoved = System.currentTimeMillis()
  }
}
