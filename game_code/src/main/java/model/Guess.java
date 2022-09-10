package model;

import java.util.Arrays;

public class Guess {
	String essai;
	Integer[] result;
	int order;
	
	public Guess(String essai,String target,int order) {
		this.essai=essai;
		this.order=order++;
		result= new Integer[4]; 
		int[] counts = {0,0,0,0,0,0,0,0,0,0};
		for (int i=0;i<4;i++) {
			if (essai.charAt(i)==target.charAt(i)){
				result[i]=1;
				}
			else {
				result[i]=0;
				counts[Character.getNumericValue(target.charAt(i))]++;
				System.out.println(Character.getNumericValue(target.charAt(i)));
				
			}
		}
		System.out.println(Arrays.toString(counts));
		
		for (int i=0;i<4;i++) {
			if (result[i]==1)continue;
			if (target.contains(Character.toString(essai.charAt(i)))) {
				if (counts[Character.getNumericValue(essai.charAt(i))]!=0) {
					result[i]=2;
					counts[Character.getNumericValue(essai.charAt(i))]--;
				}
			}
		}
		System.out.println(Arrays.toString(counts));
		
		
		
		
	}

	public String getEssai() {
		return essai;
	}

	public Integer[] getResult() {
		return result;
	}

	public int getOrder() {
		return order;
	}
	
	public String getBootstrapClass(int WhichOne) {
		switch (result[WhichOne]) {
		case 0:
			return "";
		case 1:
			return "bg-success";		
		default:
			return "bg-warning";
		}
	}
	 
}
