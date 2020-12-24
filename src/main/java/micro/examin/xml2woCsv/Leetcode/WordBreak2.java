package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class WordBreak2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) {
            return new LinkedList();
        }
        Trie trie = new Trie();
        for (String curr : wordDict) {
            trie.insert(curr);
        }
        HashMap<Integer, LinkedList<String>> dp = new HashMap<>();
        LinkedList<String> result = wordBreak(s.toCharArray(), trie, null, 0, dp);
        LinkedList<String> finalResult = new LinkedList<>();
        for(String curr : result){
            finalResult.add(curr.trim());
        }
        return result;
    }

    public LinkedList<String> wordBreak(char[] input, Trie trie, TrieNode node, int index, HashMap<Integer, LinkedList<String>> dp) {
        if (index >= input.length) {
            if (index == input.length && node != null && node.endOfWord) {
                return new LinkedList<>(Arrays.asList(" "));
            }
            return null;
        }
        if(dp.containsKey(index)){
            return dp.get(index);
        }

        node = node == null ? trie.contains(input[index] + "") : trie.contains(input[index], node);
        if (node == null) {
            return null;
        } else {
            LinkedList<String> joined = new LinkedList<>();
            LinkedList<String> got1 = null;

            LinkedList<String> got2 = wordBreak(input, trie, node, index + 1, dp);
            if (node.endOfWord) {
                got1 = wordBreak(input, trie, null, index + 1, dp);
                if (got1 != null) {
                    for (String curr : got1) {
                        joined.add(input[index] + " "+curr);
                    }
                }
            }
            if (got2 != null) {
                for (String curr : got2) {
                    joined.add(input[index] + curr);
                }
            }
            if (got1 == null && got2 == null) {
                return null;
            }
            dp.put(index,joined);
            return joined;
        }
    }

    public static void main(String[] args) {
        String input = "pineapplepenapple";
        String words[] = {"apple", "pen", "applepen", "pine", "pineapple"};
        WordBreak2 wb = new WordBreak2();
        List<String> result = wb.wordBreak(input, Arrays.asList(words));
        if (result != null) {
            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}

class TrieNode {
    TrieNode[] childs = new TrieNode[256];
    boolean endOfWord = false;
}

class Trie {
    private TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode itr = root;
        for (char curr : word.toCharArray()) {
            if (itr.childs[curr] == null) {
                itr.childs[curr] = new TrieNode();
            }
            itr = itr.childs[curr];
        }
        itr.endOfWord = true;
    }


    public TrieNode contains(String word) {
        TrieNode itr = root;
        for (char curr : word.toCharArray()) {
            if (itr.childs[curr] != null) {
                itr = itr.childs[curr];
                continue;
            } else {
                return null;
            }
        }
        return itr;
    }

    public TrieNode contains(char curr, TrieNode node) {
        TrieNode itr = node;
        if (itr.childs[curr] != null) {
            return itr.childs[curr];
        } else {
            return null;
        }
    }
}

