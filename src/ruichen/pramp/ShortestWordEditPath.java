package ruichen.pramp;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class ShortestWordEditPath {
    // https://www.pramp.com/challenge/MW75pP53wAtzNPVLPG0d

    class Solution {
        public int shortestWordEditPath(String source, String target, String[] words) {
            Set<String> wordSet = new HashSet<>();
            for (String word : words) {
                wordSet.add(word);
            }
            String alphabet = "abcdefghijklmnopqrstuvwxyz";

            if (!wordSet.contains(target)) {
                return -1;
            }

            Queue<String> queue = new ArrayDeque<>();
            Set<String> visited = new HashSet<>();
            queue.offer(source);
            visited.add(source);
            int changes = 0; // We don't have tuples in Java, it's easier to just enqueue <word,depth> pair

            while (!queue.isEmpty()) {
                int size = queue.size();

                for (int i = 0; i < size; i++) {
                    String word = queue.poll();

                    if (word.equals(target)) {
                        return changes;
                    }

                    for (int j = 0; j < word.length(); j++) {
                        for (char c : alphabet.toCharArray()) {
                            char[] arr = word.toCharArray();
                            arr[j] = c;
                            String str = new String(arr);

                            if (wordSet.contains(str) && !visited.contains(str)) {
                                queue.offer(str);
                                visited.add(str);
                            }
                        }
                    }
                }
                changes++;
            }
            return -1;
        }
    }
}
