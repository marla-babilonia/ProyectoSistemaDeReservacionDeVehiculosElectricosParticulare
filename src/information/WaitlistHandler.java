package information;

import java.util.LinkedList;
import java.util.Queue;
import information.AvailableStations.LOCATION;

public class WaitlistHandler {
    private LOCATION location;
    private Queue<Users> queue;
    
    public WaitlistHandler(LOCATION location){
        this.location = location;
        this.queue = new LinkedList<>();
    }

    public LOCATION getLocation(){
        return location;
    }

    public void add(Users user){
        queue.offer(user);
    }

    public Users next(){
        return queue.poll();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public Queue<Users> getQueue(){
        return queue;
    }
}
