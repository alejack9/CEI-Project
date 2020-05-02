package ast;

public class STEntry {
	private String Id;
	private String type;
	private Integer nl;
	private Integer offset;
	
	public STEntry(String Id, String Type) {
		this.Id = Id;
		this.type =Type;
	}

	public String getId() {
		return Id;
	}

	public String getType() {
		return type;
	}

	public Integer getNl() {
		return nl;
	}

	public Integer getOffset() {
		return offset;
	}

}
