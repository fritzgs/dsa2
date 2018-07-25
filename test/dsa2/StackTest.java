package dsa2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import dsa2.datastructures.Stack;

class StackTest {
	
	private Stack<Integer> stack = new Stack<>();

	
	@Test
	public void testStack(){
		stack.push(0);
		stack.push(1);
		assertEquals(1, stack.pop().intValue());
		assertEquals(0, stack.peek().intValue()); //the new top value
		
		
		assertFalse(stack.isEmpty());
		
		stack.push(10);
		stack.push(3);
		
		assertEquals(2, stack.search(0));
		
	} 
	
	


	
}
