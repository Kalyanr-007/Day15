package com.day15.bridgelabz;

    import java.util.*;

    public class MyMapNode<K, V> {
        K key;
        V value;
        MyMapNode<K, V> next;

        public MyMapNode(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    class MyHashMap<K, V> {
        private final int numBuckets;
        List<MyMapNode<K, V>> bucketArray;

        public MyHashMap() {
            numBuckets = 10;
            bucketArray = new ArrayList<>(numBuckets);

            for (int i = 0; i < numBuckets; i++) {
                bucketArray.add(null);
            }
        }

        public V get(K key) {
            int bucketIndex = getBucketIndex(key);
            MyMapNode<K, V> head = bucketArray.get(bucketIndex);

            while (head != null) {
                if (head.key.equals(key)) {
                    return head.value;
                }
                head = head.next;
            }

            return null;
        }

        public void add(K key, V value) {
            int bucketIndex = getBucketIndex(key);
            MyMapNode<K, V> head = bucketArray.get(bucketIndex);

            while (head != null) {
                if (head.key.equals(key)) {
                    head.value = value;
                    return;
                }
                head = head.next;
            }

            head = bucketArray.get(bucketIndex);
            MyMapNode<K, V> newNode = new MyMapNode<>(key, value);
            newNode.next = head;
            bucketArray.set(bucketIndex, newNode);
        }

        public void display() {
            for (int i = 0; i < numBuckets; i++) {
                MyMapNode<K, V> head = bucketArray.get(i);
                while (head != null) {
                    System.out.println(head.key + ": " + head.value);
                    head = head.next;
                }
            }
        }

        private int getBucketIndex(K key) {
            int hashCode = key.hashCode();
            return Math.abs(hashCode) % numBuckets;
        }
    }

     class WordFrequency {
        public static void main(String[] args) {
            String sentence = "To be or not to be";
            String[] words = sentence.toLowerCase().split(" ");

            MyHashMap<String, Integer> wordFrequencyMap = new MyHashMap<>();

            for (String word : words) {
                Integer frequency = wordFrequencyMap.get(word);
                if (frequency == null) {
                    wordFrequencyMap.add(word, 1);
                } else {
                    wordFrequencyMap.add(word, frequency + 1);
                }
            }

            wordFrequencyMap.display();
        }
    }


