package com.bryanreinero.hum.element;

import java.util.*;

import com.bryanreinero.hum.visitor.*;

import com.google.code.morphia.annotations.*;

@Embedded
public class NonDetermintistic extends HumElement {
    private String id;
    private ArrayList<Path> paths;
    static Random rand = new Random();
    
    abstract class BranchComparator implements Comparator<Path> {}
        
    private void sortByWeight() {
        
        Collections.sort(getPaths(), new BranchComparator() {
            public int compare(Path branchA, Path branchB) {
                
                if (branchA.getWeight() < branchB.getWeight())
                    return 1;
                else if (branchA.getWeight() > branchB.getWeight())
                    return -1;
                return 0;
            }
        });
        
    }
    
    public void initialize() throws Exception {
        
        Iterator<Path> it = paths.iterator();
        
        int cumulativeWeight = 0; 
        while(it.hasNext()){
            Path branch = it.next();
            
            if( branch.getWeight() < 0 || branch.getWeight() > 100 )
                throw new Exception("Bad Branch weight");
            
            cumulativeWeight += branch.getWeight();
        }
        
        if(cumulativeWeight != 100 )
            throw new Exception("Branch weights do not sum to 100");
       
        sortByWeight(); 
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    
    public void addChild(Path element){
        if(paths == null)
            paths = new ArrayList<Path>();
        paths.add(element);
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }
    
    public static int getNextInt(){
        return rand.nextInt(100);
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId(){
        return id;
    }

	@Override
	public void addParent(HumElement element) throws IllegalArgumentException {
		element.addChild(this);
	}
}
