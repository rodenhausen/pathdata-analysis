package analyze

import scala.collection.convert.wrapAll._
import java.util.Properties
import java.io.File
import org.biopax.paxtools.io.SimpleIOHandler
import java.io.FileInputStream
import org.biopax.paxtools.pattern.Pattern
import org.biopax.paxtools.model.level3.Conversion
import org.biopax.paxtools.model.level3.Named
import org.biopax.paxtools.model.level3._

object MainAnalysis2 {

   val file = "3296482.owl";
   val phosphorilation = Array("phosphorylates","phosphorylated", "phosphorylation")
   val binding = Array("bind", "bound", "form", "interact", "recruit", "assoc", "assemb")
   
   /*    bind
    binding
    bound
    formed a stable complex 
    physical interaction
    recruit
    formation of
    form heteromeric complexes
    association
    interact
    interaction
    form a complex
    associates
    assembly*/
   
   def main(args: Array[String]): Unit = {
      val loader = Thread.currentThread().getContextClassLoader();
    val properties = new Properties();
    properties.load(loader.getResourceAsStream("config.properties"));
    val dir = properties.getProperty("reactomeDir");
    val sourceFile = new File(dir, file)
    
    /*
  	val io = new SimpleIOHandler();
	val model = io.convertFromOWL(new FileInputStream(sourceFile)); 

		//val p = new Pattern(classOf[Named], "named");
		val nameds = model.getObjects(classOf[Named]);
		
		nameds.foreach(y => {
		  phosphorilation.foreach(x => {
		    if(y.getDisplayName() != null && y.getDisplayName().contains(x)) {
		      println("phosphylation: " + y.getDisplayName() + " id: " + y.toString())
		      y.getXref().foreach(r => {
		        if(r.isInstanceOf[PublicationXref]) {
			        println("db: " + r.getDb())
			        println("id: " + r.getId())
			        println("title: " + r.asInstanceOf[PublicationXref].getTitle())
		      	}
		      })
		      println()
		    }
		  })
		})
		
		println("\n-----------\n")
		
		nameds.foreach(y => {
		  binding.foreach(x => {
		    if(y.getDisplayName() != null && y.getDisplayName().contains(x)) {
		      println("binding: " + y.getDisplayName() + " id: " + y.toString())
		      y.getXref().foreach(r => {
		        if(r.isInstanceOf[PublicationXref]) {
			        println("db: " + r.getDb())
			        println("id: " + r.getId())
			        println("title: " + r.asInstanceOf[PublicationXref].getTitle())
		      	}
		      })
		      println()
		    }
		  })
		})*/
    
    	val textDir = "analysis2FullTexts"
    	
		
   }
}
/*
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
		})*/
   // })
    
    /*
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
    })*/
