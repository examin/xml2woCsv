import java.util.HashMap;
import java.util.TreeSet;

public class LFUCache {

	class Cache implements Comparable<Cache> {
		int key, f, r, val;
		public Cache(int k, int f, int r, int val) {key=k;this.f=f;this.r=r;this.val=val;}
		public boolean equals(Object object) {return key==((Cache) object).key;}
		public int hashCode() {return key;}
		public int compareTo(Cache o) {return key==o.key?0:f==o.f?r-o.r:f-o.f;}
	}

	int capacity,id;
	HashMap<Integer, Cache> caches;
	TreeSet<Cache> treeSet;

	public LFUCache(int capacity) {
		this.capacity=capacity;
		id=0;
		caches=new HashMap<>();
		treeSet=new TreeSet<>();
	}

	public int get(int key) {
		id++;
		if (caches.containsKey(key)) {
			update(key);
			return caches.get(key).val;
		}
		return -1;
	}

	public void put(int key, int value) {
		if (capacity==0) return;
		id++;
		if (caches.containsKey(key)) {
			update(key);
			caches.get(key).val = value;
			return;
		}
		if (caches.size()==capacity) {
			Cache first=treeSet.pollFirst();
			caches.remove(first.key);
		}
		Cache cache=new Cache(key, 1, id,value);
		caches.put(key, cache);
		treeSet.add(cache);
	}

	private void update(int key) {
		int f=caches.get(key).f;
		Cache cache = caches.remove(key);
		treeSet.remove(caches.get(key));
		cache.f = cache.f+1;
		caches.put(key, cache);
		treeSet.add(cache);
	}

}