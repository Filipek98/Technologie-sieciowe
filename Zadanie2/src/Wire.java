import javafx.util.Pair;

/**
 * Created by mk on 08.05.17.
 */
public class Wire {
    public static final char empty = '-';
    public static final char jam = '#';
    public static final char collision = '!';
    private WireCell[] wire;

    public Wire(int length){
        wire = new WireCell[length];
        for (int i = 0; i < length; i++) wire[i] = new WireCell();
    }

    public void propagate() {
        for (int i = 0; i < wire.length; i++) {
            for (int j = wire[i].signals.size() - 1; j >=0; j--){
                Pair<Character, Direction> signal = wire[i].signals.get(j);
                if (signal.getValue() == Direction.LEFT){
                    wire[i].signals.remove(j);
                    if (i > 0) wire[i - 1].signals.add(signal);
                }
                else if (signal.getValue() == Direction.BOTH){
                    if (i > 0) wire[i - 1].signals.add(new Pair<>(signal.getKey(), Direction.LEFT));
                }
            }
        }

        for (int i = wire.length - 1; i >= 0; i--) {
            for (int j = wire[i].signals.size() - 1; j >=0; j--){
                Pair<Character, Direction> signal = wire[i].signals.get(j);
                if (signal.getValue() == Direction.RIGHT){
                    wire[i].signals.remove(j);
                    if (i < wire.length - 1) wire[i + 1].signals.add(signal);
                }
                else if (signal.getValue() == Direction.BOTH){
                    if (i < wire.length - 1) wire[i + 1].signals.add(new Pair<>(signal.getKey(), Direction.RIGHT));
                    wire[i].signals.remove(j);
                }
            }
        }
    }

    public String print(){
        StringBuilder wireStr = new StringBuilder();
        for (WireCell cell : wire) wireStr.append(cell.getState());
        return wireStr.toString();
    }

    public WireCell getCell(int i){
        return wire[i];
    }
    public int getLength(){
        return wire.length;
    }

}