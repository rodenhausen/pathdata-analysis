package transform;

import org.tartarus.martin.Stemmer;
import weka.core.stemmers.SnowballStemmer;

public class MyStemmer {

	public String stem(String term) {
		SnowballStemmer sbStemmer = new SnowballStemmer();
		return sbStemmer.stem(term);
		
		/*Stemmer stemmer = new Stemmer();
		for(char c : term.toCharArray()) {
			stemmer.add(c);
		}
		return new String(stemmer.getResultBuffer());*/
		
	}
	
}