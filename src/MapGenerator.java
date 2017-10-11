import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

public class MapGenerator implements TextGenerator{
	private Random rand; 
	private TrainingText text;
	private HashMap<NGram, List<NGram>> hashmap;

	MapGenerator() {
		rand = new Random();
	}
	MapGenerator(int seed) {
		rand = new Random(seed);

	} 
	public int train(Scanner source, String delimiter, int k){
		text =  new TrainingText(source, delimiter, k);

		hashmap = new HashMap<NGram, List<NGram>>();
		for(int i = 0; i < text.size() - 1; i++){
			ArrayList<NGram> ngrams = new ArrayList<NGram>();
			if(!hashmap.containsKey(text.get(i))){
				hashmap.put(text.get(i), ngrams);

			}
			hashmap.get(text.get(i)).add(text.get(i + 1));
		}

		return hashmap.keySet().size();
	}


	public String generateText(int length){
		StringBuilder output = new StringBuilder();
		int dex = rand.nextInt((text.size()));
		NGram seed = text.get(dex);
		for( int j = 0; j < length; j++){
			List<NGram> Grams = hashmap.get(seed);
			seed = Grams.get(rand.nextInt(Grams.size()));
			output.append(seed.toString());


		}
		return output.toString();
	}
}



