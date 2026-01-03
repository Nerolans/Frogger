import hevs.graphics.FunGraphics

import java.awt.Color

class Cell (var x:Int, var y:Int, var size:Int, var display:FunGraphics){
  var isDangerous:Boolean = false
  var backgroundColor:Color = new Color(0,0,0)

  def getCoordinatesTopLeft():Array[Int] = {
    var xCoord:Int = x*size
    var yCoord:Int = y*size
    var arr:Array[Int] = Array(xCoord,yCoord)
    return arr
  }
  def getCoordinatesMiddle():Array[Int] = {
    var xCoord:Int = x*size+25
    var yCoord:Int = y*size+25
    var arr:Array[Int] = Array(xCoord,yCoord)
    return arr
  }

  def drawBackground() ={
    var coordinates:Array[Int] = getCoordinatesTopLeft()
    display.setColor(backgroundColor)
    display.drawFillRect(coordinates(0),coordinates(1),size,size)
  }
}
