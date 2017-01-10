import java.util.*;
import java.io.*;

public class cardgame {
	public static void main(String[] args) throws IOException {
		Scanner fin = new Scanner(new File("cardgame.in"));
		PrintWriter fout = new PrintWriter(new FileWriter("cardgame.out"));
		int N = fin.nextInt();
		Set<Integer> bessie_set = new HashSet<Integer>();
		for(int i = 1; i <= N * 2; i++) {
			bessie_set.add(i);
		}
		List<Integer> elsie = new ArrayList<Integer>(N);
		for(int i = 0; i < N; i++) {
			int c = fin.nextInt();
			elsie.add(c);
			bessie_set.remove(c);
		}
		List<Integer> bessie = new ArrayList<Integer>(bessie_set);
		Collections.sort(bessie); 
		LinkedList<Integer> bessie2 = new LinkedList<Integer>(bessie.subList(0, bessie.size() / 2));
		LinkedList<Integer> bessie1 = new LinkedList<Integer>(bessie.subList(bessie.size() / 2, bessie.size()));
		//Set<Integer> used = new HashSet<Integer>();
		//System.out.println(bessie1);
		//System.out.println(bessie2);
		int score = 0;
		for(int i = 0; i < N / 2; i++) {
			int elsie_card = elsie.get(i);
			int bessie_card = 0;
			if(elsie_card > bessie1.getLast()) {
				//System.out.println("SKIP Elsie = " + elsie_card);
				bessie1.removeFirst();
				continue;
			}
			ListIterator<Integer> iter = bessie1.listIterator();
			while(iter.hasNext()) {
				int bc = iter.next();
				if(bc > elsie_card) {
					bessie_card = bc;
					iter.remove();
					break;
				}
			}
			
			/*
			for(int j = N/2; j < bessie.size(); j++) {
				if(!used.contains(bessie.get(j)) && bessie.get(j) > elsie_card) {
					bessie_card = bessie.get(j);
					used.add(bessie_card);
					break;
				}
			}
			*/
			//System.out.println(elsie_card + " E : B " + bessie_card);
			if(bessie_card > elsie_card) score++;
			//System.out.println(bessie1);
		}
		for(int i = 0; i < N / 2; i++) {
			int elsie_card = elsie.get(N / 2 + i);
			int bessie_card = N + 1;
			if(elsie_card < bessie2.getFirst()) {
				//System.out.println("SKIP Elsie = " + elsie_card);
				bessie2.removeLast();
				continue;
			}
			/*
			for(int j = N/2 - 1; j >= 0; j--) {
				if(!used.contains(bessie.get(j)) && bessie.get(j) < elsie_card) {
					bessie_card = bessie.get(j);
					used.add(bessie_card);
					break;
				}
			}
			*/
			ListIterator<Integer> iter = bessie2.listIterator(bessie2.size());
			while(iter.hasPrevious()) {
				int bc = iter.previous();
				//System.out.println("> " + bc);
				if(bc < elsie_card) {
					bessie_card = bc;
					iter.remove();
					break;
				}
			}
			//System.out.println(elsie_card + " E : B " + bessie_card);
			if(bessie_card < elsie_card) score++;
			//System.out.println(bessie2);
		}
		//System.out.println(score);
		fout.println(score);
		fout.close();
		
	}
} 
