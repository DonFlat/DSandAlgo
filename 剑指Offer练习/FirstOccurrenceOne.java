package 剑指Offer练习;

import java.util.*;

public class FirstOccurrenceOne {
    public int FirstNotRepeatingChar(String str) {
        LinkedHashMap<Character, Integer> freq = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); ++i) {
            char cur = str.charAt(i);
            if (freq.containsKey(cur) == true) {
                //++(freq.get(cur));   
                int f = freq.get(cur);
                ++f;
                freq.replace(cur, f);
            }
            else {
                freq.put(cur, 1);
            }
        }
        char oneChar = '\0';
        for (Map.Entry<Character, Integer> oneEntry : freq.entrySet()) {
            if (oneEntry.getValue() == 1) {
                oneChar = oneEntry.getKey();
                break;
            }
        }
        if (oneChar == '\0') 
            return -1;
        else {
            for (int i = 0; i < str.length(); ++i) {
                if (str.charAt(i) == oneChar)
                    return i;
            }
            return -1;
        }
    }
}