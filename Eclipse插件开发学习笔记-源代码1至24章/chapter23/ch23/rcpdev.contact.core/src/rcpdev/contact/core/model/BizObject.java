package rcpdev.contact.core.model;


public class BizObject extends CommonObject {

	private String oid;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public boolean equals(Object o) {
		if (o == null)
			return super.equals(o);
		if (o.getClass().equals(this.getClass())) {
			BizObject co = (BizObject) o;
			if (this.getOid() == null || co.getOid() == null)
				return super.equals(o);
			return this.getOid().equals(co.getOid());
		}
		return super.equals(o);
	}
}
