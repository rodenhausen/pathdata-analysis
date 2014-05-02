package analyze

import io.reactome.ReactomeClient
import java.util.Properties
import scala.collection.convert.wrapAll._
import java.io.BufferedWriter
import java.io.FileWriter
import java.io.File
import org.biopax.paxtools.io.SimpleIOHandler
import java.io.FileInputStream
import org.biopax.paxtools.pattern.Pattern
import org.biopax.paxtools.model.level3.BiochemicalReaction
import org.biopax.paxtools.model.level3.Conversion
import de.mpii.clausie.ClausIE
import edu.stanford.nlp.parser.lexparser.LexicalizedParserQuery
import edu.stanford.nlp.parser.lexparser.LexicalizedParser
import edu.stanford.nlp.process.PTBTokenizer
import edu.stanford.nlp.process.CoreLabelTokenFactory
import java.io.OutputStream
import com.jmcejuela.bio.jenia.JeniaTagger
import scala.collection.mutable.ListBuffer
import transform.Lemmatizer
import org.tartarus.martin.Stemmer
import transform.MyStemmer
import scala.collection.mutable.HashMap
import scala.reflect.internal.util.Collections
import scala.util.Sorting
import java.util.ArrayList
import edu.stanford.nlp.ling.HasWord
import edu.stanford.nlp.ling.TaggedWord
import edu.stanford.nlp.pipeline.ParserAnnotatorUtils
import org.biopax.paxtools.model.level3.Interaction
import analyze.model.ConversionCollection
import analyze.model.MyConversion

object Main {

  def main(args: Array[String]): Unit = {
    val loader = Thread.currentThread().getContextClassLoader();
    val properties = new Properties();
    properties.load(loader.getResourceAsStream("config.properties"));
    
    //use this one: http://reactomews.oicr.on.ca:8080/ReactomeRESTfulAPI/RESTfulWS/pathwayHierarchy/homo+sapiens
    // or frontPageItems/homo+sapiens?
    //storeBiopaxFiles(properties)
    
    val dir = properties.getProperty("reactomeDir");
    val sourceFiles = new File(dir).listFiles().filter(_.isFile)
    
    val typeConversionsMap = new HashMap[String, ListBuffer[MyConversion]]()
    val nameConversionsMap = new HashMap[String, ListBuffer[MyConversion]]()
    var trigger = 0
    var total = 0
    sourceFiles.foreach(x => {
    	println("----------------\n" + x.getAbsolutePath)
      	val io = new SimpleIOHandler();
		val model = io.convertFromOWL(new FileInputStream(x)); 

		val p = new Pattern(classOf[Conversion], "bcr");
		val conversions = model.getObjects(classOf[Conversion]);
		
		conversions.foreach(y => {
		  println(y.getDisplayName())
		  val heuristicName = heuristicallyGetInteractionName(y.getDisplayName())
		  println(heuristicName);
		  if(!heuristicName.startsWith("N/A"))
		    trigger += 1;
		  total += 1;
		  
		  y.getLeft().foreach(l => {
		    println(l)
		  })
		  println("=>")
		  y.getRight().foreach(r => {
		    println(r)
		  })
		  println()
		  
		  if(!nameConversionsMap.contains(heuristicName))
		    nameConversionsMap.put(heuristicName, new ListBuffer[MyConversion]())
		  nameConversionsMap.get(heuristicName).get.append(new MyConversion(y.getClass().toString(), y.getDisplayName(), y.getLeft().size(), y.getRight().size()))
		  
		  if(!typeConversionsMap.contains(y.getClass().toString()))
		    typeConversionsMap.put(y.getClass().toString(), new ListBuffer[MyConversion]())
		  typeConversionsMap.get(y.getClass().toString()).get.append(new MyConversion(y.getClass().toString(), y.getDisplayName(), y.getLeft().size(), y.getRight().size()))
		})
    })
    
    println("Result " + trigger + " / " + total)
    
    //val interactionCollections = new Array[InteractionCollection](interactions.size)
    val nameConversions = nameConversionsMap.map(x => new ConversionCollection(x._1, x._2.toList)).toList
    val sortedNameConversions = nameConversions.sortWith((x, y) => { x.conversions.size > y.conversions.size })
    
    sortedNameConversions.foreach(x => {
    	println(x.name + ": " + x.conversions.size)
    	var inTotal: Int = 0
    	var outTotal: Int = 0
    	x.conversions.foreach(i => { 
    	  inTotal += i.in
    	  outTotal += i.out
       	})
       	println("in: " + inTotal.toDouble / x.conversions.size)
       	println("out: "+ outTotal.toDouble / x.conversions.size)
    })
    
    val typeConversions = typeConversionsMap.map(x => new ConversionCollection(x._1, x._2.toList)).toList
    val sortedTypeConversions = typeConversions.sortWith((x, y) => { x.conversions.size > y.conversions.size })
    
    sortedTypeConversions.foreach(x => {
    	println(x.name + ": " + x.conversions.size)
    	var inTotal: Int = 0
    	var outTotal: Int = 0
    	x.conversions.foreach(i => { 
    	  inTotal += i.in
    	  outTotal += i.out
       	})
       	println("in: " + inTotal.toDouble / x.conversions.size)
       	println("out: "+ outTotal.toDouble / x.conversions.size)
    })
  }
  
  def storeBiopaxFiles(properties: Properties) {
    val url = properties.getProperty("reactomeUrl");
    val dir = properties.getProperty("reactomeDir");
    val reactomeClient = new ReactomeClient(url);
    reactomeClient.open();

    val result = reactomeClient.getPathways();
      
    val p = result.get()
    println(p.size())
    p.foreach(x => {
      storeBiopaxOfDbId(reactomeClient, dir, x.getDbId())
      x.getHasEvent().foreach(h => {
        storeBiopaxOfDbId(reactomeClient, dir, h.getDbId())
      })
      x.getOrthologousEvent().foreach(o => {
        storeBiopaxOfDbId(reactomeClient, dir, o.getDbId())
      })
    })
    reactomeClient.close();
  }
  
  def storeBiopaxOfDbId(reactomeClient: ReactomeClient, dir: String, dbId: Number) {
     val file = new File(dir + File.separator + dbId + ".owl")
      if(!file.exists()) {
	      val biopax = reactomeClient.getBioPaxExport(dbId.intValue())
	      val text = biopax.get()
	      if(!text.isEmpty()) {
	    	  val writer = new BufferedWriter(new FileWriter(file))
	    	  writer.append(text)
	    	  writer.close
      	}
      }
  }

  val lexicalizedParser = LexicalizedParser.loadModel("edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz");
  val tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
  val parserQuery = lexicalizedParser.parserQuery()
  val lemmatizer = new Lemmatizer()
  val stemmer = new MyStemmer()
  
  def heuristicallyGetInteractionName(displayName: String): String = {
    if (displayName == null)
      return "N/A null"
      
    //some heuristic rules
    val normalized = displayName.toLowerCase()
    if(normalized.contains("->"))
      return "=>";
    if(normalized.contains("=>"))
      return "=>";
    if(normalized.contains("<->"))
      return "<=>";
    if(normalized.contains("<=>"))
      return "<=>";
    if(displayName.trim().isEmpty())
      return "N/A"
  
    JeniaTagger.setModelsPath("models")
    //possibly give your own tokenizer as jenia doesn't yet have genias and only tokenizes on \\s+
    val sentence = JeniaTagger.analyzePos(displayName, true)
    val stanfordSentence = new ListBuffer[HasWord]();
    sentence.foreach(t => {
      stanfordSentence.append(new TaggedWord(t.text, t.pos))
    })
	parserQuery.parse(stanfordSentence);
	val depTree = parserQuery.getBestParse();
	val semanticGraph = ParserAnnotatorUtils.generateUncollapsedDependencies(depTree);
    //println(depTree.pennString())
	//println(semanticGraph.toFormattedString())
    
    val extraction = semanticGraph.getFirstRoot().word().toLowerCase()
    //val lemma = lemmatizer.lemmatize(extraction)
    val stem = stemmer.stem(extraction);
    
	return stem
  } 

}