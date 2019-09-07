/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Hudson
 */
public class Database {
    
    private static final Database instance = new Database();
    private List<Score> scores = Collections.synchronizedList(new ArrayList<Score>());
    
    private Database() {

    }

    public void addScore(Score s) {
        instance.scores.add(s);
    }
    
    public static Database getInstance() {
        return instance;
    }

    public synchronized Score[] getScores() {
        Score[] sc = new Score[instance.scores.size()];
        instance.scores.toArray(sc);
        return sc;
    }
}
