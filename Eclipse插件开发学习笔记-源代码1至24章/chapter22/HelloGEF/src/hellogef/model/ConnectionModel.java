package hellogef.model;

public class ConnectionModel extends AbstractModel {

	private NodeModel source;

	private NodeModel target;

	public ConnectionModel(NodeModel source, NodeModel target) {
		super();
		this.source = source;
		this.target = target;
		source.addOutput(this);
		target.addInput(this);
		this.name = "Á¬Ïß";
	}

	public void setSource(NodeModel source) {
		this.source = source;
	}

	public void setTarget(NodeModel target) {
		this.target = target;
	}

	public NodeModel getTarget() {
		return this.target;
	}

	public NodeModel getSource() {
		return this.source;
	}

}
