package zookeeper.demo2;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * ZkDemo3
 *
 * @author: sunjie
 * @date: 15/8/24
 */
public class ZkDemo3 {
    public static void main(String[] args) {
        ZkOperator zkOperator = new ZkOperator();
        String path = "/testRootPath3";
        try {
            ZooKeeper zk = zkOperator.connect("127.0.0.1:2181");
            if (zk.exists(path, false) == null){
                zk.create(path, "testRootData3".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                System.out.println("zk.create....");
            }else{
                zk.setData(path,"updateTestRootData".getBytes(),-1);
                System.out.println("zk.setData....");
            }

            System.out.println(new String(zk.getData(path, false, null)));

            zk.delete(path, -1);

            zk.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
