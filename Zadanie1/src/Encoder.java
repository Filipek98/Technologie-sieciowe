public class Encoder {

    private final CRC8 crc;
    private final String terminator = "01111110";

    Encoder(){
        crc = new CRC8();
    }

    String encode(String source, int size){
        StringBuilder result = new StringBuilder();
        int bitsUsed = 0;
        while (bitsUsed < source.length()){
            String frame;
            if (source.length() - bitsUsed <= size) frame = (source.substring(bitsUsed));
            else frame = source.substring(bitsUsed, bitsUsed + size);
            frame += crc.calculateCrc(frame);
            frame = frame.replaceAll("11111", "111110");
            frame = terminator + frame + terminator;
            bitsUsed += size;
            result.append(frame);
        }

        return result.toString();
    }
}