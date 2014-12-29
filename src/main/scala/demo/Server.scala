package demo

/**
 * Created by peng on 14-12-26.
 */

import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}
import java.net.InetSocketAddress
import org.jboss.netty.handler.codec.http._

object Server extends App {
  val service = new Service[HttpRequest, HttpResponse] {
    def apply(req: HttpRequest): Future[HttpResponse] = {

      println("start a http server...")
      Future.value(new DefaultHttpResponse(
        req.getProtocolVersion, HttpResponseStatus.OK))
    }

  }

  println("hello")
  val server = Http.serve(":8080", service)
  Await.ready(server)
}