package org.epamTask.BadUsage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Contract {
    private static Contract instance;
    private List<String> terms;
    private PrintWriter fileWriter;

    private Contract() {
        try {
            fileWriter = new PrintWriter(new FileWriter("appContract.txt", true));
            this.terms = new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Contract getInstance() {
        if (instance == null) {
            synchronized (Contract.class) {
                if (instance == null) {
                    instance = new Contract();
                }
            }

        }
        return instance;
    }

    public void addTerms(List<String> terms) {
        this.terms.addAll(terms);
        for (String term : terms) {
            fileWriter.println(term);
            fileWriter.flush();
        }
    }

    public void addTerms(String term) {
        this.terms.add(term);
        fileWriter.println(term);
        fileWriter.flush();
    }

    public List<String> getTerms() {
        return terms;
    }

}
