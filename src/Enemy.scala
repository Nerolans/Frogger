import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

class Enemy (var x:Int, var y:Int, var direction:Boolean, var display:FunGraphics, var grid:Array[Array[Cell]]) {
  //this stores the last time this enemy moved
  var lastMoved:Long = System.currentTimeMillis()
  //putting the sprite in a picture (just to have the picture preloaded)
  var enemyPicture = new GraphicsBitmap("/enemyR.png")
  //changing the sprite if it's the other direction
  var speed:Int = 250
  if(direction){
    enemyPicture = new GraphicsBitmap("/enemyL.png")
  }

  //making the enemy go to the next Cell
  def move():Unit = {
    //checking the direction
    if (direction){
      //make the old Cell not dangerous anymore
      grid(x)(y).isDangerous = false
      //the cell draws his background color over the enemy (deletes the enemy from the screen)
      grid(x)(y).drawBackground()
      //changes the coordinates
      x-=1
      //checks if the ennemy is going outside the screen and making him go back at the beginning if it's the case
      if(x < 0){x = grid.length-1}
      //making the current cell dangerous
      grid(x)(y).isDangerous = true
    }
    def movePlatform():Unit = {
      //checking the direction
      if (direction){
        //make the old Cell not dangerous anymore
        grid(x)(y).isDangerous = true
        //the cell draws his background color over the enemy (deletes the enemy from the screen)
        grid(x)(y).drawBackground()
        //changes the coordinates
        x-=1
        //checks if the ennemy is going outside the screen and making him go back at the beginning if it's the case
        if(x < 0){x = grid.length-1}
        //making the current cell dangerous
        grid(x)(y).isDangerous = false
      }
      else {
        grid(x)(y).isDangerous = true
        grid(x)(y).drawBackground()
        x+=1
        if(x > grid.length-1){x = 0}
        grid(x)(y).isDangerous = false
      }
      //storing the coordinates
      val coordinates:Array[Int] = grid(x)(y).getCoordinatesMiddle()
      //drawing the enemy
      display.drawPicture(coordinates(0), coordinates(1), enemyPicture)
      //changing the last time this enemy moved
      lastMoved = System.currentTimeMillis()
    }
  }
}
