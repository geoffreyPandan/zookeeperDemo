package zookeeper.demo2;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * ZkOperatorT
 *
 * @author: sunjie
 * @date: 15/8/24
 */
public class ZkOperator {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private ZooKeeper      zooKeeper;

    public ZooKeeper connect(String hosts) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(hosts, 20000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getState() == Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                }
            }
        });
        countDownLatch.await();
        return zooKeeper;
    }

    public void create(String path, String data) throws KeeperException, InterruptedException {
        this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void setData(String path, String data) throws KeeperException, InterruptedException {
        this.zooKeeper.setData(path, data.getBytes(), -1);
    }

    public void delete(String path) throws KeeperException, InterruptedException {
        this.zooKeeper.delete(path, -1);
    }

    public String getData(String path) throws KeeperException, InterruptedException {
        return new String(this.zooKeeper.getData(path, false, null));
    }

    public boolean exists(String path) throws KeeperException, InterruptedException {
        return this.zooKeeper.exists(path, false) != null ? true : false;
    }

    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        return this.zooKeeper.getChildren(path, false);
    }

    public void close() throws InterruptedException {
        if (zooKeeper != null)
            zooKeeper.close();
    }
}
