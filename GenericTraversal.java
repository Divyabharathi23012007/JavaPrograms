//Name: Divya Bharathi I
//Reg no: 2117240020096

import java.util.Queue;
import java.util.LinkedList;

class TreeNode<T>{
	T data;
	TreeNode<T> left, right;
	TreeNode(T data){
		this.data=data;
		left=right=null;
	}
}

class BinaryTree<T extends Comparable<T>>{
	TreeNode<T> root;
	void insert(T value) {
		root=insertNode(root, value);
		
	}
	TreeNode<T> insertNode(TreeNode<T> node, T value){
		if(node==null) return new TreeNode<>(value);
		if (value.compareTo(node.data)<0)
			node.left=insertNode(node.left,value);
		else
			node.right=insertNode(node.right, value);
		return node;
		
	}
	
	void inOrder(TreeNode<T> node) {
        if(node==null) return;
		inOrder(node.left);
		System.out.print(node.data+" ");
		inOrder(node.right);
	}
	
	void preOrder(TreeNode<T> node) {
		if(node==null) return;
		System.out.print(node.data+" ");
		preOrder(node.left);
		preOrder(node.right);
	}
	
	void postOrder(TreeNode<T> node) {
		if(node==null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data+" ");
	}
	
	void levelOrder(TreeNode<T> node) {
        if (node == null) return;
          Queue<TreeNode<T>> queue = new LinkedList<>();
          queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode<T> current = queue.poll();
            System.out.print(current.data + " ");
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

	
}

public class GenericTraversal{
	public static void main(String[] args) {
		BinaryTree<Integer> o = new BinaryTree<>();
		o.insert(10);
		o.insert(5);
		o.insert(20);
		
		System.out.println("LevelOrder: ");
		o.levelOrder(o.root);
		System.out.println();
		
		
		System.out.println("InOrder: ");
		o.inOrder(o.root);
		System.out.println();
		
		System.out.println("PreOrder: ");
		o.preOrder(o.root);
		System.out.println();
		
		System.out.println("PostOrder: ");
		o.postOrder(o.root);		
		
		System.out.println();
		System.out.println("Name: Divya Bharathi I");
		System.out.println("Reg no: 2117240020096");
		
		
	}
}