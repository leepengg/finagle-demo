package la.niub.finagle.thrift;

import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.stats.NullStatsReceiver;
import com.twitter.finagle.thrift.ClientId;
import com.twitter.finagle.thrift.ThriftClientFramedCodecFactory;
import com.twitter.finagle.zipkin.thrift.ZipkinTracer;
import com.twitter.tweetservice.thriftscala.B;
import com.twitter.tweetservice.thriftscala.Hello;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;
import org.apache.thrift.protocol.TBinaryProtocol;

import java.net.InetSocketAddress;

/**
 * Created by peng on 14-12-29.
 */
public class BImpl implements B.FutureIface {
    @Override
    public Future<String> b() {
        System.out.println("b called");

        ZipkinTracer zipkinTracer = (ZipkinTracer) ZipkinTracer.mk("172.16.7.213", 9410, new NullStatsReceiver(), 1);
        Service service = ClientBuilder.safeBuild(ClientBuilder.get()
                .hosts(new InetSocketAddress(8080))
                .codec(new ThriftClientFramedCodecFactory(new ClientId("1")))
                .tracer(zipkinTracer)
                .name("B impl")
                .hostConnectionLimit(100)); // Must be more than 1 to enable parallel execution


        Hello.FinagledClient client =
                new Hello.FinagledClient(service, new TBinaryProtocol.Factory(), "thrift client", new NullStatsReceiver());

//        client.hi().addEventListener(new FutureEventListener<String>() {
//            @Override
//            public void onSuccess(String value) {
//                System.out.println(value);
//            }
//
//            @Override
//            public void onFailure(Throwable cause) {
//                System.out.println("Exception! " + cause.toString());
//            }
//        });
//
//        String ret = client.hi().get();
//
//        System.out.println("block call" + ret);

        return Future.value(client.hi().get());
    }
}
