package hellogef.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Diagram extends AbstractModel {

	public static String PROP_NODE = "NODE";

	protected List nodes = new ArrayList();

	public void addNode(NodeModel node) {
		nodes.add(node);
		fireStructureChange(PROP_NODE, nodes);
	}

	public void removeNode(NodeModel node) {
		nodes.remove(node);
		fireStructureChange(PROP_NODE, nodes);
	}

	public List getNodes() {
		return nodes;
	}

}