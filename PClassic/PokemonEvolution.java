import java.util.*;
import java.io.*;

public class PokemonEvolution {
   public static void main(String[] args) throws Exception {
      Scanner infile = new Scanner(new File("PokemonEvolution.txt"));
      while (infile.hasNext()) {
         //String s = infile.next();
         System.out.println(work(infile));
      }
   }

   public static String work(Scanner sc) {
      // pokemons to find common ancestor of
      String first = sc.next();
      String second = sc.next();

      // map id to pokemon in a hashmap
      HashMap<String, String> pokemans = new HashMap<String, String>();
      while (sc.hasNext()) {
         String key = sc.next();
         String pokemon =
            sc.next() + " " + sc.next() + " " + sc.next();
         pokemans.put(key, pokemon);
      }

      // find root [possibly extraneous]
      int firstID = 0;
      for (int i = 0 ; i <= 100; i++) {
         if (pokemans.containsKey(i + "")) {
            firstID = i;
            break;
         }
      }

      // build tree
      String[] rootPokemon = pokemans.get("" + firstID).split(" ");
      BinaryTreeNode root = workHelper(rootPokemon, pokemans);

      // get paths to target pokemans
      String s1 = search(first, root, "");
      String s2 = search(second, root, "");

      // get common path
      String inCommon = "";
      for (int i = 0; i < s1.length() && i < s2.length(); i++) {
         if (s1.charAt(i) == s2.charAt(i)) {
            inCommon += "" + s2.charAt(i);
         } else {
            break;
         }
      }

      // get common ancestor
      if (inCommon.equals("")) {
         return root.getValue();
      } else {
         BinaryTreeNode curr = root;
         for (char c : inCommon.toCharArray()) {
            if (c == 'l') {
               curr = curr.getLeft();
            } else {
               curr = curr.getRight();
            }
         }
         return curr.getValue();
      }
   }

   // generate a string to get to the target
   public static String search(String target, BinaryTreeNode root, String now) {
      if (root == null) {
         return null;
      }

      if (root.getValue().equals(target)) {
         return now;
      }

      String left = search(target, root.getLeft(), now + "l");
      if (left != null) {
         return left;
      }

      String right = search(target, root.getRight(), now + "r");
      if (right != null) {
         return right;
      }

      return null;
   }

   // create a Tree
   public static BinaryTreeNode workHelper(String[] s, HashMap<String, String> pokemans) {
      if (s == null) { return null; }
      BinaryTreeNode root = new BinaryTreeNode(s[0], null, null);

      String s1 = pokemans.get(s[1]);
      if (s1 != null) {
         root.setLeft(workHelper(s1.split(" "), pokemans));
      }

      String s2 = pokemans.get(s[2]);
      if (s2 != null) {
         root.setRight(workHelper(s2.split(" "), pokemans));
      }

      return root;
   }
}


class BinaryTreeNode {
   private String value;
   private BinaryTreeNode left;
   private BinaryTreeNode right;

   public BinaryTreeNode(String v, BinaryTreeNode l, BinaryTreeNode r) {
      value = v;
      left = l;
      right = r;
   }

   public String getValue() {
      return value;
   }

   public BinaryTreeNode getRight() {
      return right;
   }

   public BinaryTreeNode getLeft() {
      return left;
   }

   public void setRight(BinaryTreeNode r) {
      right = r;
   }

   public void setLeft(BinaryTreeNode l) {
      left = l;
   }
}