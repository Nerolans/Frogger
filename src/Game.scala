import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.{Color, Font}
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
  //number of lives for the player
  var lives:Int = 5
  //base speed for the car enemies
  var game_over: String = "you lost !"
  var restart_message:String = "Press space to restart"
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

  def renderScore():Unit = {
    var coord = grid(0)(0).getCoordinatesMiddle()
    display.drawFancyString(coord(0),coord(1)+6,level.toString,Color.WHITE,30)
    for(i<-0 until lives){
      coord = grid(sizeX-i-1)(0).getCoordinatesMiddle()
      var pic = new GraphicsBitmap("/hearth.png")
      display.drawPicture(coord(0), coord(1), pic)
    }
  }
//method to start a game
  def play(): Unit = {
    if(lives > 0) {
      Thread.sleep(300)
      createGrid()
      arrayOfCarEnnemies = groupCarsEnemies()
      arrayofplatform = groupPlatforms()
      frog.frogDirection = "frogW"
      frog.place(7, 13)
      renderScore()
      isOn = true
      isRunning()
    }
    else {
      //Gameoverscreen here
      display.drawFancyString(310,300,game_over,Color.BLACK,40)
      display.drawFancyString(200,375,restart_message,Color.BLACK,40)


    }
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
    if(isOn)lives -= 1
    isOn = false
    frog.isDead()
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
      if (sizePlatform < 2) sizePlatform = 3 - (Math.random()*1.5).toInt
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
// method to create the platform on water
  def createPlatform(line:Int, direction:Boolean, size:Int, speed:Int): Array[Wood] = {
    var platform : Array[Wood] = new Array[Wood](size)
    for(i <- 0 until size){
     // we create the platorm depending on what are their direction
      if(direction) {
      // direction left
        platform(i) = new Wood(i, line, true, display, grid)
        grid(i)(line).typeCell = "wood"
        grid(i)(line).drawBackground()
      }
      else{
      // direction right
        platform(i) = new Wood(i, line, false, display, grid)
        grid(i)(line).typeCell = "wood"
        grid(i)(line).drawBackground()
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
  //method to move the platform on the water
  def moveWood(objects:Array[Wood]):Unit = {
    //gets The time
    var milTimeNow:Long = System.currentTimeMillis()
    //checks if this enemy hasn't moved for more than a certain value (in ms)
    if(milTimeNow - objects(0).lastMoved > objects(0).speed){
      if(objects(0).direction){
        for(i<- objects.indices){
          objects(i).move(frog)
        }
      }
      else {
        for (i <- objects.length - 1 to 0 by -1) {
          objects(i).move(frog)
        }
      }
    }
  }
}
