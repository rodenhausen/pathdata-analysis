package io.reactome.model;

public class Compartment {

	private Number dbId;
	private String displayName;
	private String schemaClass;
	
	public Compartment() { }	
	
	public Compartment(Number dbId, String displayName, String schemaClass) {
		super();
		this.dbId = dbId;
		this.displayName = displayName;
		this.schemaClass = schemaClass;
	}
	public Number getDbId() {
		return dbId;
	}
	public void setDbId(Number dbId) {
		this.dbId = dbId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getSchemaClass() {
		return schemaClass;
	}
	public void setSchemaClass(String schemaClass) {
		this.schemaClass = schemaClass;
	}
	
	
	
}
