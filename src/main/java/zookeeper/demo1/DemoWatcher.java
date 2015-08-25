package zookeeper.demo1;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * DemoWatcher
 *
 * @author: sunjie
 * @date: 15/8/24
 */
public class DemoWatcher implements Watcher {

    private static final int SESSTION_TIME  = 3000;
    protected ZooKeeper      zooKeeper;
    protected CountDownLatch countDownLatch = new CountDownLatch(1);

    public void connect(String hosts) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(hosts, SESSTION_TIME, this);
//        countDownLatch.wait();
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState() == Event.KeeperState.SyncConnected) {
//            countDownLatch.countDown();
        }
    }

    public void close() throws InterruptedException {
        if (zooKeeper != null)
            zooKeeper.close();
    }
}
