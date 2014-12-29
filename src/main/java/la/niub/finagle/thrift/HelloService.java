package la.niub.finagle.thrift;

import com.twitter.finagle.Service;
import com.twitter.tweetservice.thriftscala.Hello;
import com.twitter.tweetservice.thriftscala.Hello.FinagledService;
import com.twitter.util.Future;
import org.apache.thrift.protocol.TProtocolFactory;

/**
 * Created by peng on 14-12-29.
 */
public class HelloService extends Hello.FinagledService {
    public HelloService(Hello.FutureIface iface, TProtocolFactory protocolFactory) {
        super(iface, protocolFactory);
    }
}
