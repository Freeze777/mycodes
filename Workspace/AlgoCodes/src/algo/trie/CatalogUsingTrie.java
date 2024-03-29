package algo.trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Data implements Comparable<Data> {
	public Long id;
	public Double price;
	public String category;
	public String name;

	public Data(long id, double price, String category, String name) {
		this.price = price;
		this.id = id;
		this.category = category;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Data [id=" + id + ", price=" + price + ", category=" + category
				+ ", name=" + name + "]";
	}

	@Override
	public int compareTo(Data o) {
		if (this.price.equals(o))
			return this.id.compareTo(o.id);
		return this.price.compareTo(o.price);
	}

}

public class CatalogUsingTrie {
	static Trie trie = new Trie();
	static TreeMap<Double,List<Data>> map=new TreeMap<Double,List<Data>>();
	private static class TrieNode {
		Map<Character, TrieNode> children;
		boolean isEnd;
		Data data;

		public TrieNode() {
			children = new TreeMap<Character, TrieNode>();
			isEnd = false;
		}
	}

	private static class Trie {
		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		public void insert(String word, Data data) {
			TrieNode crawl = root;
			for (int i = 0; i < word.length(); i++) {
				if (!crawl.children.containsKey(word.charAt(i)))
					crawl.children.put(word.charAt(i), new TrieNode());
				crawl = crawl.children.get(word.charAt(i));
			}
			crawl.isEnd = true;
			crawl.data = data;
			if(!map.containsKey(data.price))
				map.put(data.price,new ArrayList<Data>());
			map.get(data.price).add(data);				
			System.out.println("inserted product: " + data.name);
		}

		public Data search(String word) {
			TrieNode crawl = root;
			for (int i = 0; i < word.length(); i++) {
				if (!crawl.children.containsKey(word.charAt(i)))
					return null;
				crawl = crawl.children.get(word.charAt(i));
			}
			return crawl.isEnd ? crawl.data : null;
		}

		public void printContents() {
			System.out.println("###Contents###");
			dfs(root);
			System.out.println("#############");
		}

		private void dfs(TrieNode root) {
			if (root == null)
				return;
			if (root.isEnd)
				System.out.println(root.data);
			for (TrieNode node : root.children.values()) {
				dfs(node);
			}
		}

		public boolean startsWith(String prefix) {
			TrieNode crawl = root;
			for (int i = 0; i < prefix.length(); i++) {
				if (!crawl.children.containsKey(prefix.charAt(i)))
					return false;
				crawl = crawl.children.get(prefix.charAt(i));
			}
			dfs(crawl);
			return true;
		}
	}

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(new File("product.csv"));
			while (sc.hasNextLine()) {
				String[] input = sc.nextLine().split(",");
				Data data = new Data(Long.parseLong(input[0]),
						Double.parseDouble(input[2]), input[3], input[1]);
				trie.insert(input[1], data);

			}
			 trie.printContents();
			// System.out.println(trie.search("iphone 4s"));
			System.out.println(trie.startsWith("iph"));
			System.out.println(map.subMap(357.5,true, 23357.85,true));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
