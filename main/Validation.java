package main;

public class Validation {

	public static boolean haveOnlyNumbers(String str) {
		int len = str.length();
		
		for(int i = 0 ; i < len ; i++){
			if(str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
}
