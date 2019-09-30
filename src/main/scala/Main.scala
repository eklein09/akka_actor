import akka.actor.{Actor, ActorSystem, Props, ActorLogging}

object HelloActor {
  def props(): Props = Props(new HelloActor)
}

class HelloActor extends Actor with ActorLogging {

  def factorial(x: Int): Int = {
    def factorialTimes(forFact: Int, times: Int): Int = {
      if (forFact==1)
        times
      else
        factorialTimes(forFact-1, times * forFact)
    }
    if (x==0)
      1
    if (x==1)
      1
    else
      factorialTimes(x, 1)
  }

  override def receive = {
    case x:Int => println(factorial(x))
  }
}

//object PrinterActor {
//  def props(): Props = Props(new PrinterActor)
//}
//
//class PrinterActor extends Actor with ActorLogging {
//
//  override def receive = {
//    case x => println(x)
//  }
//}

object Main extends App {
  val system = ActorSystem("HelloSystem")

  val helloActor = system.actorOf(HelloActor.props(), name= "helloactor")
  val helloActor2 = system.actorOf(HelloActor.props(), name= "helloactor2")

  helloActor ! 9
  helloActor2 ! 2

//  system.terminate()
}
