import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class demo {

	public static void main(String[] args) {
	List<String> ll= new ArrayList<String>();
	ll.add("?16999");
	ll.add("18,000");
	ll.add("0");
	ll.add("0");
	

	System.out.println(ll);
	
	// ll.get(0).replaceAll("[?,]","");
	
	//String new2 = new1.replace(",","");
	//ll.get(0).replace(","," ");
	List<Integer> num =new ArrayList<Integer> (Integer.parseInt(ll.get(0).replaceAll("[?,]","")));
	Iterator<Integer> itr = num.iterator();
	while(itr.hasNext())
		System.out.println(itr.next());
	
}
}
