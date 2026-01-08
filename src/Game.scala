import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color
import java.awt.event.{KeyEvent, KeyListener}

class Game (var sizeX:Int, var sizeY:Int, var display:FunGraphics,var sizeOfcell:Int) {
  //check if the game is on
  private var isOn:Boolean = true
  //check for key release (so that you can't hold down a key)
  private var isReleased: Boolean = true
  //creating the grid that contains all the Cells with the right size
  val grid:Array[Array[Cell]] = Array.ofDim(sizeX,sizeY)
  //creating the Frog and drawing it at the Start
  var frog:Frog = new Frog(7,13,display, grid)
  //level TO USE FOR LATER (makes the enemies go faster the higher the level is) ///////////////////////////////////////////////////////////////////////
  var level:Int = 1
  //base speed for the car enemies
  val baseSpeed = 500
  //colors
  var score:Color = new Color(0,0,0)
  var endLine:Color = new Color(218,112,214)
  var water:Color = new Color(135,206,250)
  var safeLine:Color = new Color(0,150,0)
  var road:Color = new Color(144,144,144)
  //number of enemy car
  var arrayOfCarEnnemies:Array[Array[Enemy]] = new Array[Array[Enemy]](5)
  var arrayofplatform :Array[Array[Wood]] = new Array[Array[Wood]](5)


  //KEY LISTENER -> goes here whenever the keys listed under are typed
  var e:KeyListener = new KeyListener {
    override def keyTyped(e: KeyEvent): Unit = {
    }

    override def keyPressed(e: KeyEvent): Unit = {
      //when w is pressed the frog goes forward
      //also checks the isReleased Boolean (so that you can't hold down a key)
      //the game is played with "wasd"
      if(isReleased && isOn) {
        if (e.getKeyChar == 'w') {
          frog.moveForward()
          frog.draw()
        }
        if (e.getKeyChar == 's') {
          frog.moveBackward()
          frog.draw()
        }
        if (e.getKeyChar == 'a') {
          frog.moveLeft()
          frog.draw()
        }
        if (e.getKeyChar == 'd') {
          frog.moveRight()
          frog.draw()
        }
        //goes to false when the key is pressed
        isReleased = false
        //checks if the frog hit a car when he moved
        if(grid(frog.x)(frog.y).isDangerous())gameOver()
        if(frog.y == 1)victory()
      }
    }

    override def keyReleased(e: KeyEvent): Unit = {
      //goes to true when the key is released
      isReleased = true
    }
  }
  display.setKeyManager(e)




  def createGrid():Unit = {
    //creating each Cell inside the grid
    for(x<-0 until sizeX){
      for(y<-0 until sizeY){
        //creating the cell
        grid(x)(y) = new Cell(x, y, sizeOfcell, display)
        //score line
        if(y == 0)grid(x)(y).backgroundColor = score
        //end line
        if(y == 1)grid(x)(y).backgroundColor = endLine
        //Water
        for(i<-8 to 12){
          if(y == sizeY-i){grid(x)(y).backgroundColor = water
            grid(x)(y).typeCell = "water" }
        }
        //safe line
        if(y == sizeY-7)grid(x)(y).backgroundColor = safeLine
        //Grass
        for(i<-2 to 6){
          if(y == sizeY-i)grid(x)(y).backgroundColor = road
        }
        //beginning
        if(y == sizeY-1)grid(x)(y).backgroundColor = safeLine
        //drawing the Cell so the background is drawn when the game starts
        grid(x)(y).drawBackground()
      }
    }
  }

  def play(): Unit = {
    arrayOfCarEnnemies = groupCarsEnemies()
    arrayofplatform = groupPlatforms()
    createGrid()
    frog.frogDirection = "frogW"
    frog.place(7,13)
    isOn = true
    isRunning()
  }
  def isRunning():Unit = {
    while(isOn){
      for(i<-arrayOfCarEnnemies.indices){
        moveEnemy(arrayOfCarEnnemies(i))
      }
      for(i<-arrayofplatform.indices){
        moveWood(arrayofplatform(i))
      }
      if(frog.isDead())gameOver()
    }
    play()
  }


  //do something here when the frog and an enemy came into contact
  def gameOver():Unit = {
    //do something here when frog is dead
    isOn = false
  }
  def victory():Unit = {
    //use when y == 0
    isOn = false
    level +=1
  }

  def groupCarsEnemies():Array[Array[Enemy]] = {
    //here you can manipulate the number of cars and all their parameters //number is fixed here //some setting will change because of the level
    var arr = new Array[Array[Enemy]](5)
    for(i<-arr.indices){
      var speed:Int = baseSpeed-(Math.random()*150+20*level).toInt
      if(speed < 50)speed = 50
      var distanceCar = 10-((Math.random()*1.5).toInt + level)
      if (distanceCar < 3) distanceCar = 4 - (Math.random()*1.5).toInt
      if (i%2 == 0){
        arr(i) = createEnemies(8+i, true, distanceCar, speed)
      }
      else{
        arr(i) = createEnemies(8+i, false, distanceCar, speed)
      }
    }
    return arr
  }
  def groupPlatforms():Array[Array[Wood]] = {
    //here you can manipulate the number of cars and all their parameters //number is fixed here //some setting will change because of the level
    var arr = new Array[Array[Wood]](5)
    for(i<-arr.indices){
      var speed:Int = baseSpeed-(Math.random()*150+20*level).toInt
      if(speed < 50)speed = 50
      var sizePlatform = 10-((Math.random()*1.5).toInt + level)
      println(sizePlatform)
      if (sizePlatform < 2) sizePlatform = 2 - (Math.random()*1.5).toInt
      if (i%2 == 0){
        arr(i) = createPlatform(2+i, true, sizePlatform, speed)
      }
      else{
        arr(i) = createPlatform(2+i, false, sizePlatform, speed)
      }
    }
    return arr
  }
  //to create the ennemies
  def createEnemies(line:Int, direction:Boolean, distance:Int, speed:Int):Array[Enemy] = {
    val pop: Int = sizeX / distance
    val arr:Array[Enemy] = new Array[Enemy](pop)
    for(i<-0 until pop){
      if(direction){
        arr(i) = new Enemy((sizeX-1)-i*distance,line,direction,display,grid)
      }
      else{
        arr(i) = new Enemy(i*distance,line,direction,display,grid)
      }
      arr(i).speed = speed
    }
    return arr
  }

  def createPlatform(line:Int, direction:Boolean, size:Int, speed:Int): Array[Wood] = {
    var platform : Array[Wood] = new Array[Wood](size)
    for(i <- 0 until size){
      if(direction) {
        platform(i) = new Wood(i, line, direction, display, grid)
      }
      else{
        platform(i) = new Wood(i+4, line, direction, display, grid)
      }
      platform(i).speed = speed
    }
    platform
  }

  def moveEnemy(objects:Array[Enemy]):Unit = {
    //gets The time
    var milTimeNow:Long = System.currentTimeMillis()
    //checks if this enemy hasn't moved for more than a certain value (in ms)
    if(milTimeNow - objects(0).lastMoved > objects(0).speed){
      for(i<- objects.indices){
        objects(i).move()
      }
    }
  }

  def moveWood(objects:Array[Wood]):Unit = {
    //gets The time
    var milTimeNow:Long = System.currentTimeMillis()
    //checks if this enemy hasn't moved for more than a certain value (in ms)
    if(milTimeNow - objects(0).lastMoved > objects(0).speed){
      for(i<- objects.indices){
        objects(i).move(frog)
      }
    }
  }
}
