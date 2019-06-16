package teslalee.lexer;

import java.io.*;
import java.util.*;

public class Lexer {
    public int line = 1;
    private char peek = ' ';
    private Hashtable<String, Token> words = new Hashtable<String, Token>();

    void reserve(Word t) {
        words.put(t.lexeme, t);
    }

    public Lexer() {
        reserve(new Word(Tag.TRUE, "true"));
        reserve(new Word(Tag.FALSE, "false"));
    }

    public Token scan() throws IOException {
        for(;;peek = (char)System.in.read()) {
            if(peek == ' ' || peek == '\t') continue;
            else if (peek == '\n') line ++;
            else break;
        }

        /**
         * 以下部分是 2.6.1 的解
         */
        if(peek == '/') {
            peek = (char)System.in.read();
            // 行注释
            if(peek == '/') {
                for(;;peek = (char)System.in.read()) {
                    if (peek == '\n') {
                        peek = (char)System.in.read();
                        line ++;
                        break;
                    }
                }
            } else if (peek == '*') {
                // block 注释
                for(;;peek = (char)System.in.read()) {
                    if (peek == '\n') line ++;
                    else if (peek == '*') {
                        peek = (char)System.in.read();
                        if(peek == '/') {
                            peek = (char)System.in.read();
                            break;
                        } else {
                            throw new Error("syntax error");
                        }
                    }
                }
            } else {
                throw new Error("syntax error");
            }
        }

        /**
         * 以下部分是 2.6.2 的解
         * <, <=, ==, !=, >= >
         */
        if("<=!>".indexOf(peek) >= 0) {
            StringBuffer b = new StringBuffer();
            b.append(peek);
            peek = (char)System.in.read();
            if(peek == '=') {
                b.append(peek);
                String s = b.toString();
                RelationalOperator ro = new RelationalOperator(s);
                return ro;
            } else {
                String s = b.toString();
                RelationalOperator ro = new RelationalOperator(s);
                return ro;
            }
        }
        
        /**
         * 以下部分是 2.6.3 的解，
         * 支持浮点数
         */
        if(Character.isDigit(peek) || peek == '.') {
            double vf = 0;
            int vi = 0;

            // 整数部分
            if(Character.isDigit(peek)) {
                do {
                    vi = 10 * vi + Character.digit(peek, 10);
                    peek = (char)System.in.read();
                } while (Character.isDigit(peek));
            }

            // 小数部分
            if(peek == '.') {
                int i = 1;
                peek = (char)System.in.read();

                do {
                    vf = vf + 0.1 * Character.digit(peek, 10);
                    peek = (char)System.in.read();
                    i ++;
                } while(Character.isDigit(peek));

                Float f = new Float(vi + vf);
                return f;
            }

            Num n = new Num(vi);
            return n;

            // int v = 0;
            // do {
            //     v = 10 * v + Character.digit(peek, 10);
            //     peek = (char)System.in.read();
            // } while (Character.isDigit(peek));
            // return new Num(v);
        }
        
        if(Character.isLetter(peek)) {
            StringBuffer b = new StringBuffer();
            do {
                b.append(peek);
                peek = (char)System.in.read();
            } while(Character.isLetterOrDigit(peek));

            String s = b.toString();
            Word w = (Word)words.get(s);
            if(w != null) return w;

            w = new Word(Tag.ID, s);
            words.put(s, w);
            return w;
        }

        Token t = new Token(peek);
        peek = ' ';
        return t;
    }
}