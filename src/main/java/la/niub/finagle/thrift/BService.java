package la.niub.finagle.thrift;

import com.twitter.tweetservice.thriftscala.B;
import org.apache.thrift.protocol.TProtocolFactory;

/**
 * Created by peng on 14-12-29.
 */
public class BService extends B.FinagledService {
    public BService(B.FutureIface iface, TProtocolFactory protocolFactory) {
        super(iface, protocolFactory);
    }
}
