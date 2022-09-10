package model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Game {
	private int target;
	List<Guess> guesses;
	boolean win;
	long start;
	long duration;
	long score;
	public Game() {
		this.win=false;
		this.target = (int) Math.floor(Math.random()*(9000)+1000);
		this.guesses = new LinkedList<Guess>();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.start = timestamp.getTime()/1000;
		this.score=0;
	}
	
	public long getSpentTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return (timestamp.getTime()/1000-this.start);
	}
	
	public boolean isWin() {
		return win;
	}
	public void setWin() {
		this.win = true;
		setDuration(getSpentTime());
		this.score=2000000/(getDuration()*getGuesses().size());
	}
	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getStart() {
		return start;
	}

	public List<Guess> getGuesses() {
		return guesses;
	}
	public void addGuesses(Integer essai) {
		if (essai==0) {return;}
		Guess guess= new Guess(essai.toString(),Integer.toString(getTarget()),guesses.size());
		this.guesses.add(0,guess);
	}
	public int getTarget() {
		return target;
	}

	public long getScore() {
		return score;
	}
	
	
}
