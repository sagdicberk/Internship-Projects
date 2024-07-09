import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Rasgele sayý belirlemek için Random Nesnesi yaratýyoruz
		Random R = new Random();

		// Girilen sayýlarý almak için bir Scanner yaratýyoruz.
		Scanner sc = new Scanner(System.in);

		// 1 ile 100 arasýnda rasgele bir deðer üretip
		// bu deðeri bir deðiþkene atayalým.
		int rast = R.nextInt(100);

		// Süre tutmak için sistem zamanýna ihtiyacýmýz var.
		long StartTime = System.currentTimeMillis();

		// Sistem zamanýna Eklediðimiz süreyi sayaç olarak kullanýcaz
		long FinishTime = StartTime + 30000; // 30000 ms = 30 Saniye

		// Deneme sayýsý için bir Counter oluþtururyoruz 
		int MaxAttempt = 6;
		
		// Baþlangýç arayüz
		System.out.println("Sayý tahmin oyununa Hoþgeldiniz\nOyun (1-100) Deðerleri arasýnda sýnýrlýdýr");
		System.out.println("-------------------------------");
		
		// while Döngüsü Oyunu baþlatmak için
		while (true) {

			// Kalan sürenin gösterilmesi
			long RemainingTime = (FinishTime - System.currentTimeMillis()) / 1000;

			// RemainingTime ve MaxAttempt Kontrol
			if (RemainingTime <= 0 || MaxAttempt <= 0) { // RemainingTime ve MaxAttempt sýfýra geldiðinde Oyun
															// sonlanacaktýr.
				System.out.println("Kaybettiniz!! bulmanýz gereken sayý " + rast + " idi");
				break;
			}

			// Kullanýcý arayüzü
			System.out.println(RemainingTime + " Saniye kaldý " + MaxAttempt + " Kez deneyebilirsiniz");
			System.out.print("Tahmin Girin: ");
			
			// Ýnteger tipinde veri girilmezse hata alýp sonlanmasýný engellemek için
			try {
				int guess = sc.nextInt(); 
				
				// tahminden sonra MaxAttempt deðerini azaltma
				MaxAttempt--;

				// tahminden fazla ve az olduðunu tespit etme
				if (guess == rast) {
					System.out.println("Tebrikler!! Kazandýnýz " + rast + " sayýsýný doðru tahmin ettiniz. :D");
					break;
				} else if (guess > rast) {
					System.out.println("Çok Yüksek");
				} else {
					System.out.println("Çok Düþük");
				}

			} catch (Exception e) {
				System.out.println("Lütfen bir sayý girin");
				sc.next();
			}

		}

		sc.close();
	}
}
