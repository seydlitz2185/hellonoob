public class IntList {
   	public int first;
   	public IntList rest;
  	public IntList(int f, IntList r) {
    	first = f;
		rest = r;
   	}
 
   /** Return the size of this IntList. */
	public int size() {
    	if (rest == null) {
        return 1;
      }
   	return 1 + this.rest.size();
	}
	
	public IntList get(int i){
    	if (this.rest != null &&this.rest.size() + i == this.size()) {
        return this;
      }else if (i ==1 ){return this;}
	  return this.rest.get(i-1);
	}
	
}