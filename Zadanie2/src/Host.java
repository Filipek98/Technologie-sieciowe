import java.util.Random;

public class Host {
    char name;
    char message;
    WireCell wireCell;

    private Random random = new Random();
    private int wait;
    private int toSend;
    private int messageLength;
    int resendCounter = 0;

    private boolean sending = false;
    private boolean jam = false;
    private boolean collision = false;
    private boolean wantToSend = false;

    private static int resendDelayUnit = 2;


    public Host(char name, WireCell wireCell, int messageLength){
        this.name = name;
        this.message = name;
        this.messageLength = messageLength;
        this.wireCell = wireCell;
    }

    private int calculateBackoff(){
        return resendDelayUnit * random.nextInt((int)Math.pow(2, resendCounter) + 1);
    }
    public void setWantToSend(){
        wantToSend = true;
    }

    public void send(){
        wait--;

        if (jam){
            if (toSend > 0){
                wireCell.addSignal(Wire.jam);
                toSend--;
            }
            else jam = false;
        }

        if (collision && !jam){
            wait = calculateBackoff();
            resendCounter++;
            if(resendCounter > 10){
                wantToSend = false;
                System.out.println("timeout");
            }
            collision = false;
        }

        if(sending){
            if (toSend > 0){
                wireCell.addSignal(message);
                toSend--;
                if (wireCell.getState() != message){
                    sending = false;
                    jam = true;
                    collision = true;
                    wantToSend = true;
                }
            }
            else{
                wantToSend = false;
                sending = false;
                resendCounter = 0;
            }
        }

        if (wait <= 0 && wireCell.getState() == Wire.empty && !collision && wantToSend){
            sending = true;
            toSend = messageLength;
        }

    }
}