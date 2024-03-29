package algo.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieUsingMap {

	private static class TrieNode {
		Map<Character, TrieNode> children;
		boolean isEnd;

		public TrieNode() {
			children = new HashMap<Character, TrieNode>();
			isEnd = false;
		}
	}

	private static class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word) {
			TrieNode crawl = root;
			for (int i = 0; i < word.length(); i++) {
				if (!crawl.children.containsKey(word.charAt(i)))
					crawl.children.put(word.charAt(i), new TrieNode());
				crawl = crawl.children.get(word.charAt(i));
			}
			crawl.isEnd = true;
		}

		public boolean search(String word) {
			TrieNode crawl = root;
			for (int i = 0; i < word.length(); i++) {
				if (!crawl.children.containsKey(word.charAt(i)))
					return false;
				crawl = crawl.children.get(word.charAt(i));
			}
			return crawl.isEnd;
		}

		public boolean startsWith(String prefix) {
			TrieNode crawl = root;
			for (int i = 0; i < prefix.length(); i++) {
				if (!crawl.children.containsKey(prefix.charAt(i)))
					return false;
				crawl = crawl.children.get(prefix.charAt(i));
			}
			return true;
		}
	}

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("freeze");
		trie.insert("free");
		trie.insert("freedom");
		trie.insert("freaking");
		System.out.println(trie.search("fry"));
		System.out.println(trie.search("freeze"));
		System.out.println(trie.search("freez"));
		System.out.println(trie.startsWith("fre"));
	}
}
