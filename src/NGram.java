import java.util.Arrays;
import java.util.List;

public class NGram implements Comparable<NGram> {

	private String[] contents;
	private String separator;

	public NGram(List<String> source, String sep) {
		separator = sep;
		contents = new String[source.size()];
		contents = Arrays.copyOf(source.toArray(new String[source.size()]), source.size());
	}

	public int compareTo(NGram other) {
		int c = contents.length;
		int o = other.contents.length;

		if( c < o){
			for( int i = 0; i < contents.length; i++){
				if(!contents[i].equals(other.contents[i])){
					return contents[i].compareTo(other.contents[i]);
				}
			}
		}else if(c > o){
			for(int p = 0; p < other.contents.length; p++){
				if(!(contents[p].equals(other.contents[p]))){
					return contents[p].compareTo(other.contents[p]);
				}
			}
		}else if(c == o){
			for(int k = 0; k < contents.length; k++){
				return contents[k].compareTo(other.contents[k]);
			}
		}
		if(c < o){
			return -1;
		} else if(c > o){
			return 1;
		}
		return 0;
	}
	

	public boolean equals(Object o) {
		if( o instanceof NGram){
			NGram newgram = (NGram) o; 
			if (!(newgram.contents.length == contents.length)){
				return false;
			}else{ 
				for (int i = 0; i < newgram.contents.length; i++){
					if(!contents[i].equals(newgram.contents[i])){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}

	public int hashCode() {
		int hash = 0, j = 1;
		for (int i = 0; i < this.contents.length; i++) {
			hash += this.contents[i].hashCode()*((j+117) % 31);
			j += 7;
		}
		return hash;
	}

	public String toString() {
		return contents[contents.length - 1] + "" + separator;
	}
}