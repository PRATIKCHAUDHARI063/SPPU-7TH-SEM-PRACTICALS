import java.util.*;

class Node implements Comparable<Node> {
    String character;
    int frequency;
    Node left, right;

    public Node(String character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public Node(Node left, Node right) {
        this.character = left.character + right.character;
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public int compareTo(Node other) {
        return this.frequency - other.frequency;
    }
}

public class HuffmanPractice {
    public static Map<String, String> huffmanCodeMap = new HashMap<>();

    public static void buildHuffmanCode(Node node, String code) {
        if (node.isLeaf()) {
            huffmanCodeMap.put(node.character, code);
        } else {
            buildHuffmanCode(node.left, code + "0");
            buildHuffmanCode(node.right, code + "1");
        }
    }

    public static void main(String[] args) {
        String str = "BCAADDDCCACACAC";

        // Step 1: Frequency map
        Map<String, Integer> freqMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            freqMap.put(String.valueOf(c), freqMap.getOrDefault(String.valueOf(c), 0) + 1);
        }

        // Step 2: Priority queue
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Step 3: Build Huffman Tree
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            queue.add(new Node(left, right));
        }

        // Step 4: Generate Huffman codes
        buildHuffmanCode(queue.poll(), "");

        // Step 5: Display the codes
        System.out.println("Char | Huffman Code");
        System.out.println("-------------------");
        for (Map.Entry<String, String> entry : huffmanCodeMap.entrySet()) {
            System.out.println(" " + entry.getKey() + "   | " + entry.getValue());
        }
    }
}

