package teslalee.lexer;

public class Float extends Num {
    public final double value;

    public Float(double v) {
        super(Tag.NUM);
        value = v;
    }
}