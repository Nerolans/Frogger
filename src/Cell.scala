class Cell (var x:Int, var y:Int, var size:Int){
  var isDangerous:Boolean = false

  def getCoordinates():Array[Int] = {
    var xCoord:Int = x*size+25
    var yCoord:Int = y*size+25
    var arr:Array[Int] = Array(xCoord,yCoord)
    return arr
  }
}
