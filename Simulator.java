import java.util.*;
/*
 *  @author Tahseen Zaman
 *  ID : 114332480
 *  Recitation : 03
 */
public class Simulator {

    private Router dispatcher;
    private ArrayList<Router> intermediate;
    private Router destination;
    private int totalServiceTime;
    private double averageServiceTime;
    private int totalPacketsArrived;
    private int packetsDropped;
    private double arrivalProb;
    private int numIntRouters;
    private int maxBufferSize;
    private int minPacketSize;
    private int maxPacketSize;
    private int bandWith;
    private int duration;
    private int timeLeft;

    public static int MAX_PACKETS = 3;

    public Simulator() {
        totalServiceTime = 0;
        totalPacketsArrived = 0;
        packetsDropped = 0;
        averageServiceTime = 0;
        timeLeft = 0;
        dispatcher = new Router();
        intermediate = new ArrayList<Router>();
        destination = new Router();
    }
    /* This method checks whether the randomly generated number is less than the arrivalProbalility or not.
     */
    public boolean occurs() {
        return (Math.random() < arrivalProb);
    }
    /* This method is implemented by the following steps:
       At first, it decide whether packets have arrived at the Dispatcher. And a maximum of 3 can arrive at a given time. 
       If the Dispatcher contains unsent packets, it send them off to one of the Intermediate routers. 
       The method sendPacketTo decide which router the packet should be forwarded to. It decrement all packets counters 
       in the beginning of the queue at each Intermediate router. And forward any packets that are ready to be moved to
        the Destination router. At last, packet arrive at the Destination router.
     */
    public double simulate(int totalServiceTime, double arrivalProb, int numIntRouters, int maxBufferSize, int minPacketSize, int maxPacketSize,
                           int bandWith, int duration) {

        Router.maxBufferSize = maxBufferSize;
        this.arrivalProb = arrivalProb;

        for(int i = 0; i < numIntRouters; i++){
            intermediate.add(new Router());
        }

        for (int currentSecond = 1; currentSecond < duration; currentSecond++) {
            System.out.println("Time : "+ currentSecond);
            for (int i = 0; i < 3; i++) {
                if (occurs()) {
                    int n = randInt(minPacketSize, maxPacketSize);
                    Packet.packetCount++;
                    Packet packet = new Packet( n, currentSecond, duration);
                    dispatcher.enqueue(packet);

                    System.out.println("Packet " + packet.getId() + " arrives at dispatcher with size " + n );
                }
            }
            while (dispatcher.size() != 0) {
                Packet p = dispatcher.dequeue();
                try {
                    int index = Router.sendPacketTo(intermediate);
                    intermediate.get(index).enqueue(p);

                    System.out.println("Packet " + p.getId() + " sent to Router" + (index+1));

                }catch(FullRouterException e){
                    packetsDropped++;
                    System.out.println("Network is congested. Packet " + p.getId() + " is dropped");
                }
            }
            for(Router r : intermediate){
                if(r.peek() != null){
                    r.peek().setTimeToDest(r.peek().getTimeToDest()-1);
                }
                if(r.peek() != null){
                    if(r.peek().getTimeToDest() == 0){
                        destination.enqueue(r.dequeue());
                        totalPacketsArrived++;
                        totalServiceTime = currentSecond - r.peek().getTimeArrive();
                    }
                }
            }
            for(int i = 0; i<intermediate.size(); i++){
                System.out.println("R" +(i+1) + intermediate.get(i).toString());
            }
        }
        if(totalPacketsArrived == 0) {
            averageServiceTime = 0;
        }else{
            averageServiceTime = totalServiceTime / totalPacketsArrived;
        }
        System.out.println("Simulation ending...");
        System.out.println("Total Service time : " + totalServiceTime);
        System.out.println("Total packets served : " + totalPacketsArrived);
        System.out.println("Average time Per Packet : " + averageServiceTime);
        System.out.println("Total packets dropped : " + packetsDropped);

        return averageServiceTime;
    }
    /* It generates the random numbers between maximum and minimum size.
     */
    private int randInt(int minVal, int maxVal){
        return (int)(Math.random()* (maxVal- minVal) + minVal);
    }
    /* This method receives the input from the user and runs the simulate method.
     */
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        System.out.println("Starting simulator...");
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the number of Intermediate routers : ");
        int num = reader.nextInt();
        System.out.println("Enter the arrival probability of a packet: ");
        double arrivalProb = reader.nextDouble();
        System.out.println("Enter the maximum buffer size of a router: ");
        int maxBufferSize = reader.nextInt();
        System.out.println("Enter the minimum size of a packet: ");
        int minSize = reader.nextInt();
        System.out.println("Enter the maximum size of a packet: ");
        int maxSize = reader.nextInt();
        System.out.println("Enter the bandwidth size:");
        int bandWithSize = reader.nextInt();
        System.out.println("Enter the simulation duration : ");
        int duration = reader.nextInt();

        reader.close();

        simulator.simulate(duration, arrivalProb, num, maxBufferSize, minSize, maxSize, bandWithSize, duration);


    }
}
