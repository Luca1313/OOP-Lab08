package it.unibo.oop.lab.advanced;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ReadFromResources {

    private static final String SEPARATOR = ": ";
    private final BufferedReader sr;

    public ReadFromResources() {
        this.sr = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("config.yml")));
    }
    public final int readIntFromResources() {
        try {
            final StringTokenizer st = new StringTokenizer(this.sr.readLine(), ReadFromResources.SEPARATOR, false);
            st.nextToken();
            return Integer.parseInt(st.nextToken());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
