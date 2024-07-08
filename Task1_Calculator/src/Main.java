import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	// Scanner nesnesi oluþturulmasý
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// Hesap Makinesi
		AnaMenu();
	}

	public static void AnaMenu() {
		// exit 0 olduðu sürece Do-While döngüsü çalýþacaktýr
		// case : 9 olarak tanýmlý duruma girdiðinde exit deðeri 1 olarak deðiþtirilir
		// ve dögüden çýkýlýr.

		// loop'a default olarak girmesi için sýfýr tanýmlý
		int exit = 0;

		do {
			// Kullanýcýnýn göreceði menu
			System.out.println("1) Toplama iþlemi");
			System.out.println("2) Çýkarma iþlemi");
			System.out.println("3) Çarpma iþlemi");
			System.out.println("4) Bölme iþlemi");
			System.out.println("5) Karakök iþlemi");
			System.out.println("6) Üsünü iþlemi");
			System.out.println("9) Çýkýþ");
			System.out.print("Yapmak istediðiniz iþlemi seçiniz : ");

			try {
				int Key = scanner.nextInt();

				// switch case yapýsý kullanarak iþlemlere yönlendirebiliriz
				switch (Key) {
				case 1:
					// Toplama fonsiyonu baþlatmak için
					Toplama();
					break;
				case 2:
					// Çýkarma fonksiyonu
					Cikarma();
					break;
				case 3:
					// Çarpma fonksiyonu
					Carpma();
					break;
				case 4:
					// Bölme fonksiyonu
					Bolme();
					break;
				case 5:
					// Karakök fonksiyonu
					Karakok();
					break;
				case 6:
					// Üs fonksiyonu
					Us();
					break;
				case 9:
					exit = 1;
					break;
				default:
					System.out.println("Geçersiz iþlem. Lütfen tekrar deneyin.");
					exit = 0; // Yanlýþ giriþ durumunda tekrar döngüye gir
				}
			} catch (InputMismatchException e) {
				// integer harici bir deðer girilmemesi için kontrol edilen hata yakalam bloðu
				System.out.println("Geçersiz giriþ. Lütfen bir sayý girin.");
				scanner.next(); // Hatalý girdiyi temizle
				exit = 0; // Yanlýþ giriþ durumunda tekrar döngüye gir
			}
		} while (exit == 0);

		System.out.println("Hesap makinesi kapandý");
		scanner.close();
	}

	// Çaðýralacak Fonksiyonlarýn tanýmlanmasý
	// -----------------------------------------
	public static void Toplama() {
		int sum = 0;
		int sayi;
		int Count;
		try {
			System.out.println("Kaç adet sayý ile iþlem yapacaksýnýz?");
			Count = scanner.nextInt();

			// For döngüsü kullanarak sayýlarýn toplanmasý
			for (int i = 1; i <= Count; i++) {
				System.out.print(i + ". Sayýyý giriniz: ");
				sayi = scanner.nextInt();
				sum += sayi;
			}

			// Sonucu yazdýrýlmasý
			System.out.println("\nÝþleminizin sonucu: " + sum + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Geçersiz giriþ. Lütfen bir sayý girin.");
			scanner.next(); // Hatalý girdiyi temizle
		}

	}

	public static void Cikarma() {
		int fark = 0;
		try {
			System.out.print("Ýlk sayýyý giriniz: ");
			int sayi1 = scanner.nextInt();

			System.out.print("Ýkinci sayýyý giriniz: ");
			int sayi2 = scanner.nextInt();

			fark = sayi1 - sayi2;

			System.out.println("\nÝþleminizin sonucu: " + fark + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Geçersiz giriþ. Lütfen bir sayý girin.");
			scanner.next(); // Hatalý girdiyi temizle
		}
	}

	public static void Carpma() {
		int multi = 1;
		int sayi;
		int Count;
		try {
			System.out.println("Kaç adet sayý ile iþlem yapacaksýnýz?");
			Count = scanner.nextInt();

			// For döngüsü kullanarak sayýlarýn toplanmasý
			for (int i = 1; i <= Count; i++) {
				System.out.print(i + ". Sayýyý giriniz: ");
				sayi = scanner.nextInt();
				multi *= sayi;
			}

			// Sonucu yazdýrýlmasý
			System.out.println("\nÝþleminizin sonucu: " + multi + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Geçersiz giriþ. Lütfen bir sayý girin.");
			scanner.next(); // Hatalý girdiyi temizle
		}
	}

	public static void Bolme() {
		try {
			System.out.print("Bölme iþlemi için ilk sayýyý giriniz: ");
			double sayi1 = scanner.nextDouble();

			System.out.print("Bölme iþlemi için ikinci sayýyý giriniz: ");
			double sayi2 = scanner.nextDouble();

			if (sayi2 == 0) {
				System.out.println("Sýfýra bölme hatasý! Ýkinci sayý sýfýr olamaz.");
			} else {
				double bolum = sayi1 / sayi2;
				System.out.println("\nÝþleminizin sonucu: " + bolum + "\n");
			}
		} catch (InputMismatchException e) {
			System.out.println("Geçersiz giriþ. Lütfen bir sayý girin.");
			scanner.next(); // Hatalý girdiyi temizle
		}
	}

	public static void Karakok() {
		try {
			System.out.print("Karakök iþlemi için bir sayý giriniz: ");
			double sayi = scanner.nextDouble();

			if (sayi < 0) {
				System.out.println("Negatif sayýlarýn karakökü alýnamaz.");
			} else {
				double kok = Math.sqrt(sayi);
				System.out.println("\nÝþleminizin sonucu: " + kok + "\n");
			}
		} catch (InputMismatchException e) {
			System.out.println("Geçersiz giriþ. Lütfen bir sayý girin.");
			scanner.next(); // Hatalý girdiyi temizle
		}
	}

	public static void Us() {
		try {
			System.out.print("Üs iþlemi için taban sayýyý giriniz: ");
			double taban = scanner.nextDouble();

			System.out.print("Üs iþlemi için üs sayýyý giriniz: ");
			double us = scanner.nextDouble();

			double sonuc = Math.pow(taban, us);
			System.out.println("\nÝþleminizin sonucu: " + sonuc + "\n");
		} catch (InputMismatchException e) {
			System.out.println("Geçersiz giriþ. Lütfen bir sayý girin.");
			scanner.next(); // Hatalý girdiyi temizle
		}
	}
}
