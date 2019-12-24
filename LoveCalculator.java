import java.util.Scanner;
import java.util.HashMap;

public class LoveCalculator {
	
	static Scanner skender = new Scanner(System.in);
	static boolean killSwitch = true;
	static String prvoIme = "";
	static String drugoIme = "";
	
    public static void main(String args[]) throws InterruptedException{
    /* MAIN */
	
	//TITLE graphic print-out:
	int timer1 = 100;
	int timer2 = 200;
	System.out.println("\n\n <3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3"); dly(timer1);
	System.out.println(" <3                            <3"); dly(timer1);
	System.out.println(" <3          L O V E           <3"); dly(timer1);
	System.out.println(" <3    C A L C U L A T O R     <3"); dly(timer1);
	System.out.println(" <3                            <3");
	System.out.println(" <3<3<3<3<3<3<3<3<3<3<3<3<3<3<3<3\n"); dly(timer2);  
		
	/*First name processing: 
	  this part takes the string as an input, puts it in all caps;
	  the method 'toAscii()' tokenizes the string, converts every 
	  char to integer and checks if the inserted string contains only letters. 
	  If ther are white-spaces, numbers or other characters inserted it returns 
	  a customised error message and asks for the input again. Then the int values
	  of chars are stored in an array. The method 'avg()' gives as an avarage double
	  value of stored ints:
	*/
	double avgPrvo = 0.0;
	System.out.print("   * Insert your name:  ");					
	while(killSwitch == true){
		String str = skender.nextLine(); 					
		prvoIme = str.toUpperCase();					
		int[] prvoImeArrInt = toAscii(prvoIme);				
		avgPrvo = avg(prvoImeArrInt);
	}
	
	killSwitch = true;
	System.out.println("");

	//Second name processing:			
	double avgDrugo = 0.0;
	System.out.print(" ~ Insert the second name: ");
	while(killSwitch == true){
		String str = skender.nextLine(); 					
		drugoIme = str.toUpperCase();					
		int[] drugoImeArrInt = toAscii(drugoIme);				
		avgDrugo = avg(drugoImeArrInt);
	}
	
	/*Now we have two avarage values (of both names) of the type double.
	  We want to get a number that tells us how are the two values related 
	  to each other. We will get a percentage if we divide the greater value
	  from the smaller one. The next code checks which one is greater and
	  divides accordingly.
	*/
	double avgRez;
	int rez;
	
	//Creates the result that holds the percentage:
	if(avgPrvo >= avgDrugo){
		avgRez = (avgDrugo/avgPrvo)*100;
		rez = (int) avgRez;
	}else{
		avgRez = (avgPrvo/avgDrugo)*100;
		rez = (int) avgRez;
	}
	
	/*We must multiply the result by 100 since we get a value between 1.00
	  and 0.00 ...or so I thought.
	  
	  The next problem arised: as I said - the code basicly checks how much apart
	  are the values of individual names. So if the names are the same, the result
	  is 100 % ...which kind of makes sense. But if the char values are on complete
	  oposite sides of ASCII table - 'A' and 'Z' (65/90 *100), the program returns 
	  a value of 72. So the lowest match possible in this program is 72 %. Which makes 
	  no sense.
	  
	  I wanted to lower the ratio of the results. We can notice that the result 
	  has a set of numers that range from 72 to 100 => that is 28 numers.
	  In other words - no matter what kind of strings we input, the result will
	  always range in that set. So I've set up a constant that came from dividing 
	  100 by 28 (since there's 28 numbers in the set of results). The value of this
	  constant is 3.571428periodical so i defined a constant KONS with the same value
	  (without the periodical part of course). That KONS will be multiplied with 
	  the "index" of the returned 'result'. 
	  
	  Example: 73 - the lowest result is the first number of our fictional set of
	  results (we get 73 by dividing 'a' and 'z'). So it has an fictional index of 1
	  74 is the second one, and so on .... 100 has and index of 28.
	  
	  Take the set and it's indexes real I've put the set of results (73-100) in a HashMap 
	  as keys and indexes (1-28) as values. That way the loop can check for the result in 
	  the HashMap and when the result is found it retuns its values (our index).
	  
	  The index is then multiplied with the KONS which returns the ultimate result.
	  
	  PS.: I am sure there are other easier and more correct ways to do this.
		   But I did this for fun with a (for now) very limited range of java knowdlege.	
	*/
	
	final double KONS = 3.571428;
	
	HashMap<Integer, Integer> hasis = new HashMap<Integer, Integer>();
		
	int j = 1;
	for(int i = 72; i < 101; i++){
		hasis.put(i, j);
		j++;
	}
			
	int match = (int) (hasis.get(rez)*KONS);
	
	/*One last little problem arised: If there's a perfect match, the final result is
	  103. I did't want the program to return a value that's greater than 100%
	  so the next few lines just subtract 3 from the max result.	
	*/
	if(match == 103){
		match -= 3;
	}
	
	//RESULT graphic print-out:
	
	System.out.print("\n      CALCULATING: "); 
	delayedPrint(" <3 <3 <3", 400);
	System.out.println("\n");
	System.out.print("         T");
	dly(75);
	delayedPrint("he match is: ", 75);
	System.out.println("\n");
	dly(2000);
	
	System.out.println("        @@@@@@           @@@@@@\r\n" + 
			"      @@@@@@@@@@       @@@@@@@@@@\r\n" + 
			"    @@@@@@@@@@@@@@   @@@@@@@@@@@@@@\r\n" + 
			"  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
			" @@@@\t\t\t\t  @@@@\r\n" + 
			"@@@@@\t    "+prvoIme+" && "+drugoIme+"\t  @@@@@\r\n" + 
			"@@@@@\t\t  "+match+" %	\t  @@@@@\r\n" + 
			"@@@@@\t\t\t\t  @@@@\r\n" + 
			" @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
			"  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
			"   @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
			"    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
			"      @@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
			"        @@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
			"          @@@@@@@@@@@@@@@@@@@\r\n" + 
			"            @@@@@@@@@@@@@@@\r\n" + 
			"              @@@@@@@@@@@\r\n" + 
			"                @@@@@@@\r\n" + 
			"                  @@@\r\n" + 
			"                   @");
	
	/* END MAIN */
    }	
	/* METODE */
	
	//Delay:
	public static void dly(int x) throws InterruptedException{
		Thread.sleep(x);
	}
	
	//Delayed print:
	public static void delayedPrint(String str, int millisec) throws InterruptedException{
		for(int i = 0; i<str.length(); i++){
			System.out.print(str.charAt(i) );
			dly(millisec);
		}
	}
	
	//NOVA toAscii METODA:
	public static int[] toAscii(String s){
		int[] TomArraya = new int[s.length()];
		for(int i=0;i<s.length();i++){
			if(s.charAt(i) >= 65 && s.charAt(i) <= 90){
				killSwitch = false;
				
			}else{
				System.out.println("\n       ! Error - wrong inout !");
				System.out.println(" Looks like there's a typo in the name.");
				System.out.println("    Be careful when writing names.");
				System.out.println("   If you have multiple first names");
				System.out.println("    type the names wihout spaces. \n");
				System.out.print  (" Please type the name again: ");
				
				killSwitch = true;
				break;
			}
			TomArraya[i] = s.charAt(i);
		}
		return TomArraya;
	}
	
	//avg: izracuna povprecno vrednost stevilk v tabeli:
	public static double avg (int[] tab){
		int sum = 0;
		for(int i=0;i<tab.length;i++){
			sum = sum+tab[i];
		}
		return sum/tab.length;
	}
	
	
}