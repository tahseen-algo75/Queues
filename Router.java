/*
 *  @author Tahseen Zaman
 *  ID : 114332480
 *  Recitation : 03
 */
import java.util.*;
public class Router extends LinkedList<Packet> {
    LinkedList<Packet> queue;
    public static int maxBufferSize;
    
    public Router(){
        queue = new LinkedList<Packet>();
    }
    /* This method is implemented by using the JAVA API and by extending
    the linkedList class. It adds the packets in the queue.
    */
    public void enqueue(Packet p){
        queue.add(p);
    }
    /* This method is implemented by using the JAVA API and by extending
    the linkedList class. It removes the packets from the queue.
    */
    public Packet dequeue(){
        return queue.remove();
    }
    /* This method is implemented by using the JAVA API and by extending
    the linkedList class. It returns the top value of the queue.
     */
    public Packet peek(){
        return queue.peek();
    }
    /* This method is implemented by using the JAVA API and by extending
       the linkedList class. It returns the size of the queue.
     */
    public int size(){
        return queue.size();
    }
    /* This method is implemented by using the JAVA API and by extending
       the linkedList class. It returns the boolean value to check whether the queue is empty or not.
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }
    /* This method loop through the list of Intermediate routers. We use this method
       to send packet from one router to another router. And when all router buffers are
       full it throws an Exception.
     */
    public static int sendPacketTo(ArrayList<Router> routers) throws FullRouterException {
        int index = 0;
        int smallestRouterSize = routers.get(0).size();
        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(i).size() < smallestRouterSize) {
                index = i;
                smallestRouterSize = routers.get(i).size();
            }
        }
        if (maxBufferSize == smallestRouterSize) throw new FullRouterException("Routers are full");
        return index;
    }
    /* It returns the string called result and helps to format the output in a specified way. 
     */
    public String toString(){
        String st = "{ ";
        if(queue.isEmpty() == false){
            for(int i = 0; i<queue.size(); i++){
                st += " " + queue.get(i).toString();
            }
            st += " }";
        }
        return st;
    }
}
