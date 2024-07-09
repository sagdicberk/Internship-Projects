
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Bir LinkedList OLuþturuyoruz.  
		LinkedList<Integer> liste = new LinkedList<Integer>();
		
		// 1den 100e kadar sayýlarýn listeye alýnmasý.
		for (int i = 1; i <= 100; i++) {
			liste.add(i);
		}
		

		// 4e ve 2ye bölünen sayý adedi ve sayýlar
		System.out.println("-------------------------------------------------");
		System.out.println("4 ve 2 ile bölünen sayý adedi: " + liste.stream().filter(i -> i%4 == 0 ).count());
		System.out.print("Hem 4 Hem de 2 ile bölünen sayýlar: ");
		liste.stream().filter(i -> i%4 == 0).forEach(i -> System.out.print(i + " "));
		
		// tek sayý adedi ve toplamlarý
		System.out.println("\n-------------------------------------------------");
		int Count4Odd = (int) liste.stream().filter(i -> i%2 != 0 ).count();
		System.out.println("Tek Sayý Adedi: " + Count4Odd );
		System.out.print("Tek Sayýlar: ");
		liste.stream().filter(i -> i %2 != 0).forEach(i -> System.out.print(i + " "));
		int Sum4Odd = liste.stream().filter(i -> i%2 != 0).mapToInt(Integer :: intValue).sum();
		System.out.println("\nTek Sayýlarýn toplamý : " + Sum4Odd);
				
		// çift sayý adedi ve toplamý 
		System.out.println("-------------------------------------------------");
		int Count4Even = (int) liste.stream().filter(i -> i%2 == 0 ).count();
		System.out.println("Çift Sayý Adedi: " + Count4Even);
		System.out.print("Çift Sayýlar: ");
		liste.stream().filter(i -> i%2 == 0).forEach(i -> System.out.print(i + " "));
		int Sum4Even = liste.stream().filter(i -> i%2 == 0).mapToInt(Integer :: intValue).sum();
		System.out.println("\nÇift Sayýlarýn toplamý: " + Sum4Even);
		
		// 3 ile bölünebilen sayýlar tersten yazdýrýlmasý
		System.out.println("-------------------------------------------------");
		System.out.print("3 ile bölünebilen sayýlarýn tersten sýralanmasý: ");
		liste.stream().filter(i -> i%3 == 0).sorted(Comparator.reverseOrder()).forEach(i -> System.out.print(i + " "));;
		
		// Denediðim diðer örnekler
		// 5 ile bölünebilen sayýlar 
		System.out.println("\n-------------------------------------------------");
		System.out.print("5 ile bölünebilen sayýlar: ");
		liste.stream().filter(i -> i%5 == 0).forEach(i->System.out.print(i + " "));
		System.out.println("\n5 ile bölünebilen sayý adedi: " + liste.stream().filter(i -> i%5 == 0 ).count());
		System.out.println("5 ile bölünenebilen sayýlarýn toplamý: " + liste.stream().filter(i -> i%5 == 0 ).mapToInt(Integer::intValue).sum());
		System.out.println("5 ile bölünenebilen sayýlarýn ortalamasý: " + liste.stream().filter(i -> i%5 == 0 ).mapToInt(Integer :: intValue).average().getAsDouble());
		System.out.print("5 ve 2 ile bölünen sayýlar: ");
		liste.stream().filter(i -> i%5 == 0 ).filter(n -> n%2 == 0).forEach(i -> System.out.print(i + " "));
		 
		// Karelerin toplamý
		System.out.println("\n-------------------------------------------------");
		int SqrtSum = liste.stream().map(n -> n*n).reduce(0, (a,b) -> a+b);
		System.out.println("Sayýlarýn karelerinin toplamý: "+SqrtSum);
		// 7 ile bölünebilen sayýlarýn toplamý
		System.out.println("7 ile bölünen sayýlarýn karesinin toplamý: "+ liste.stream().filter(i -> i%7 == 0).map(n -> n*n).reduce(0, (a,b) -> a+b));
		System.out.println("7 ile bölünen ve 50'den büyük sayýlarýn toplamý: "+ liste.stream().filter(i -> i%7 == 0).filter(i -> i >= 50).mapToInt(Integer :: intValue).sum());

	}

}
