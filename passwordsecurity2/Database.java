/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordsecurity2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Database {
    
    final static class MyResult {
        private final boolean first;
        private final String second;

        public MyResult(boolean first, String second) {
            this.first = first;
            this.second = second;
        }

        public boolean getFirst() {
            return first;
        }

        public String getSecond() {
            return second;
        }
    }
    
}
