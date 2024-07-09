import java.util.*;

public class Main {

	public static void main(String[] args) {
		// Rasgele say� belirlemek i�in Random Nesnesi yarat�yoruz
		Random R = new Random();

		// Girilen say�lar� almak i�in bir Scanner yarat�yoruz.
		Scanner sc = new Scanner(System.in);

		// 1 ile 100 aras�nda rasgele bir de�er �retip
		// bu de�eri bir de�i�kene atayal�m.
		int rast = R.nextInt(100);

		// S�re tutmak i�in sistem zaman�na ihtiyac�m�z var.
		long StartTime = System.currentTimeMillis();

		// Sistem zaman�na Ekledi�imiz s�reyi saya� olarak kullan�caz
		long FinishTime = StartTime + 30000; // 30000 ms = 30 Saniye

		// Deneme say�s� i�in bir Counter olu�tururyoruz 
		int MaxAttempt = 6;
		
		// Ba�lang�� aray�z
		System.out.println("Say� tahmin oyununa Ho�geldiniz\nOyun (1-100) De�erleri aras�nda s�n�rl�d�r");
		System.out.println("-------------------------------");
		
		// while D�ng�s� Oyunu ba�latmak i�in
		while (true) {

			// Kalan s�renin g�sterilmesi
			long RemainingTime = (FinishTime - System.currentTimeMillis()) / 1000;

			// RemainingTime ve MaxAttempt Kontrol
			if (RemainingTime <= 0 || MaxAttempt <= 0) { // RemainingTime ve MaxAttempt s�f�ra geldi�inde Oyun
															// sonlanacakt�r.
				System.out.println("Kaybettiniz!! bulman�z gereken say� " + rast + " idi");
				break;
			}

			// Kullan�c� aray�z�
			System.out.println(RemainingTime + " Saniye kald� " + MaxAttempt + " Kez deneyebilirsiniz");
			System.out.print("Tahmin Girin: ");
			
			// �nteger tipinde veri girilmezse hata al�p sonlanmas�n� engellemek i�in
			try {
				int guess = sc.nextInt(); 
				
				// tahminden sonra MaxAttempt de�erini azaltma
				MaxAttempt--;

				// tahminden fazla ve az oldu�unu tespit etme
				if (guess == rast) {
					System.out.println("Tebrikler!! Kazand�n�z " + rast + " say�s�n� do�ru tahmin ettiniz. :D");
					break;
				} else if (guess > rast) {
					System.out.println("�ok Y�ksek");
				} else {
					System.out.println("�ok D���k");
				}

			} catch (Exception e) {
				System.out.println("L�tfen bir say� girin");
				sc.next();
			}

		}

		sc.close();
	}
}
