/*
 *  @author Tahseen Zaman
 *  ID : 114332480
 *  Recitation : 03
 */
public class Packet {

    public static int packetCount = 0;
    private int id;
    private int packetSize; 
    private int timeArrive;
    private int timeToDest; 
    
    public Packet(){}
    
    public Packet(int packetSize, int timeArrive, int timeToDest){
        this.id = ++packetCount;
        this.packetSize = packetSize;
        this.timeArrive = timeArrive;
        this.timeToDest = packetSize/100;
    }
    /* @ return
     *   It return the id number of the Packet as an integer value.
     */
    public int getId() {
        return id;
    }
    /* @ return
     *   It return the packetSize as an integer value.
     */
    public int getPacketSize() {
        return packetSize;
    }
    /* @ return
     *   It return the arrivalTime of the Packet as an integer value.
     */
    public int getTimeArrive() {
        return timeArrive;
    }
    /* @ return
     *   It return the timeToDest as an integer value.
     */
    public int getTimeToDest() {
        return timeToDest;
    }
    /* @ param id
         It can set the id number of the Packet.
     */
    public void setId(int id) {
        this.id = id;
    }
    /* @ param packetSize
         It can set the packetSize of the Packet.
     */
    public void setPacketSize(int packetSize) {
        this.packetSize = packetSize;
    }
    /* @ param timeArrive
         It can set the arrivalTime of the Packet.
     */
    public void setTimeArrive(int timeArrive) {
        this.timeArrive = timeArrive;
    }
    /* @ param timeArrive
        It can set the timeToDest of the Packet.
    */
    public void setTimeToDest(int timeToDest) {
        this.timeToDest = timeToDest;
    }
    /* It return the data fields of the packets by using the toString method.
     * It is also implemented to format the output.
     */
    public String toString(){
        return "[" + timeArrive + " , " + timeToDest + " , " + id + "]" ;
    }
}
