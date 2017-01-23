package net.bloberry.poker;

import static org.junit.Assert.*;

import org.junit.Test;

import net.bloberry.poker.WinningHand;

public class WinningHandTest {
   
	public static final String[] flush = {"3H", "4H", "5H", "6H", "8H"};
	public static final String[] threeOfKind = { "3C", "3D", "3S", "8C", "0D"}; 
	public static final String[] onePair = { "AC", "0C", "5C", "2S", "2C"};
	
	@Test
	public void winningHandConstructorTest1() {
		WinningHand wh = new WinningHand();
		assertNotNull(wh.getRank());
		assertNotNull(wh.getSuit());
	}

	@Test
	public void winningHandConstructorTest2() {
		WinningHand wh = new WinningHand(flush);
		assertNotNull(wh.getRank());
		assertNotNull(wh.getSuit());
	}


	@Test
	public void onePairTest() {
		WinningHand wh = new WinningHand(onePair);
		assertTrue("onePair",wh.onePair());
		
	}
	
	
	@Test
	public void threeOfKindTest() {
		WinningHand wh = new WinningHand(threeOfKind);
		assertTrue("threeOfKind",wh.threeOfKind());
		assertTrue("onePair",wh.onePair());


	}

	@Test
	public void flushTest() {
		WinningHand wh = new WinningHand(flush);
		assertTrue("flush",wh.flush());
	}
	
	
}
