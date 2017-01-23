package net.bloberry.poker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class WinningHand {
	private Map<String,List<String>> suit;
	private Map<String,List<String>> rank;

	public WinningHand(String[] cards ) {
		this.suit = new HashMap<String,List<String>>();
		this.rank = new HashMap<String,List<String>>();

		for(String card: cards ){
			String rank = card.substring(1,2);
			String suit = card.substring(0,1);
			addToSuitMap(rank, suit);
			addToRankMap(rank, suit);
		}
	}

	
	public WinningHand() {
		Set<String> fiveCards =  tossCards(5);
		this.suit = new HashMap<String,List<String>>();
		this.rank = new HashMap<String,List<String>>();
		for(String card : fiveCards){
			String rank = card.substring(1,2);
			String suit = card.substring(0,1);
			addToSuitMap(rank, suit);
			addToRankMap(rank, suit);
		}
	}
	
	private void addToSuitMap(String rank, String suit){
		List<String> rankList = this.suit.get(suit); 
		if( rankList == null ) rankList = new ArrayList<String>();
		rankList.add(rank);
		this.suit.put(suit , rankList);
	}
	
	private void addToRankMap(String rank, String suit){
		List<String> suitList = this.rank.get(rank); 
		if( suitList == null ) suitList = new ArrayList<String>();
		suitList.add(suit);
		this.rank.put(rank , suitList);
	}

	
	public Set<String> tossCards(int maxCards) {
		Set<String> cards = new HashSet<>(maxCards);
		int i = 0;
		do  {
		 String card = cardGenerator();
		 cards.add(card);
		} while (cards.size() < maxCards); 
		return cards;
	}
	
	private String cardGenerator(){
		Random generator = new Random(); 
		int rank_indx = generator.nextInt(12);
		int suit_indx = generator.nextInt(3);
		
		char[] suit = {'C','H','S','R'};
		char[] rank = {'2','3','4','5','6','7','8','9','0','J','Q','K','A'};
		
		return suit[suit_indx] + "" +rank[rank_indx]; 
	}
	
	
	/**
	 * This check for 'One Pair' combination  'One Pair': def:{ Rank1 = Rank2}
	 * @return
	 */
	public boolean onePair(){
		// loop over all values of Map
		for(List<String> ranks : this.getRank().values() ){
			if (ranks.size() >= 2 ) return true; 
		}
		return false;
	}
	
	/**
	 * This check for 'Two Pair' combination  'Two Pair': def:{Rank1 = Rank1 & Rank2 = Rank2 where Rank!=Rank2}
	 * @return
	 */
	public boolean twoPair(){
		// loop over all values of Map
		int rank = 0;
		for(List<String> ranks : this.getRank().values() ){
			if (ranks.size() >= 2 ) rank ++;
			if( rank == 2 ) return true;
		}
		return false;
	}

	/**
	 * This check for 'Three of Kind' combination  'Three of Kind'': def:{suit1 = suit2 = suit3  where suit1!=suit2!=suit3}
	 * @return
	 */
	public boolean threeOfKind(){
		// loop over all values of Map		
		for(List<String> suits : this.getSuit().values() ){
			if (suits.size() >= 3 ) return true; 
		}
		return false;
	}	
	
	/**
	 * This check for 'Four of Kind' combination  'Four of Kind'': def:{suit1 = suit2 = suit3 = suit4  }
	 * @return
	 */
	public boolean fourOfKind(){
		// loop over all values of Map		
		for(List<String> suits : this.getSuit().values() ){
			if (suits.size() >= 4 ) return true; 
		}
		return false;
	}	

	
	/**
	 * This check for 'Flush' combination  'Flush'': def:{suit1 = suit2 = suit3 = suit4 = suit5 where Rank1 != Rank2 != Rank3 != Rank4 != Rank5  }
	 * @return
	 */
	public boolean flush(){
		// loop over all values of Map		
		for(List<String> ranks : this.getRank().values() ){
			if (ranks.size() == 5 ) return true; 
		}
		return false;
	}

	/**
	 * This check for 'RoalFlush' combination  'RoyalFlush'': def:{'Flush'&  Rank1 = 'A' &  Rank2 = 'K' & Rank3 = 'Q' & Rank4 = 'J' & Rank5 = '0'   }
	 * @return
	 */
	public boolean royalFlush(){
		
		boolean ret = false;
		if(flush()){
			Collection<List<String>> ranks = this.getSuit().values();
			for(List<String> rankList : ranks )
			{
				int total = 0;
				for(String rank: rankList) {
					if("A".equals(rank)) total ++; 
					if("K".equals(rank)) total ++;
					if("Q".equals(rank)) total ++;
					if("J".equals(rank)) total ++;
					if("0".equals(rank)) total ++;
				}
				if(total == 5) ret =  true;
			}
		}
		return ret;
	}

	
	
	public Map<String, List<String>> getSuit() {
		return suit;
	}
	

	public void setSuit(Map<String, List<String>> suit) {
		this.suit = suit;
	}

	public Map<String, List<String>> getRank() {
		return rank;
	}

	public void setRank(Map<String, List<String>> rank) {
		this.rank = rank;
	};
	
	
}
