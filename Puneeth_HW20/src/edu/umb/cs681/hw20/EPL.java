package edu.umb.cs681.hw20;

import java.util.ArrayList;


public class EPL {

	private String teamName;
	private int matchesPlayed;
	private int won;
	private int points;
	
	public EPL(String teamName, int matchesPlayed, int won, int points) {
		this.teamName = teamName;
		this.matchesPlayed = matchesPlayed;
		this.won = won;
		this.points = points;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public int getWon() {
		return won;
	}

	public int getPoints() {
		return points;
	}

	public static void main(String args[]) {
		ArrayList<EPL> eplTable=new ArrayList<>();
		
		EPL chelsea = new EPL("Chelsea",20,12,32);
		EPL arsenal = new EPL("Arsenal",18,10,28);
		EPL liverpool = new EPL("Liverpool",17,10,26);
		EPL leicester = new EPL("Leicester",19,11,29);
		
		eplTable.add(chelsea);
		eplTable.add(arsenal);
		eplTable.add(liverpool);
		eplTable.add(leicester);
		
		//Maximum matches played by a team
		Integer maxMatchesPlayed = eplTable.stream().parallel().map((EPL epl)-> epl.getMatchesPlayed()).reduce(0, (result, matchesPlayed)->{
			if(result==0) 
				return matchesPlayed;
			else if(matchesPlayed > result) 
				return matchesPlayed; 
			return result;});

		//Maximum matches won
		Integer maxWon = eplTable.stream().parallel().map((EPL epl) -> epl.getWon()).reduce(0,(result, won)->{
			if(result==0)
				return won;
			else if(won > result)
				return won;
			return result;});

		//Total teams
		Integer count = eplTable.stream().parallel().map((EPL car) -> car.getWon()).reduce(1, (result, carPrice)->{
			return ++result;});
		
		//Maximum points
		Integer maxPoints = eplTable.stream().parallel().map((EPL epl) -> epl.getPoints()).reduce(0,(result, points)->{
			if(result==0)
				return points;
			else if(points > result)
				return points;
			return result;});
		
		System.out.println("-----------Using parallel streams------------");
		System.out.println("Total Teams: " + count );
		System.out.println("Maximum matches played by a team: " + maxMatchesPlayed);
		System.out.println("Maximum matches won by a team: " + maxWon);
		System.out.println("Maximum points of a team: " + maxPoints);
		
	}
}