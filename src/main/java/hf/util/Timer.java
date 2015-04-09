package hf.util;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by howard.fackrell on 4/9/15.
 */
public class Timer {

    Map<String, Collection<Token>> map;
    public Timer() {
        map = new HashMap<String, Collection<Token>>();
    }

    public Token start(String category, String id) {
        return new Token(category, id);
    }

    public void stop(Token token) {
        token.end();
        if (map.containsKey(token.category)) {
            map.get(token.category).add(token);
        } else {
            ArrayList<Token> tokens = new ArrayList<Token>();
            tokens.add(token);
            map.put(token.category, tokens);
        }
    }

    private String stat(String category) {
        Collection<Token> tokens = map.get(category);
        double total = 0.0;
        long biggest = Long.MIN_VALUE;
        long smallest = Long.MAX_VALUE;

        for (Token token : tokens) {
            long diff = token.diff();
            total += diff;
            if (biggest < diff) biggest = diff;
            if (smallest > diff) smallest = diff;
        }
        double average = total / tokens.size();
        return MessageFormat.format("Category: {0} Count: {1} Avg: {2} Rng: {3}-{4}",
                category, tokens.size(), average, smallest, biggest);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String category : map.keySet()) {
            sb.append(stat(category)).append("\n");
        }
        return sb.toString();
    }

    public class Token {
        String category, id;
        Date start, stop;
        Token(String category, String i) { this.category = category; this.id = id; start = new Date();}
        void end() { stop = new Date();}
        long diff() { return stop.getTime() - start.getTime(); }
    }
}

