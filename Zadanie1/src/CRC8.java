public class CRC8 {

    final String polynomial = "111010101";

    public String calculateCrc(String source) {
        StringBuilder result = new StringBuilder(source);
        result.append("00000000") ;

        int i = source.indexOf('1');

        while (i < source.length()){
            for (int j = 0; j < polynomial.length(); j++){
                result.setCharAt(i+j, xor(result.charAt(i+j), polynomial.charAt(j)));
            }
            i = result.indexOf("1", i);
        }
        return result.substring(source.length());
    }

    private static char xor(char x, char y){
        return x == y ? '0' : '1';
    }
}
