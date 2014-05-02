package io.reactome.model;

import java.util.List;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Pathway{
   	private boolean _doRelease;
   	private List<Authored> authored;
   	private Created created;
   	private Number dbId;
   	private String displayName;
   	private List<Edited> edited;
   	private List<Figure> figure;
   	private GoBiologicalProcess goBiologicalProcess;
   	private boolean hasDiagram;
   	private String doi;
   	private List<Compartment> compartment;
   	private List<HasEvent> hasEvent;
   	private boolean isInDisease;
   	private boolean isInferred;
   	private List<LiteratureReference> literatureReference;
   	private List<Modified> modified;
   	private List<String> name;
   	private List<OrthologousEvent> orthologousEvent;
   	private String releaseDate;
   	private List<Revised> revised;
   	private List<Reviewed> reviewed;
   	private String schemaClass;
   	private List<Species> species;
   	private StableIdentifier stableIdentifier;
   	private List<Summation> summation;
   	private String releaseStatus;

 	public boolean get_doRelease(){
		return this._doRelease;
	}
	public void set_doRelease(boolean _doRelease){
		this._doRelease = _doRelease;
	}
 	public List<Authored> getAuthored(){
		return this.authored;
	}
	public void setAuthored(List<Authored> authored){
		this.authored = authored;
	}
 	public Created getCreated(){
		return this.created;
	}
	public void setCreated(Created created){
		this.created = created;
	}
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
 	public List<Edited> getEdited(){
		return this.edited;
	}
	public void setEdited(List<Edited> edited){
		this.edited = edited;
	}
 	public List<Figure> getFigure(){
		return this.figure;
	}
	public void setFigure(List<Figure> figure){
		this.figure = figure;
	}
 	public GoBiologicalProcess getGoBiologicalProcess(){
		return this.goBiologicalProcess;
	}
	public void setGoBiologicalProcess(GoBiologicalProcess goBiologicalProcess){
		this.goBiologicalProcess = goBiologicalProcess;
	}
 	public boolean getHasDiagram(){
		return this.hasDiagram;
	}
	public void setHasDiagram(boolean hasDiagram){
		this.hasDiagram = hasDiagram;
	}
 	public List<HasEvent> getHasEvent(){
		return this.hasEvent;
	}
	public void setHasEvent(List<HasEvent> hasEvent){
		this.hasEvent = hasEvent;
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
 	public List<LiteratureReference> getLiteratureReference(){
		return this.literatureReference;
	}
	public void setLiteratureReference(List<LiteratureReference> literatureReference){
		this.literatureReference = literatureReference;
	}
 	public List<Modified> getModified(){
		return this.modified;
	}
	public void setModified(List<Modified> modified){
		this.modified = modified;
	}
 	public List<String> getName(){
		return this.name;
	}
	public void setName(List<String> name){
		this.name = name;
	}
 	public List<OrthologousEvent> getOrthologousEvent(){
		return this.orthologousEvent;
	}
	public void setOrthologousEvent(List<OrthologousEvent> orthologousEvent){
		this.orthologousEvent = orthologousEvent;
	}
 	public String getReleaseDate(){
		return this.releaseDate;
	}
	public void setReleaseDate(String releaseDate){
		this.releaseDate = releaseDate;
	}
 	public List<Reviewed> getReviewed(){
		return this.reviewed;
	}
	public void setReviewed(List<Reviewed> reviewed){
		this.reviewed = reviewed;
	}
 	public String getSchemaClass(){
		return this.schemaClass;
	}
	public void setSchemaClass(String schemaClass){
		this.schemaClass = schemaClass;
	}
 	public List<Species> getSpecies(){
		return this.species;
	}
	public void setSpecies(List<Species> species){
		this.species = species;
	}
 	public StableIdentifier getStableIdentifier(){
		return this.stableIdentifier;
	}
	public void setStableIdentifier(StableIdentifier stableIdentifier){
		this.stableIdentifier = stableIdentifier;
	}
 	public List<Summation> getSummation(){
		return this.summation;
	}
	public void setSummation(List<Summation> summation){
		this.summation = summation;
	}
	public List<Compartment> getCompartment() {
		return compartment;
	}
	public void setCompartment(List<Compartment> compartment) {
		this.compartment = compartment;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public List<Revised> getRevised() {
		return revised;
	}
	public void setRevised(List<Revised> revised) {
		this.revised = revised;
	}
	
	
}
