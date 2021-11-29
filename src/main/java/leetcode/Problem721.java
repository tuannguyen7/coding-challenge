package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem721 {

    public static class Node {
        String email;
        String name;
        Set<String> children;

        public Node(String email, String name) {
            this.email = email;
            this.name = name;
            this.children = new HashSet<>();
            addChild(email);
        }

        public void addChild(String email) {
            children.add(email);
        }

        public void addChildren(Set<String> emails) {
            children.addAll(emails);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Node> emailParent = new HashMap<>();
        Map<String, Node> roots = new HashMap();
        List<List<String>> list = new ArrayList<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            Node root = null;
            for (int i = 1; i < account.size(); i++) {
                root = getRoot(account.get(i), emailParent);
                if (root != null) break;
            }
            if (root == null) {
                root = new Node(account.get(1), name);
                roots.put(root.email, root);
            }
            for (int i = 1; i < account.size(); i++) {
                Node oldRoot = getRoot(account.get(i), emailParent);
                if (oldRoot == root) {
                    continue;
                }
                if (oldRoot == null) {
                    emailParent.put(account.get(i), root);
                    root.addChild(account.get(i));
                } else {
                    emailParent.put(oldRoot.email, root); // old root to new root
                    root.addChildren(oldRoot.children);
                    roots.remove(oldRoot.email);
                }
            }
        }

        for (Map.Entry<String, Node> entry : roots.entrySet()) {
            Node n = entry.getValue();
            List<String> element = new ArrayList<>();
            element.add(n.name);
            List<String> sortedEmails = n.children.stream().sorted().collect(Collectors.toList());
            element.addAll(sortedEmails);
            list.add(element);
        }
        return list;
    }

    public Node getRoot(String email, Map<String, Node> emailParent) {
        if (!emailParent.containsKey(email))
            return null;
        Node n = emailParent.get(email);
        if (n.email.equals(email)) { // this is root
            return n;
        }
        Node root = getRoot(n.email, emailParent);
        emailParent.put(email, root);
        return root;
    }

    public static void main(String[] args) {
        Problem721 app = new Problem721();
    }
}
