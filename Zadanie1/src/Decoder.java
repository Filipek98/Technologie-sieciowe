public class Decoder {

    private final CRC8 crc;
    private final String terminator = "01111110";

    public Decoder(){
        crc = new CRC8();
    }

    public String decode(String source){
        StringBuilder result = new StringBuilder();
        source = source.replaceAll(terminator + terminator, " ");
        source = source.replaceAll(terminator, "");

        String[] frames = source.split(" ");

        for (String frame: frames){
            frame = frame.replaceAll("111110", "11111");
            String crcReceived = frame.substring(frame.length() - 8);
            String frameData = frame.substring(0, frame.length() - 8);
            if (crcReceived.equals(crc.calculateCrc(frameData))) result.append(frameData);
            else System.err.println("Incorrect CRC for package " + frameData);
        }

        return result.toString();
    }

}