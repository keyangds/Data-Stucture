/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Keyang Xuan (xuan0008)
 */
public class main {
    public static void main(String[] args){
        /**
         * test for graph
         */
        Project3Graph<Integer> myNodeList = new Project3Graph<>();
        myNodeList.addNode("n1",5);
        myNodeList.addNode("n2",2);
        myNodeList.addNode("n3",7);
        System.out.println(myNodeList);
        myNodeList.addEdge("n1","n2");
        myNodeList.addEdge("n3","n2");
        myNodeList.addEdge("n1","n3");
        System.out.println(myNodeList);
        //myNodeList.removeNode("n1");
        //System.out.println(myNodeList);
        myNodeList.removeEdge("n1","n2");
        System.out.println(myNodeList);
        myNodeList.removeNode("n1");
        System.out.println(myNodeList);

        /**
         * test for money game
         */
         MoneyGame m = new MoneyGame();
         m.addNode("m1",1);
         m.addNode("m2",2);
         m.addNode("m3",1);
         m.addEdge("m1","m2");
         m.addEdge("m3","m2");
         m.addEdge("m1","m3");
         System.out.println(m.sum());
         System.out.println(m.isWinnable());

    }
}
