package la.niub.finagle.thrift;

import com.twitter.tweetservice.thriftscala.Hello;
import com.twitter.util.Future;

/**
 * Created by peng on 14-12-29.
 */
public class HelloImpl implements Hello.FutureIface {

    @Override
    public Future<String> hi() {
        System.out.println("hi called");
        return Future.value("hello finagle!");
    }
}
