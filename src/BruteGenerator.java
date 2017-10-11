import java.util.*;
public class BruteGenerator implements TextGenerator{
	private Random rand;
	private TrainingText text;

	BruteGenerator() {
		rand = new Random();
	}

	BruteGenerator(int seed) {
		rand = new Random(seed);
	}

	public int train(Scanner source, String delimiter, int k){
		text =  new TrainingText(source, delimiter, k); 
		return text.size();
	}

	public String generateText(int length){
		StringBuilder output = new StringBuilder();
		int dex = rand.nextInt((text.size()));
		NGram seed = text.get(dex);
		for(int i = 0; i < length; i++){
			ArrayList<NGram> grams = new ArrayList<NGram>();
			int startPos = 0; 
			while(text.indexOf(seed, startPos) + 1 < text.size()){
				startPos = text.indexOf(seed, startPos) + 1; 
				grams.add(text.get(startPos)); 
			}
			seed = grams.get(rand.nextInt(grams.size()));

			output.append(seed.toString());
		}
		return output.toString();

	}


}


