package transform;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Lemmatizer {

	private StanfordCoreNLP pipeline;

	public Lemmatizer() {
		//Properties props = new Properties();
		//props.put("annotators", "lemma"); 
		//pipeline = new StanfordCoreNLP(props, false);
	}

	public String lemmatize(String token) {
		Annotation annotation = pipeline.process(token);
		String lemma = annotation.get(LemmaAnnotation.class);
		return lemma;
	}

}
