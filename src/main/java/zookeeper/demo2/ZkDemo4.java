package zookeeper.demo2;

import java.util.List;

/**
 * ZkDemo4
 *
 * @author: sunjie
 * @date: 15/8/24
 */
public class ZkDemo4 {
    public static void main(String[] args) {
        ZkOperator zkOperator = new ZkOperator();
        String path = "/3root2";
        String pathChild1 = "/3root2/child1";
        String pathChild2 = "/3root2/child2";
        String pathChild3 = "/3root2/child3";
        try {
            zkOperator.connect("127.0.0.1:2183");
            if (!zkOperator.exists(path)){
                zkOperator.create(path,"rootData");
                zkOperator.create(pathChild1,"child1Data1");
                zkOperator.create(pathChild2,"child1Data2");
                zkOperator.create(pathChild3,"child1Data3");
            }else {
                zkOperator.setData(path, "updateRootData");
                zkOperator.setData(pathChild1, "updateChild1Data");
                zkOperator.setData(pathChild2, "updateChild2Data");
                zkOperator.setData(pathChild3, "updateChild3Data");
            }


            System.out.println(zkOperator.getData(path));

            List<String> list = zkOperator.getChildren(path);
            if (list.isEmpty()){
                System.out.println(path + "没有节点");
            }else {
                System.out.println(path + "有节点");
                for (String child: list){
                    System.out.println("有节点为:" + child);
                }
            }

            zkOperator.delete(pathChild1);
            zkOperator.delete(pathChild2);
            zkOperator.delete(pathChild3);
            zkOperator.delete(path);

            zkOperator.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
