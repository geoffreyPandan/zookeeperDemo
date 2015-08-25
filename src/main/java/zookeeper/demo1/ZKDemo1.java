package zookeeper.demo1;

import org.apache.zookeeper.*;

/**
 * ZKDemo1
 *
 * @author: sunjie
 * @date: 15/8/24
 */
public class ZKDemo1 {

    public static void main(String[] args) {
        try {
            ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 2000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("已经触发了" + event.getType() + "事件！");
                }
            });

            zk.create("/testRootPath2", "testRootData2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            zk.create("/testRootPath/testChildRootPath", "testChildRootData".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.PERSISTENT);

            System.out.println(new String(zk.getData("/testRootPath2", false, null)));
//            System.out.println(zk.getChildren("/testRootPath", true));

//            zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
//            System.out.println(zk.getChildren("/testRootPath", true));

            zk.delete("/testRootPath2", -1);
            zk.close();
        } catch (Exception e) {

        }

    }
}
