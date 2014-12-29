package la.niub.finagle.thrift;

import com.twitter.finagle.ListeningServer;
import com.twitter.finagle.Service;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.builder.Server;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.thrift.ThriftServerFramedCodec;
import com.twitter.finagle.thrift.ThriftServerFramedCodecFactory;
import com.twitter.tweetservice.thriftscala.Hello;
import com.twitter.util.Await;
import com.twitter.util.TimeoutException;
import org.apache.thrift.protocol.TBinaryProtocol;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import static com.twitter.finagle.builder.ServerBuilder.safeBuild;

/**
 * Created by peng on 14-12-29.
 */
public class ThriftServer {
    public static void main(String args[]) throws TimeoutException, InterruptedException {
        //#thriftserverapi
        /*
        Hello.FutureIface impl = new HelloImpl();
        ListeningServer server = Thrift.serveIface("localhost:8080", impl);
        Await.ready(server);
        */
        //#thriftserverapi

        SocketAddress address = new InetSocketAddress(8080);

        Service myService = new HelloService(new HelloImpl(), new TBinaryProtocol.Factory());

        safeBuild(myService,
                ServerBuilder.get()
                        .codec(new ThriftServerFramedCodecFactory())
                        .bindTo(address)
                        .name("thrift server"));
    }
}
