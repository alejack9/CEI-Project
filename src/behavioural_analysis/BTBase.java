package behavioural_analysis;

import java.util.LinkedList;

public abstract class BTBase {

	public static BTBlock add(BTAtom atom, BTBlock block){
		if(block != null)
			return new BTBlock(Math.max(0, block.maximum + atom.cost), 
				block.total + atom.cost, block.printedValues);
		
		else
			return new BTBlock(Math.max(0, atom.cost),
					atom.cost, new LinkedList<Integer>());
	}
	
	public static BTBlock add(BTPrint print, BTBlock block){
		LinkedList<Integer> printed = new LinkedList<Integer>();
		printed.add(print.value);
		if(block!= null){
			printed.addAll(block.printedValues);
			return new BTBlock(block.maximum, block.total, printed);
		}else{
			return new BTBlock(0,0, printed);
		}
	}
	
	public static BTBlock add(BTBlock block1, BTBlock block2){
		
		LinkedList<Integer> printed = new LinkedList<Integer>();
		printed.addAll(block1.printedValues);
		
		if(block2 != null){
			int max = Math.max(block1.maximum, block1.total + block2.maximum);
			int total = block1.total + block2.total;
			printed.addAll(block2.printedValues);
			return new BTBlock(max, total, printed);
		}else{
			return new BTBlock(block1.maximum, block1.total, printed);
		}
		 
	}

	public static BTBlock add(BTBase b, BTBlock current) {
		
		if(b instanceof BTAtom)
			return add((BTAtom)b, current);
		else if(b instanceof BTPrint)
			return add((BTPrint)b, current);
		return add((BTBlock)b, current);
	}
}
