package com.tcj.domains.tree;
import java.util.ArrayList;  
import java.util.HashMap;
import java.util.List;  
import java.util.Map;  

import org.directwebremoting.annotations.DataTransferObject;
   
@DataTransferObject
public class TreeNode {  
  
    private String id;          //要显示的子节点的ID  
    private String text;        //要显示的子节点的 Text  
    private Map attributes=new HashMap();	 //要显示节点的url
    private String iconCls;     //节点的图标  
    private String parentId;    //父节点的ID  
    private List<TreeNode>  children;   //孩子节点的List  
    private int childrenCount = 0;
  
    public TreeNode(){}  
  
    public TreeNode(String id, String text, Map attributes, String iconCls, String parentId,  
  
           List<TreeNode>children) {  
  
       super();  
       this.id= id;  
       this.text= text;  
       this.attributes=attributes;
       this.iconCls= iconCls;  
       this.parentId= parentId;  
       this.children= children;  
    }  
  
     
  
    public String getId() {  
  
       return id;  
  
    }  
  
    public void setId(String id) {  
       this.id= id;  
    }  
  
    public String getText() {  
       return text;  
    }  
  
    public void setText(String text) {  
       this.text= text;  
    }  
  
    public String getIconCls() {  
       return iconCls;  
    }  
  
    public void setIconCls(String iconCls) {  
       this.iconCls= iconCls;  
    }  
  
    public String getParentId(){   
       return parentId;  
    }  
  
    public void setParentId(String parentId) {  
       this.parentId= parentId;  
    }  
  
    public List<TreeNode> getChildren() {  
       return children;  
    }  
  
    public Map getAttributes() {
		return attributes;
	}

	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}

	public void setChildren(List<TreeNode> children) {  
       this.children= children;  
    }  
  
	public void setAttributes(String key,Object value) {
		this.attributes.put(key, value) ;
	}
    //添加孩子的方法  
  
    public void addChild(TreeNode node){  
       if(this.children == null){  
           children= new ArrayList<TreeNode>();  
           children.add(node);  
       }else{  
           children.add(node);  
       }  
            
    }

	public int getChildrenCount() {
		if(this.children != null){
			this.childrenCount = this.children.size();
		}
		return childrenCount;
	}

	public void setChildrenCount(int childrenCount) {
		this.childrenCount = childrenCount;
	}  
     
}