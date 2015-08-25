package zookeeper.demo1;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

import java.util.List;

/**
 * ZkOperatorT
 *
 * @author: sunjie
 * @date: 15/8/24
 */
public class ZkOperatorT extends  DemoWatcher{
    public  void  create(String path, byte[] data) throws KeeperException, InterruptedException {
        this.zooKeeper.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public  void  setData(String path, byte[] data) throws KeeperException, InterruptedException {
        this.zooKeeper.setData(path, data, -1);
    }

    public  void  delete(String path, byte[] data) throws KeeperException, InterruptedException {
        this.zooKeeper.delete(path, -1);
    }

    public  void  getChild(String path) throws KeeperException, InterruptedException {
        List<String> list = this.zooKeeper.getChildren(path, false);
        if (list.isEmpty()){
            System.out.println(path + "没有节点");
        }else {
            System.out.println(path + "有节点");

            for (String child: list){
                System.out.println("有节点为:" + child);
            }
        }

    }

    public  byte[] getData(String path) throws KeeperException, InterruptedException {
        return  this.zooKeeper.getData(path, false , null);

    }

}
