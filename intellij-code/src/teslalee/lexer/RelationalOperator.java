package teslalee.lexer;

public class RelationalOperator extends Token{
    public final String opt;
    public RelationalOperator(String s) {
        super(Tag.RELATIONAL_OPERATOR);
        opt = new String(s);
    }
}