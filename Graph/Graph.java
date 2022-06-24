/*
 * Project 3
 * Class: CSCI 1913, Spring 2021, Section 10
 * Professor: Jerald Thomas
 * Student: Keyang Xuan (xuan0008)
 */
import java.util.Arrays;
public class Project3Graph<T> implements Project3GraphADT<T>{

    private int numVertex;
    private int numEdge;
    private Nodelist<T> nodes;


    public Project3Graph(){
        numVertex = 0;
        numEdge = 0;
        nodes = new Nodelist<>();
    }

    public class Nodelist<T>{

        private Node[] n; // a list for storing the nodes in graph
        private int size; // the size of the arraylist

        /**
         * initialize a Nodelist
         */
        Nodelist() {
            n = new Node[0];
            size = 0;
        }

        public String toString(){
            return Arrays.toString(n);
        }


        /**
         * this function helps get the node we want
         * @param index of the node we want to get
         * @return the corresponding node
         */
        public Node<T> getaNode(int index){
            if(index<0 || index>= size){
                return null;
            }
            return n[index];
        }

        /**
         * this function helps insert the node into Nodelist
         * @param ele the node we want to insert
         * @param index the place where we want to add node
         */
        public void insert(Node ele, int index){
            if(index<0 || index> size){ // check whether it is a qualified list
                return;
            }

            size++;  // increase the size of list first
            Node[] tmp = new Node[size]; // creat a tmp list

            for(int i=0; i<size; i++) // use loop go through list to find the location
            {
                if(i<index)  // all nodes before the location just store in original index of the tmp
                {
                    tmp[i] = n[i];
                }
                else if (i == index)  // the right location store the node we want to insert
                {
                    tmp[i] = ele;
                }
                else // all nodes behind the target location store to index+1 location in tmp list
                {
                    tmp[i] = n[i-1];
                }
            }
            n = tmp;  // make nodes list = tmp
        }

        /**
         * this function helps append node into list
         * @param ele the node we want to append into list
         */
        public void append(Node ele)
        {

            insert(ele, size);
        }


        /**
         * this function helps remove the node
         * @param index the location of node which we want to remove
         */
        void remove(int index)
        {
            if (index < 0 || index > (size - 1)) // check whether list is qualified
            {
                return;
            }

            size--;  // reduce the size

            Node[] tmp = new Node[size]; // create a tmp list

            for(int i=0; i<size; i++)  // all node before location store to the same location in tmp
            {
                if (i < index)
                {
                    tmp[i] = n[i];
                }
                else        // all node behind location store to index-1 location in tmp
                {
                    tmp[i] = n[i+1];
                }
            }
            n = tmp; // change nodes list
        }


    }


    private class Node<T>{
        T value;
        String id;
        Nodelist<Node<T>> adj; // Every node has a queue to store all its neighbor nodes


        /**
         * this function helps remove node from adj list
         * @param id the id of the node we want to remove
         */
        void removeadjnode(String id){
            for(int i=0; i<adj.size;i++){  // go through adj list
                if(adj.getaNode(i).id==id){   // if find same id, remove the node with this id
                    adj.remove(i);
                }
            }
        }

        /**
         * function helps check whether node with this id is in the adj list
         * @param id the id we want to check
         * @return a boolean type
         */
        boolean checkNode(String id){
            for(int i=0; i<adj.size; i++){  // go through list
                if(adj.getaNode(i).id==id){
                    return true;         // id inside case
                }
            }
            return false;   // otherwise return false
        }

        Node(T value, String id) {
            this.id = id;
            this.value = value;
            adj = new Nodelist<>();
        }

    }

    /**
     * this function will add a node to the graph
     * @param id newnode's id
     * @param value newnode's value
     */
    @Override
    public void addNode(String id, T value) {
        Node<T> newnode = new Node(value, id);  // create a newnode
        nodes.insert(newnode, numVertex);  // insert newnode

        numVertex++; //increase numVertex
    }

    /**
     * this function helps add edge between node1 and node2
     * @param id1 id of node1
     * @param id2 id of node2
     */
    @Override
    public void addEdge(String id1, String id2) {
        for(int i=0; i<numVertex; i++){
            if(nodes.getaNode(i).id==id1){  // find node1
                for(int j=0; j<numVertex; j++){
                    if(nodes.getaNode(j).id==id2){  // find node2
                        if(nodes.getaNode(i).checkNode(id2)== true || nodes.getaNode(j).checkNode(id1)== true)
                        {
                            return;   //check whether two nodes have already connected by a edge
                        }
                        nodes.getaNode(i).adj.append(nodes.getaNode(j));//append node2 into node1's adj list

                        nodes.getaNode(j).adj.append(nodes.getaNode(i));  //append node1 into node2's adj list
                    }
                }
            }
        }
        numEdge++;
    }

    /**
     * this function helps set the value to the specific node
     * @param id the location of the node we want to set value
     * @param value the value we want to set it to the node
     */
    @Override
    public void setNode(String id, T value){
        for(int i=0; i<numVertex; i++){ // go through the list
            if(nodes.getaNode(i).id==id){  // if node's id = target id
                nodes.getaNode(i).value = value; //set value to this node
            }
        }
    }

    /**
     * this function help get the value of the node we want to find in the graph
     * @param id of the node we want to find
     * @return the value of Node
     */
    @Override
    public T getNode(String id) {
        for(int i=0; i<numVertex; i++){ // go through the list
            if(nodes.getaNode(i).id == id) {   // if node's id = target id
                return nodes.getaNode(i).value;  // return this node's value
            }
        }
        return null; // otherwise return nothing
    }

    /**
     * this function return an array of strings which populated the each of the node ids in the graph
     * @return an array of strings populated with nodes id
     */
    @Override
    public String[] getNodeIds() {
        String[] idlist = new String[numVertex]; // create a string array which length = number of vertex
        if(numVertex==0){
            return idlist;  //if no vertex in Nodelist, directly return empty string array
        }
        for(int i=0; i<numVertex; i++){ // else go through graph
            idlist[i] = nodes.getaNode(i).id; // add each node's id into string array
        }
        return idlist; // return string array
    }

    /**
     * this function will remove the specified node from the graph
     * @param id the id of the node we want to remove
     */
    @Override
    public void removeNode(String id) {
        for(int i=0; i<numVertex; i++){ // go through t1
            if(nodes.getaNode(i).id == id){// if current node's id = id, then remove this node
                for(int j=0; j<numVertex; j++){
                    if(nodes.getaNode(j).checkNode(id)==true){  //delete all the edge of this node
                        nodes.getaNode(j).removeadjnode(id);
                    }
                }
                nodes.getaNode(i).adj=null; // delete all the edge of this node
                nodes.remove(i);
                break;
            }
        }
        numVertex--; // decrease numVertex by 1
    }

    /**
     * this function helps delete the edge between two node
     * @param id1 id of node1
     * @param id2 id of node2
     */
    @Override
    public void removeEdge(String id1, String id2){
        for(int i=0; i<numVertex; i++){
            if(nodes.getaNode(i).id==id1){  // find first node
                for(int j=0; j<numVertex; j++){
                    if(nodes.getaNode(j).id==id2){  // find second node
                        if(nodes.getaNode(i).checkNode(id2)==false || nodes.getaNode(j).checkNode(id1)==false)
                        {
                            return;   //check whether two nodes have already connected by a edge
                        }
                        nodes.getaNode(i).removeadjnode(id2);  // remove node2 from node1's adj list
                        nodes.getaNode(j).removeadjnode(id1);  // remove node1 from node1's adj list
                    }
                }
            }
        }
        numEdge--; //  reduce numEdge

    }

    /**
     * this function return the number of nodes in graph
     * @return the number of nodes in graph
     */
    @Override
    public int countNodes() {
        return numVertex;
    }

    /**
     * this function return the number of edge
     * @return the number of Edge
     */
    @Override
    public int countEdges() {
        return numEdge;
    }

    /**
     * this function will return the genus of the graph
     * @return the genus of the graph
     */
    @Override
    public int genus() {
        return numEdge - numVertex + 1; // return magnitude of genus
    }

    /**
     * this function helps print graph in a specific format
     * @return a string represent the graph
     */
    @Override
    public String toString() {
        String format = "";
        for(int i=0; i<numVertex; i++){
            format += nodes.getaNode(i).id + "("+nodes.getaNode(i).value+")"+":";
            for (int j = 0; j < nodes.getaNode(i).adj.size; j++) {
                format += nodes.getaNode(i).adj.getaNode(j).id + ",";
            }
            //format += nodes.getaNode(i).adj.getaNode(nodes.getaNode(i).adj.size-1) + "\n";


            format += "\n";

        }

        return format;
    }
}
