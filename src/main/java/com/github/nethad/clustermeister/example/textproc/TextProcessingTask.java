/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.nethad.clustermeister.example.textproc;

import com.github.nethad.clustermeister.api.impl.Task;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/**
 *
 * @author thomas
 */
public class TextProcessingTask extends Task<TextProcessingResult> {

    @Override
    public TextProcessingResult execute() throws Exception {
        String text = (String) getValue("text");
        BufferedReader reader = new BufferedReader(new StringReader(text));
        String line;
        int wordCounter = 0;
        int allWordsLength = 0;
        int vowelCountA = 0;
        int vowelCountE = 0;
        int vowelCountI = 0;
        int vowelCountO = 0;
        int vowelCountU = 0;
        while ((line = reader.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            String token;
            while (tokenizer.hasMoreTokens() && (token = tokenizer.nextToken()) != null) {
                allWordsLength += token.length();
                wordCounter++;
            }
            for(int i=0; i<line.length(); i++) {
                char charAt = line.charAt(i);
                if (charAt == 'a') vowelCountA++;
                if (charAt == 'e') vowelCountE++;
                if (charAt == 'i') vowelCountI++;
                if (charAt == 'o') vowelCountO++;
                if (charAt == 'u') vowelCountU++;
            }
        }
        TextProcessingResult result = new TextProcessingResult();
        result.setWordCount(wordCounter);
        double avgWordLength = ((double) allWordsLength / wordCounter);
        result.setAvgWordLength(avgWordLength);
        result.setVowelCountA(vowelCountA);
        result.setVowelCountE(vowelCountE);
        result.setVowelCountI(vowelCountI);
        result.setVowelCountO(vowelCountO);
        result.setVowelCountU(vowelCountU);
        return result;
    }
    
    
    
}
