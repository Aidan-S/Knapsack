package java.util;

public class ExpressionTree extends TreeNode implements Expressions{
	
	//dang
	ExpressionTree(Object v){
		super(v);
		TreeNode n = buildTree(null);
		this.setLeft(n.getLeft());
		this.setRight(n.getRight());
		this.setValue(n.getValue());
	}

	@Override
	public java.util.HashMap.TreeNode buildTree(String[] exp) {
		Stack c =new Stack<TreeNode>();
		TreeNode t;

		//TreeNode t = new TreeNode(exp[exp.length-1]);
		for(int i = 0; i < exp.length-1; i++) {
			if(exp[i] != "*" && exp[i] != "+" ) {
				c.push(new TreeNode(exp[i]));
			}else {
				t = new TreeNode(exp[i]);
				c.push(t);
			}
		}
		
		return c.pop();
	}

	@Override
	public int evalTree() {
		int a = 0;
		return evalTree(a);
	}
	
	public int evalTree(int sum) {
		String a = "";
		String b = "";
		if(this.getLeft().getLeft() == null) {
			a  = (String) this.getLeft().getValue();
			b  = (String) this.getRight().getValue();
			return Integer.parseInt(a + this.getValue() + b);
		}else {
			this.getLeft().evalTree();
		}
		if(this.getRight().getRight() == null) {
			a  = (String) this.getLeft().getValue();
			b  = (String) this.getRight().getValue();
			return Integer.parseInt(a + this.getValue() + b);
		}
		
		
		return sum;
	}
	
	@Override
	public String toPrefixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toInfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPostfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int postfixEval(String[] exp) {
		Stack c = new Stack<String>();
		int r = 0;
		for(int i = 0; i < exp.length-1; i++) {
			if(exp[i] != "*" && exp[i] != "+" ) {
				c.push(exp[i]);
			}else {
				r = Integer.parseInt(c.pop() + exp[i] + c.pop());
				c.push("" + r);
			}
		}
		return Integer.parseInt((String) c.pop());

	}
	
}
