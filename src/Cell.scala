import hevs.graphics.FunGraphics

import java.awt.Color

class Cell (var x:Int, var y:Int, var size:Int, var display:FunGraphics){
  //checks the current state of the Cell (if an ennemy is on it or not)
  var isDangerous:Boolean = false
  //bas color for the Cell (Black)
  var backgroundColor:Color = new Color(0,0,0)

  //returns the coordinates (x and y) for the top pixel of this Cell (used to draw the background)
  def getCoordinatesTopLeft():Array[Int] = {
    val xCoord:Int = x*size
    val yCoord:Int = y*size
    val arr:Array[Int] = Array(xCoord,yCoord)
    return arr
  }
  //returns the coordinates (x and y) for the middle pixel of this Cell (used to draw the sprites)
  def getCoordinatesMiddle():Array[Int] = {
    val xCoord:Int = x*size+25
    val yCoord:Int = y*size+25
    val arr:Array[Int] = Array(xCoord,yCoord)
    return arr
  }
  //draws the background color of this cell
  def drawBackground():Unit ={
    //gets the coordinates for this cell
    val coordinates:Array[Int] = getCoordinatesTopLeft()
    //set the color of the display for the color of this cell
    display.setColor(backgroundColor)
    //draws the rectangle (in this case a square)
    display.drawFillRect(coordinates(0),coordinates(1),size,size)
  }
}
