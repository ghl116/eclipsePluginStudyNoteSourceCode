package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

import com.example.image.ImageConstants;

/**
 * TODO 
 * @2007-1-13
 * @author xuli
 */
public class TreeItemModel extends FNode {

	private boolean ifroot = false;

	public static final int ITEM_NOCHILD = 0;

	public static final int ITEM_EXPAND = 1;

	public static final int ITEM_COLLAPSED = 2;

	public static final int TYPE_ROOT = 0;

	public static final int TYPE_TABLE = 1;

	public static final int TYPE_COLUMN = 2;

	private int expand = ITEM_EXPAND; // 0: nothing, 1: expanded, 2:collapsed

	private int type = TYPE_ROOT; // 0: table, 1: column, 2:element, 3:leafElement

	private String[] typeImgs = {ImageConstants.STEP_ROOT,
			ImageConstants.STEP_TABLE,
			ImageConstants.STEP_COLUMN};  

	private String[] expImgs = {ImageConstants.STEP_MINUS,ImageConstants.STEP_ADD}; //$NON-NLS-1$ //$NON-NLS-2$

	protected Dimension fSize = new Dimension(120,25);

	private Dimension region = new Dimension(120,25);

	private int offset = 15; 

	private int depth = 0;

	private boolean ifRefresh = false;

	private int index = -1;

	private TreeItemModel selected = null;

	private Point expLoc = new Point(0,0);

	private Dimension expSize = new Dimension(10,10);

	private Point typeLoc = new Point(0,0);

	private Dimension typeSize = new Dimension(15,20);

	private Dimension labelSize = new Dimension(100,25);

	private Point labelLoc = new Point(0,0);

	private boolean vsb = true;

	private Stack oldVsb = new Stack();

	public TreeItemModel(){
	}

	public void setSelfSelected (){
		TreeItemModel root = findRoot();
		root.setSelected(this);
	}

	public void setSelected(TreeItemModel se){
		this.selected = se;
	}

	public TreeItemModel getSelected(){
		return this.selected;
	}

	public void expand(){
		if(this.expand == ITEM_COLLAPSED  && this.getChildren().size()>0){
			this.setExpand(ITEM_EXPAND);
			ifRefresh = true;
		}
	}
	
	public void collapse(){
		if(this.expand == ITEM_EXPAND  && this.getChildren().size()>0){
			this.setExpand(ITEM_COLLAPSED);
			ifRefresh = true;
		}
	}

	public boolean getIfRefresh(){
		if(this.ifRefresh){
			return true;
		}else{
			for(int i = 0; i < getChildren().size(); i++){
				TreeItemModel temp = (TreeItemModel)getChildren().get(i);
				if(temp.getIfRefresh()){
					return true;
				}
			}
		}
		return false;
	}

	public int getExpand() {
		return expand;
	}

	public void setExpand(int expand) {
		this.expand = expand;

	}

	public boolean isIfroot() {
		return ifroot;
	}

	public void setIfroot(boolean ifroot) {
		this.ifroot = ifroot;
		if(this.ifroot)
			this.type = this.TYPE_ROOT;
	}

	public void setOffset(int ost) {
		this.offset = ost;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Dimension refreshRegion(){
		int h = this.fSize.height; 
		if(this.expand == ITEM_EXPAND){
			for(int i = 0; i < this.getChildren().size(); i++){
				TreeItemModel ch = (TreeItemModel)this.getChildren().get(i);
				h += ch.refreshRegion().height;
			}
		}
		this.region.height = h;
		int w = this.fSize.width;
		w += this.getTotalDepth()*this.offset;
		this.region.width = w;
		this.size = this.region.getCopy();
		return this.getSize();
	}

	public int getTotalDepth(){
		int rst = 0;
		if(this.expand == ITEM_COLLAPSED || this.getChildren().size()==0){
			return rst;
		}
		rst++;
		int large = 0;
		if(this.expand == ITEM_EXPAND){
			for(int i = 0; i< this.getChildren().size();i++){
				TreeItemModel temp = (TreeItemModel)this.getChildren().get(i);
				int td = temp.getTotalDepth();
				if(td > large){
					large= td;
				}
			}
		}
		rst +=large;
		return rst;
	}

	private Point findPosition(Point p){
		Point rst = new Point();
		if(!this.ifroot){
			rst.x = this.findRoot().getLocation().x + this.offset*this.getDepth();
			TreeItemModel pa = (TreeItemModel)this.getParent();
			if(pa.getChildren().indexOf(this) == 0){
				rst.y = pa.getLocation().y  + this.fSize.height;
			}else{
				TreeItemModel upSibling = (TreeItemModel)pa.getChildren().get(pa.getChildren().indexOf(this)-1);
				rst.y = upSibling.getLocation().y  + upSibling.getSize().height;
			}
		}else{
			rst.x = p.x + 2;
			rst.y = p.y + 30;
		}
		return rst;
	}

	public TreeItemModel findRoot(){
		if(this.ifroot){
			return this;
		}
		TreeItemModel pa = (TreeItemModel)this.getParent();
		if(pa.ifroot){
			return pa;
		}else{
			return pa.findRoot();
		}
	}

	public int getDepth(){
		return this.depth;
	}

	public void setDepth(int d){
		this.depth = d;
		for(int i = 0; i < this.getChildren().size(); i++){
			TreeItemModel temp = (TreeItemModel)this.getChildren().get(i);
			temp.setDepth(this.depth+1);
		}
	}

	public void setLocation(Point p) {
		Dimension rgn = this.refreshRegion();
		this.location = this.findPosition(p);
		this.setSize(rgn);
		this.setExpLocation();
		this.setTypeLocation();
		this.setLabelLocation();
		if(this.getChildren() != null && this.getChildren().size()>0){
			Point temp = this.getLocation().getCopy();
			for(int i = 0; i < this.getChildren().size(); i++){
				TreeItemModel ti = (TreeItemModel)this.getChildren().get(i);
				ti.setLocation(temp);
				temp = ti.getLocation().getCopy();
			}
		}
	}

	public List getAllIncomings(){
		List l = new ArrayList();
		if(this.type == TreeItemModel.TYPE_COLUMN){
			return(this.getIncomingConnections());
		}else{
			for (Iterator iter = this.getChildren().iterator(); iter.hasNext();) {
				TreeItemModel child = (TreeItemModel) iter.next();
				l.addAll(child.getAllIncomings());
			}
		}

		return l;
	}

	public List getAllOutgoings(){
		List l = new ArrayList();
		if(this.type == TreeItemModel.TYPE_COLUMN){
			return(this.getOutgoingConnections());
		}else{
			for (Iterator iter = this.getChildren().iterator(); iter.hasNext();) {
				TreeItemModel child = (TreeItemModel) iter.next();
				l.addAll(child.getAllOutgoings());
			}
		}

		return l;
	}

	public List getShowIncomingConnections(){
		if((this.type == TreeItemModel.TYPE_COLUMN) && this.isVsb()){
			return this.getIncomingConnections();
		}
		List l = new ArrayList();
		if(!this.isVsb() || ((this.type != TreeItemModel.TYPE_COLUMN)&& this.expand != ITEM_COLLAPSED)){
			return Collections.EMPTY_LIST;
		}
		if(this.expand != ITEM_COLLAPSED){
			return(this.getIncomingConnections());
		}else{
			for (Iterator iter = this.getChildren().iterator(); iter.hasNext();) {
				TreeItemModel child = (TreeItemModel) iter.next();
				l.addAll(child.getAllIncomings());
			}
		}

		return l;
	}

	public List getShowOutgoingConnections(){
		if((this.type == TreeItemModel.TYPE_COLUMN) && this.isVsb()){
			return this.getOutgoingConnections();
		}
		List l = new ArrayList();
		if(!this.isVsb() || ((this.type != TreeItemModel.TYPE_COLUMN)&& this.expand != ITEM_COLLAPSED)){
			return Collections.EMPTY_LIST;
		}
		if(this.expand != ITEM_COLLAPSED){
			return(this.getOutgoingConnections());
		}else{
			for (Iterator iter = this.getChildren().iterator(); iter.hasNext();) {
				TreeItemModel child = (TreeItemModel) iter.next();
				l.addAll(child.getAllOutgoings());
			}
		}

		return l;
	}

	public void setExpLocation(){
		Point pnt = this.getLocation().getCopy();
		pnt.x = pnt.x + 2;
		pnt.y = pnt.y + 5;
		this.expLoc = pnt;
	}

	public void setTypeLocation(){
		Point pnt = this.getLocation().getCopy();
		pnt.x = pnt.x + 15;
		this.typeLoc = pnt;
	}

	public void setLabelLocation(){
		Point pnt = this.getLocation().getCopy();
		pnt.x = pnt.x + 35;
		pnt.y = pnt.y - 2;
		this.labelLoc = pnt;
	}

	public Point getLabelLocation(){
		setLabelLocation();
		return this.labelLoc;
	}

	public Point getExpLocation(){
		setExpLocation();
		return this.expLoc;
	}

	public Point getTypeLocation(){
		setTypeLocation();
		return this.typeLoc;
	}

	public Dimension getExpSize(){
		return this.expSize;
	}

	public Dimension getTypeSize(){
		return this.typeSize;
	}

	public Dimension getLabelSize(){
		return this.labelSize;
	}

	public String getExpImg(){
		if(this.expand == ITEM_NOCHILD){
			return null;
		}
		return this.expImgs[this.expand-1];
	}

	public String getTypeImg(){
		return this.typeImgs[this.type];
	}
	public void addChild(FElement child){
		addChild(-1,child);
		((TreeItemModel)child).setDepth(this.depth +1); 
	}

	public boolean isVsb() {
		return vsb;
	}

	public void setVsb(boolean v) {
		this.vsb = v;
	}
	public void hide(){
		this.oldVsb.push(new Boolean(this.vsb));
		this.vsb = false;
	}
	public void show(){
		if(this.oldVsb.size()<1){
			this.vsb = true;
		}else{
			this.vsb = ((Boolean)this.oldVsb.pop()).booleanValue();
		}
	}
	public void hideAllChildVsb(){
		for(Iterator iter = this.getChildren().iterator();iter.hasNext();){
			TreeItemModel child = (TreeItemModel)iter.next();
			child.hide();
			child.hideAllChildVsb();
		}
	}
	public void showAllChildVsb(){
		for(Iterator iter = this.getChildren().iterator();iter.hasNext();){
			TreeItemModel child = (TreeItemModel)iter.next();
			child.show();
			child.showAllChildVsb();
		}
	}
	public FStepModel findStepModel(){
		FElement step = this.getParent();
		if(step instanceof FStepModel){
			return (FStepModel)step;
		}else{
			if(step instanceof TreeItemModel){
				return ((TreeItemModel)step).findStepModel();
			}
		}
		return null;
	}

	public void removeAllConnections(){
		List incomings = this.getIncomingConnections();
		List outgoing = this.getOutgoingConnections();
		FConnection con = null;
		TreeItemModel item = null;
		if(incomings != null && incomings.size() > 0){
			for(Iterator iter = incomings.iterator(); iter.hasNext();){
				con = (FConnection)iter.next();
				item = (TreeItemModel)con.getSource();
				item.removeOutput(con);
			}
		}
		if(outgoing!= null && outgoing.size() > 0){
			for(Iterator iter = outgoing.iterator(); iter.hasNext();){
				con = (FConnection)iter.next();
				item = (TreeItemModel)con.getTarget();
				item.removeInput(con);
			}
		}
		if(this.children != null && this.children.size() > 0){
			TreeItemModel child = null;
			for(Iterator iter = this.children.iterator(); iter.hasNext();){
				child = (TreeItemModel)iter.next();
				child.removeAllConnections();
			}
		}
	}

	/***********************************************/
	// Abstract methods from IPropertySource
	public Object getPropertyValue(Object id) {
		if (PROP_NAME.equals(id))
			return getName();

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		if (PROP_NAME.equals(id))
			setName((String) value);
	}

	public boolean isPropertySet(Object id) {
		return true;
	}

	public void resetPropertyValue(Object id) {

	}
}

