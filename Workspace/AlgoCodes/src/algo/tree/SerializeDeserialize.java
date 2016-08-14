package algo.tree;


public class SerializeDeserialize {
     public void preOrderSerialization(TreeNode root,StringBuilder sb) {
         if(root==null){
             sb.append("null ");
             return;
         }
         sb.append(root.data+" ");
         preOrderSerialization(root.left,sb);
         preOrderSerialization(root.right,sb);
    }
   
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        preOrderSerialization(root,sb);
        return sb.toString();
    }
    
    public TreeNode preOrderDeserialization(String[] data,int[] idx) {
        if(data[idx[0]].equals("null"))
            return null;
        TreeNode node=new TreeNode(Integer.parseInt(data[idx[0]]));
        idx[0]++;
        node.left=preOrderDeserialization(data,idx);
        idx[0]++;
        node.right=preOrderDeserialization(data,idx);
        return node;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      int[] idx={0};
      return preOrderDeserialization(data.split(" "),idx);
        
    }
}
