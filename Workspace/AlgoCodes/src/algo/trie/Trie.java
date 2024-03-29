package algo.trie;

import java.util.ArrayList;

class TrieNode {
	public boolean isEnd;
	public TrieNode[] children;

	public TrieNode(int size) {
		this.isEnd = false;
		this.children = new TrieNode[size];
	}

}

public class Trie {
	public TrieNode root;
	public int count;
	public static final int ALPHABET_SIZE = 26;

	public Trie() {
		this.root = new TrieNode(ALPHABET_SIZE);
		this.count = 0;
	}

	void insert(char[] key) {
		TrieNode crawl = this.root;
		this.count++; // keeping track of total number of inserts
		int index = -1;
		for (int level = 0; level < key.length; level++) {
			index = key[level] - 'a'; // get the proper index of the character
										// in child array
			if (crawl.children[index] == null) {// check for characters already
												// present
				crawl.children[index] = new TrieNode(ALPHABET_SIZE); // initializing
																		// for
																		// new
																		// character
			}
			crawl = crawl.children[index];// changing root node to child
		}
		crawl.isEnd = true;// marking leaf node with 1

	}

	boolean search(char[] key) {
		TrieNode crawl = this.root;
		int index = -1;
		for (int level = 0; level < key.length; level++) {
			index = key[level] - 'a';
			if (crawl.children[index] == null)
				return false;
			crawl = crawl.children[index];
		}
		// return true; for any prefix matches
		return (crawl.isEnd); // for only exact matches
	}

	void autocomplete(char[] key) {
		TrieNode crawl = this.root;
		int index = -1;
		for (int level = 0; level < key.length; level++) {
			index = key[level] - 'a';
			if (crawl.children[index] == null)
				break;
			crawl = crawl.children[index];
		}
		if (crawl != this.root || key.length == 0) {
			ArrayList<String> words = new ArrayList<>();
			getWords(crawl, "", words);
			System.out.println(words);
		}
	}

	public void getWords(TrieNode crawl, String s, ArrayList<String> words) {
		if (crawl.isEnd) {
			words.add(s);
			return;
		}
		for (int i = 0; i < ALPHABET_SIZE; i++) {
			if (crawl.children[i] != null) {
				getWords(crawl.children[i], s + ((char) (i + 'a')), words);
			}
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("freeze".toCharArray());
		trie.insert("freak".toCharArray());
		trie.insert("frying".toCharArray());
		trie.insert("freedom".toCharArray());
		trie.insert("funyyou".toCharArray());
		// System.out.println(trie.search("freedom".toCharArray()));
		trie.autocomplete("fr".toCharArray());

	}
}
