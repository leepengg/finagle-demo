package demo

/**
 * Created by peng on 14-12-27.
 */

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.http._
import com.twitter.finagle.builder.{ClientBuilder, ServerBuilder}

object Proxy extends App {
  val client: Service[HttpRequest, HttpResponse] =
    Http.newService("www.google.com:80")

  val server = Http.serve(":8080", client)
  Await.ready(server)
}
