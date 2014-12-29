package demo

import com.twitter.finagle.Thrift
import com.twitter.tweetservice.thriftscala.Hello
import com.twitter.util.{Await, Future}

/**
 * Created by peng on 14-12-27.
 */
object ThriftServer extends App {
  val server = Thrift.serveIface(":8080", new Hello.FutureIface {
    def hi() = {
      println("hello called")
      Future.value("hi")
    }
  })

  println("thrift server start...")
  Await.ready(server)
}
