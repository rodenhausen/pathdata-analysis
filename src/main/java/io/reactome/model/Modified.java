
package io.reactome.model;

public class Modified{
   	private Number dbId;
   	private String displayName;
   	private String schemaClass;

 	public Number getDbId(){
		return this.dbId;
	}
	public void setDbId(Number dbId){
		this.dbId = dbId;
	}
 	public String getDisplayName(){
		return this.displayName;
	}
	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}
 	public String getSchemaClass(){
		return this.schemaClass;
	}
	public void setSchemaClass(String schemaClass){
		this.schemaClass = schemaClass;
	}
}
