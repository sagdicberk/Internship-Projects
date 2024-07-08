import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	// Scanner nesnesi olu�turulmas�
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// Hesap Makinesi
		AnaMenu();
	}

	public static void AnaMenu() {
		// exit 0 oldu�u s�rece Do-While d�ng�s� �al��acakt�r
		// case : 9 olarak tan�ml� duruma girdi�inde exit de�eri 1 olarak de�i�tirilir
		// ve d�g�den ��k�l�r.

		// loop'a default olarak girmesi i�in s�f�r tan�ml�
		int exit = 0;

		do {
			// Kullan�c�n�n g�rece�i menu
			System.out.println("1) Toplama i�lemi");
			System.out.println("2) ��karma i�lemi");
			System.out.println("3) �arpma i�lemi");
			System.out.println("4) B�lme i�lemi");
			System.out.println("5) Karak�k i�lemi");
			System.out.println("6) �s�n� i�lemi");
			System.out.println("9) ��k��");
			System.out.print("Yapmak istedi�iniz i�lemi se�iniz : ");

			try {
				int Key = scanner.nextInt();

				// switch case yap�s� kullanarak i�lemlere y�nlendirebiliriz
				switch (Key) {
				case 1:
					// Toplama fonsiyonu ba�latmak i�in
					Toplama();
					break;
				case 2:
					// ��karma fonksiyonu
					Cikarma();
					break;
				case 3:
					// �arpma fonksiyonu
					Carpma();
					break;
				case 4:
					// B�lme fonksiyonu
					Bolme();
					break;
				case 5:
					// Karak�k fonksiyonu
					Karakok();
					break;
				case 6:
					// �s fonksiyonu
					Us();
					break;
				case 9:
					exit = 1;
					break;
				default:
					System.out.println("Ge�ersiz i�lem. L�tfen tekrar deneyin.");
					exit = 0; // Yanl�� giri� durumunda tekrar d�ng�ye gir
				}
			} catch (InputMismatchException e) {
				// integer harici bir de�er girilmemesi i�in kontrol edilen hata yakalam blo�u
				System.out.println("Ge�ersiz giri�. L�tfen bir say� girin.");
				scanner.next(); // Hatal� girdiyi temizle
				exit = 0; // Yanl�� giri� durumunda tekrar d�ng�ye gir
			}
		} while (exit == 0);

		System.out.println("Hesap makinesi kapand�");
		scanner.close();
	}

	// �a��ralacak Fonksiyonlar�n tan�mlanmas�
	// -----------------------------------------
	public static void Toplama() {
		int sum = 0;
		int sayi;
		int Count;
		try {
			System.out.println("Ka� adet say� ile i�lem yapacaks�n�z?");
			Count = scanner.nextInt();

			// For d�ng�s� kullanarak say�lar�n toplanmas�
			for (int i = 1; i <= Count; i++) {
				System.out.print(i + ". Say�y� giriniz: ");
				sayi = scanner.nextInt();
				sum += sayi;
			}

			// Sonucu yazd�r�lmas�
			System.out.println("\n��leminizin sonucu: " + sum + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Ge�ersiz giri�. L�tfen bir say� girin.");
			scanner.next(); // Hatal� girdiyi temizle
		}

	}

	public static void Cikarma() {
		int fark = 0;
		try {
			System.out.print("�lk say�y� giriniz: ");
			int sayi1 = scanner.nextInt();

			System.out.print("�kinci say�y� giriniz: ");
			int sayi2 = scanner.nextInt();

			fark = sayi1 - sayi2;

			System.out.println("\n��leminizin sonucu: " + fark + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Ge�ersiz giri�. L�tfen bir say� girin.");
			scanner.next(); // Hatal� girdiyi temizle
		}
	}

	public static void Carpma() {
		int multi = 1;
		int sayi;
		int Count;
		try {
			System.out.println("Ka� adet say� ile i�lem yapacaks�n�z?");
			Count = scanner.nextInt();

			// For d�ng�s� kullanarak say�lar�n toplanmas�
			for (int i = 1; i <= Count; i++) {
				System.out.print(i + ". Say�y� giriniz: ");
				sayi = scanner.nextInt();
				multi *= sayi;
			}

			// Sonucu yazd�r�lmas�
			System.out.println("\n��leminizin sonucu: " + multi + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Ge�ersiz giri�. L�tfen bir say� girin.");
			scanner.next(); // Hatal� girdiyi temizle
		}
	}

	public static void Bolme() {
		try {
			System.out.print("B�lme i�lemi i�in ilk say�y� giriniz: ");
			double sayi1 = scanner.nextDouble();

			System.out.print("B�lme i�lemi i�in ikinci say�y� giriniz: ");
			double sayi2 = scanner.nextDouble();

			if (sayi2 == 0) {
				System.out.println("S�f�ra b�lme hatas�! �kinci say� s�f�r olamaz.");
			} else {
				double bolum = sayi1 / sayi2;
				System.out.println("\n��leminizin sonucu: " + bolum + "\n");
			}
		} catch (InputMismatchException e) {
			System.out.println("Ge�ersiz giri�. L�tfen bir say� girin.");
			scanner.next(); // Hatal� girdiyi temizle
		}
	}

	public static void Karakok() {
		try {
			System.out.print("Karak�k i�lemi i�in bir say� giriniz: ");
			double sayi = scanner.nextDouble();

			if (sayi < 0) {
				System.out.println("Negatif say�lar�n karak�k� al�namaz.");
			} else {
				double kok = Math.sqrt(sayi);
				System.out.println("\n��leminizin sonucu: " + kok + "\n");
			}
		} catch (InputMismatchException e) {
			System.out.println("Ge�ersiz giri�. L�tfen bir say� girin.");
			scanner.next(); // Hatal� girdiyi temizle
		}
	}

	public static void Us() {
		try {
			System.out.print("�s i�lemi i�in taban say�y� giriniz: ");
			double taban = scanner.nextDouble();

			System.out.print("�s i�lemi i�in �s say�y� giriniz: ");
			double us = scanner.nextDouble();

			double sonuc = Math.pow(taban, us);
			System.out.println("\n��leminizin sonucu: " + sonuc + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Ge�ersiz giri�. L�tfen bir say� girin.");
			scanner.next(); // Hatal� girdiyi temizle
		}
	}
}
