package micro.examin.xml2woCsv.Leetcode;

import java.util.Hashtable;


class LRUCache {

	private Hashtable<Integer, DLinkedNode>
			cacheMap = new Hashtable<Integer, DLinkedNode>();
	private int count;
	private int maxSize;
	private DLinkedNode head, tail;

	public LRUCache(int capacity) {
		this.count = 0;
		this.maxSize = capacity;

		head = new DLinkedNode();
		head.pre = null;

		tail = new DLinkedNode();
		tail.post = null;

		head.post = tail;
		tail.pre = head;
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}

	/**
	 * Always add the new node right after head;
	 */
	private void addNode(DLinkedNode node) {

		node.pre = head;
		node.post = head.post;

		head.post.pre = node;
		head.post = node;
	}

	/**
	 * Remove an existing node from the linked list.
	 */
	private void removeNode(DLinkedNode node) {
		DLinkedNode pre = node.pre;
		DLinkedNode post = node.post;

		pre.post = post;
		post.pre = pre;
	}

	/**
	 * Move certain node in between to the head.
	 */
	private void moveToHead(DLinkedNode node) {
		this.removeNode(node);
		this.addNode(node);
	}

	// pop the current tail.
	private DLinkedNode popTail() {
		DLinkedNode res = tail.pre;
		this.removeNode(res);
		return res;
	}

	public int get(int key) {

		DLinkedNode node = cacheMap.get(key);
		if (node == null) {
			return -1; // should raise exception here.
		}

		// move the accessed node to the head;
		this.moveToHead(node);

		return node.value;
	}


	public void put(int key, int value) {
		DLinkedNode node = cacheMap.get(key);

		if (node == null) {

			DLinkedNode newNode = new DLinkedNode();
			newNode.key = key;
			newNode.value = value;

			this.cacheMap.put(key, newNode);
			this.addNode(newNode);

			++count;

			if (count > maxSize) {
				// pop the tail
				DLinkedNode tail = this.popTail();
				this.cacheMap.remove(tail.key);
				--count;
			}
		} else {
			// update the value.
			node.value = value;
			this.moveToHead(node);
		}
	}

	class DLinkedNode {
		int key;
		int value;
		DLinkedNode pre;
		DLinkedNode post;
	}
}