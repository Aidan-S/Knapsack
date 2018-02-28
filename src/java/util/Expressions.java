package java.util;

import java.util.HashMap.TreeNode;

public interface Expressions {

	TreeNode buildTree(String[] exp);
	int evalTree();
	String toPrefixNotation();
	String toInfixNotation();
	String toPostfixNotation();
	int postfixEval(String[] exp);
}