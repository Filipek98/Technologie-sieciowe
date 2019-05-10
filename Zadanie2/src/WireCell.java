import javafx.util.Pair;

import java.util.ArrayList;

public class WireCell {
    public ArrayList<Pair<Character, Direction>> signals;

    WireCell(){
        signals = new ArrayList<>();
    }

    public void addSignal(char signal){
        signals.add(new Pair<>(signal, Direction.BOTH));
    }

    public char getState(){
        for (int i = 0; i < signals.size(); i++) if (signals.get(i).equals(Wire.jam)) return Wire.jam;
        if (signals.size() > 1) return Wire.collision;
        if (signals.size() == 1)return signals.get(0).getKey();
        return Wire.empty;
    }
}