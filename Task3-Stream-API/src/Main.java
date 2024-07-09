
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Bir LinkedList OLu�turuyoruz.  
		LinkedList<Integer> liste = new LinkedList<Integer>();
		
		// 1den 100e kadar say�lar�n listeye al�nmas�.
		for (int i = 1; i <= 100; i++) {
			liste.add(i);
		}
		

		// 4e ve 2ye b�l�nen say� adedi ve say�lar
		System.out.println("-------------------------------------------------");
		System.out.println("4 ve 2 ile b�l�nen say� adedi: " + liste.stream().filter(i -> i%4 == 0 ).count());
		System.out.print("Hem 4 Hem de 2 ile b�l�nen say�lar: ");
		liste.stream().filter(i -> i%4 == 0).forEach(i -> System.out.print(i + " "));
		
		// tek say� adedi ve toplamlar�
		System.out.println("\n-------------------------------------------------");
		int Count4Odd = (int) liste.stream().filter(i -> i%2 != 0 ).count();
		System.out.println("Tek Say� Adedi: " + Count4Odd );
		System.out.print("Tek Say�lar: ");
		liste.stream().filter(i -> i %2 != 0).forEach(i -> System.out.print(i + " "));
		int Sum4Odd = liste.stream().filter(i -> i%2 != 0).mapToInt(Integer :: intValue).sum();
		System.out.println("\nTek Say�lar�n toplam� : " + Sum4Odd);
				
		// �ift say� adedi ve toplam� 
		System.out.println("-------------------------------------------------");
		int Count4Even = (int) liste.stream().filter(i -> i%2 == 0 ).count();
		System.out.println("�ift Say� Adedi: " + Count4Even);
		System.out.print("�ift Say�lar: ");
		liste.stream().filter(i -> i%2 == 0).forEach(i -> System.out.print(i + " "));
		int Sum4Even = liste.stream().filter(i -> i%2 == 0).mapToInt(Integer :: intValue).sum();
		System.out.println("\n�ift Say�lar�n toplam�: " + Sum4Even);
		
		// 3 ile b�l�nebilen say�lar tersten yazd�r�lmas�
		System.out.println("-------------------------------------------------");
		System.out.print("3 ile b�l�nebilen say�lar�n tersten s�ralanmas�: ");
		liste.stream().filter(i -> i%3 == 0).sorted(Comparator.reverseOrder()).forEach(i -> System.out.print(i + " "));;
		
		// Denedi�im di�er �rnekler
		// 5 ile b�l�nebilen say�lar 
		System.out.println("\n-------------------------------------------------");
		System.out.print("5 ile b�l�nebilen say�lar: ");
		liste.stream().filter(i -> i%5 == 0).forEach(i->System.out.print(i + " "));
		System.out.println("\n5 ile b�l�nebilen say� adedi: " + liste.stream().filter(i -> i%5 == 0 ).count());
		System.out.println("5 ile b�l�nenebilen say�lar�n toplam�: " + liste.stream().filter(i -> i%5 == 0 ).mapToInt(Integer::intValue).sum());
		System.out.println("5 ile b�l�nenebilen say�lar�n ortalamas�: " + liste.stream().filter(i -> i%5 == 0 ).mapToInt(Integer :: intValue).average().getAsDouble());
		System.out.print("5 ve 2 ile b�l�nen say�lar: ");
		liste.stream().filter(i -> i%5 == 0 ).filter(n -> n%2 == 0).forEach(i -> System.out.print(i + " "));
		 
		// Karelerin toplam�
		System.out.println("\n-------------------------------------------------");
		int SqrtSum = liste.stream().map(n -> n*n).reduce(0, (a,b) -> a+b);
		System.out.println("Say�lar�n karelerinin toplam�: "+SqrtSum);
		// 7 ile b�l�nebilen say�lar�n toplam�
		System.out.println("7 ile b�l�nen say�lar�n karesinin toplam�: "+ liste.stream().filter(i -> i%7 == 0).map(n -> n*n).reduce(0, (a,b) -> a+b));
		System.out.println("7 ile b�l�nen ve 50'den b�y�k say�lar�n toplam�: "+ liste.stream().filter(i -> i%7 == 0).filter(i -> i >= 50).mapToInt(Integer :: intValue).sum());

	}

}
