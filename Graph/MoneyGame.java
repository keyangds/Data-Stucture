/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Keyang Xuan (xuan0008)
 */
public class MoneyGame{

    private Project3Graph<Integer> money = new Project3Graph<>();

    /**
     * funtion helps add node into money game
     * @param id the id of the node
     * @param element the value of node we want to add
     */
    public void addNode(String id, int element){
        money.addNode(id,element);  // call money's addNode function
    }

    /**
     * this function helps print money game's situation
     * @return a string of money game's graph
     */
    public String toString(){
        return money.toString(); // call money's toString function
    }

    /**
     * function helps add edge between two node
     * @param id1 the id for one node
     * @param id2 the id for another node
     */
    public void addEdge(String id1, String id2){
        money.addEdge(id1, id2);  // call money's addEdge function
    }

    /**
     * this function get the sum of all nodes' value in the game
     * @return integer value represents sum
     */
    public int sum(){
        int sum = 0;
        for(int i=0; i<money.countNodes(); i++){  // go through all node
            sum += money.getNode(money.getNodeIds()[i]); // add each node's value
        }
        return sum;
    }

    /**
     * this function detect whether game can be won
     * @return a boolean value represent whether game can be won
     */
    public boolean isWinnable(){
        int sum = sum();  // use sum() get the sum of all nodes
        if(sum >= money.genus())  // if sum >= genus, game win
        {
            return true;
        }
        else{   // otherwise, game fail
            return false;
        }
    }

}
