/**
 * 
 */
package org.pergamum.battlesnek.handlers;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.pergamum.battlesnek.api.SnekInitResponse;
import org.pergamum.battlesnek.yggdrasil.TreeNode;

/**
 * @author geoffreypeart
 *
 */
class RoborianTreeSnekTest {

	
	@Test
	void testEvaluateMove()
	{
		
	}
	
	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#initialize()}.
	 */
	@Test
	void testInitialize() {

		RoborianTreeSnek testSnekHandler = new RoborianTreeSnek();
		SnekInitResponse response = testSnekHandler.initialize();
		
		assertNotNull(response);
		assertNotNull(response.getName());
		assertNotNull(response.getAuthor());
		assertNotNull(response.getColor());
		assertNotNull(response.getHead());
		assertNotNull(response.getTail());
		assertNotNull(response.getApiversion());
		assertEquals("1",response.getApiversion());
			
	}

	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#move(org.pergamum.battlesnek.api.Request)}.
	 */
	@Test
	void testMove() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#start(org.pergamum.battlesnek.api.Request)}.
	 */
	@Test
	void testStart() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link org.pergamum.battlesnek.handlers.RoborianTreeSnek#end(org.pergamum.battlesnek.api.Request)}.
	 */
	@Test
	void testEnd() {
		//fail("Not yet implemented");
	}
	
	@Test void tapForSyrup() throws NoSuchMethodException, SecurityException
	{
		RoborianTreeSnek snekHandler = new RoborianTreeSnek();
		// Arrays.stream(snekHandler.getClass().getMethods()).forEach(meth -> meth.setAccessible(true)); ;
		
		// Method m = snekHandler.getClass().getMethod("tapForSyrup", TreeNode.class);
		
		
		
	}

}
