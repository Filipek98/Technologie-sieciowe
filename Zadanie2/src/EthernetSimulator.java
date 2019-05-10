import java.util.ArrayList;
import java.util.Random;

public class EthernetSimulator {
    private Wire wire = new Wire(100);
    private ArrayList<Host> hosts = new ArrayList<>();
    private Random random;

    public void addHost(int position){
        char name = (char)('A' + hosts.size());
        hosts.add(new Host(name, wire.getCell(position), 2 * Math.max(position, wire.getLength() - position)));
        random = new Random();
    }

    public void run(){
        int timer = 0;
        hosts.get(0).setWantToSend();
        while (timer < 8000){
            for (Host host : hosts) host.send();
            System.out.println(wire.print() + " " + String.valueOf(timer));
            wire.propagate();
            if (timer == 5) hosts.get(1).setWantToSend();
            if (timer == 15) hosts.get(2).setWantToSend();
            if (timer == 25) hosts.get(3).setWantToSend();
            if (timer == 30) hosts.get(4).setWantToSend();
            timer++;
        }
    }

    public static void main(String[] args){
        EthernetSimulator ethernet = new EthernetSimulator();
        ethernet.addHost(5);
        ethernet.addHost(40);
        ethernet.addHost(50);
        ethernet.addHost(70);
        ethernet.addHost(99);
        ethernet.run();
    }

}