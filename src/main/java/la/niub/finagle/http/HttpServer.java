package la.niub.finagle.http;

import java.net.InetSocketAddress;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.http.Http;
import com.twitter.util.Future;
import com.twitter.util.FutureTransformer;

/**
 * Created by peng on 14-12-30.
 */
public class HttpServer extends Service<HttpRequest, HttpResponse> {
    public Future<HttpResponse> apply(HttpRequest request) {

        Future<String> contentFuture = getContentAsync(request);

        return contentFuture.transformedBy(new FutureTransformer<String, HttpResponse>() {
                                               @Override
                                               public HttpResponse map(String content) {
                                                   HttpResponse httpResponse =
                                                           new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
                                                   httpResponse.setContent(ChannelBuffers.wrappedBuffer(content.getBytes()));
                                                   return httpResponse;
                                               }

                                               @Override
                                               public HttpResponse handle(Throwable throwable) {
                                                   HttpResponse httpResponse =
                                                           new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.SERVICE_UNAVAILABLE);
                                                   httpResponse.setContent(ChannelBuffers.wrappedBuffer(throwable.toString().getBytes()));
                                                   return httpResponse;
                                               }
                                           }
        );
    }

    //  TODO
    private Future<String> getContentAsync(HttpRequest request) {
        return null;
    }

    public static void main(String[] args) {
        ServerBuilder.safeBuild(new HttpServer(),
                ServerBuilder.get()
                        .codec(Http.get())
                        .name("HTTPServer")
                        .bindTo(new InetSocketAddress("localhost", 8080)));

    }
}



