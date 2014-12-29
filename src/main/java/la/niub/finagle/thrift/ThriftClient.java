package la.niub.finagle.thrift;

import com.twitter.finagle.Service;
import com.twitter.finagle.Thrift;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.stats.NullStatsReceiver;
import com.twitter.finagle.stats.StatsReceiver;
import com.twitter.finagle.thrift.ClientId;
import com.twitter.finagle.thrift.ThriftClientFramedCodecFactory;
import com.twitter.tweetservice.thriftscala.Hello;
import com.twitter.util.Await;
import com.twitter.util.Function;
import com.twitter.util.Future;
import com.twitter.util.FutureEventListener;
import org.apache.thrift.protocol.TBinaryProtocol;
import scala.runtime.BoxedUnit;

import java.net.InetSocketAddress;

/**
 * Created by peng on 14-12-29.
 */
public class ThriftClient {
    public static void main(String args[]) throws Exception {
        //#thriftclientapi
//        Hello.FutureIface client = Thrift.newIface("localhost:8080", Hello.FutureIface.class);
//        Future<String> response = client.hi().onSuccess(new Function<String, BoxedUnit>() {
//            @Override
//            public BoxedUnit apply(String response) {
//                System.out.println("Received response: " + response);
//                return null;
//            }
//        });
//
//        Await.result(response);
        //#thriftclientapi

        Service service = ClientBuilder.safeBuild(ClientBuilder.get()
                .hosts(new InetSocketAddress(8080))
                .codec(new ThriftClientFramedCodecFactory(new ClientId("1")))
                .hostConnectionLimit(100)); // Must be more than 1 to enable parallel execution


        Hello.FinagledClient client =
                new Hello.FinagledClient(service, new TBinaryProtocol.Factory(), "thrift client", new NullStatsReceiver());

        client.hi().addEventListener(new FutureEventListener<String>() {
            @Override
            public void onSuccess(String value) {
                System.out.println(value);
            }

            @Override
            public void onFailure(Throwable cause) {
                System.out.println("Exception! " + cause.toString());
            }
        });

        System.out.println("block call" + client.hi().get());


    }

}
