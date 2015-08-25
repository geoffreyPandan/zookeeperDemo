package zookeeper.demo1;

/**
 * ZKdDemo2
 *
 * @author: sunjie
 * @date: 15/8/24
 */
public class ZKdDemo2 {
    public static void main(String[] args) {
        try {
            ZkOperatorT zkOperatorT = new ZkOperatorT();
            zkOperatorT.connect("127.0.0.1:2181");

            String zkTest = "only a test";

            zkOperatorT.create("/root2", null);
            zkOperatorT.create("/root2/child3", zkTest.getBytes());

            System.out.println(new String(zkOperatorT.getData("/root2/child3")));
            zkOperatorT.getChild("/root2");

            zkOperatorT.setData("/root2", "1234".getBytes());
            zkOperatorT.setData("/root2/child3",zkTest.getBytes());

            zkOperatorT.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
