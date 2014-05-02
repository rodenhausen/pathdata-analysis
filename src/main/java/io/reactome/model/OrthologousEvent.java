
package io.reactome.model;

public class OrthologousEvent{
   	private Number dbId;
   	private String displayName;
   	private boolean hasDiagram;
   	private boolean isInDisease;
   	private boolean isInferred;
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
 	public boolean getHasDiagram(){
		return this.hasDiagram;
	}
	public void setHasDiagram(boolean hasDiagram){
		this.hasDiagram = hasDiagram;
	}
 	public boolean getIsInDisease(){
		return this.isInDisease;
	}
	public void setIsInDisease(boolean isInDisease){
		this.isInDisease = isInDisease;
	}
 	public boolean getIsInferred(){
		return this.isInferred;
	}
	public void setIsInferred(boolean isInferred){
		this.isInferred = isInferred;
	}
 	public String getSchemaClass(){
		return this.schemaClass;
	}
	public void setSchemaClass(String schemaClass){
		this.schemaClass = schemaClass;
	}
}
