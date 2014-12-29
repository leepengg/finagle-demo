package demo

import com.twitter.finagle.Thrift
import com.twitter.tweetservice.thriftscala.Hello
import com.twitter.util.Await

/**
 * Created by peng on 14-12-27.
 */
object ThriftClient extends App {
  val client = Thrift.newIface[Hello.FutureIface]("127.0.0.1:8080")

  println(client)
  //client.hi()

  var response = client.hi() onSuccess { response =>
    println("Received response: " + response)
  } onFailure { exc =>
    println("failed :-(", exc)
  }

  Await.ready(response)
}
