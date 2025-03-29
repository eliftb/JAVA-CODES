import java.util.Scanner;

class Kitap {
    String baslik;
    String yazar;
    Kitap sonraki;
    Kitap onceki;

    public Kitap(String baslik, String yazar) {
        this.baslik = baslik;
        this.yazar = yazar;
    }
}

class CiftYonluListe {
    private Kitap bas, son; 
    private int boyut; 

    public void basaEkle(String baslik, String yazar) {
        Kitap yeniKitap = new Kitap(baslik, yazar);
        if (bas == null) {
            son = yeniKitap;  
            bas = yeniKitap;
        } else {
            yeniKitap.sonraki = bas;
            bas.onceki = yeniKitap; 
            bas = yeniKitap;
        }
        boyut++; 
    }

    public void sonaEkle(String baslik, String yazar) 
    {
        Kitap yeniKitap = new Kitap(baslik, yazar);
        if (son == null) {
            bas = son = yeniKitap;
        } else {
            son.sonraki = yeniKitap;
            yeniKitap.onceki = son; 
            son = yeniKitap;
        }
        boyut++; 
    }

    public void ortayaEkle(String baslik, String yazar) {
        if (boyut < 2) {
            sonaEkle(baslik, yazar); 
            return;
        }

        Kitap yeniKitap = new Kitap(baslik, yazar);
        Kitap suanki = bas;
        int adim = (boyut - 1) / 2; 

        for (int i = 0; i < adim; i++) {
            suanki = suanki.sonraki;
        }

        yeniKitap.sonraki = suanki.sonraki;
        yeniKitap.onceki = suanki;
        if (suanki.sonraki != null)
         {
            suanki.sonraki.onceki = yeniKitap; 
        }
        suanki.sonraki = yeniKitap;

        if (yeniKitap.sonraki == null) {
            son = yeniKitap; 
        }
        boyut++;
    }

    public void basligaGoreSil(String baslik) {
        if (bas == null) {
            System.out.println("Liste boş!");
            return;
        }

        Kitap suanki = bas;
        while (suanki != null) {
            if (suanki.baslik.equalsIgnoreCase(baslik)) {
                if (suanki == bas) {
                    bas = suanki.sonraki;
                    if (bas != null) bas.onceki = null; 
                } 
                else if (suanki == son) {
    son = suanki.onceki;
     son.sonraki = null; 
                } 
                else {
                    suanki.onceki.sonraki = suanki.sonraki;
                    suanki.sonraki.onceki = suanki.onceki;
                }
                boyut--;
                return;
            }
            suanki = suanki.sonraki;
        }
        System.out.println("'" + baslik + "' diye bir kitap yok!");  
    }

    public void ortadanSil()
{
        if (bas == null) {
            System.out.println("Silinecek ne var ki!");
            return;
        }

        Kitap suanki = bas;
        int adim = boyut / 2;

        for (int i = 0; i < adim; i++) 
        {
            suanki = suanki.sonraki;
        }

        if (suanki.onceki != null) 
        {
            suanki.onceki.sonraki = suanki.sonraki;
        } else 
        {
            bas = suanki.sonraki;
        }

        if (suanki.sonraki != null) {
            suanki.sonraki.onceki = suanki.onceki;
        } else  
        {
            son = suanki.onceki;
        }
        boyut--;
    }

    public void listele() {
        Kitap suanki = bas;
        System.out.println("\n=== KÜTÜPHANE ==="); 
        while (suanki != null) {
            System.out.println("✧ " + suanki.baslik + " - " + suanki.yazar); 
            suanki = suanki.sonraki;
        }
    }

    public void terstenListele() {
        Kitap suanki = son;
        System.out.println("\n=== TERSTEN ===");
        while (suanki != null) {
            System.out.println("> " + suanki.baslik);
            suanki = suanki.onceki;
        }
    }
}

public class Kutuphane {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CiftYonluListe kitaplar = new CiftYonluListe();

        while (true) {
            System.out.println("\nMENÜ ");
            System.out.println("1. Başa ekle");
            System.out.println("2. Sona ekle");
            System.out.println("3. Ortaya ekle");
            System.out.println("4. Kitap sil");
            System.out.println("5. Ortadan sil");
            System.out.println("6. Listele");
            System.out.println("7. Tersten listele");
            System.out.println("8. Çıkış");
            System.out.print("-> Seçiminiz: ");
            scanner.nextLine();
            int secim = scanner.nextInt();
                switch (secim) {
                    case 1: 
                        System.out.print("Başlık: ");
                        String baslik = scanner.nextLine();
                        System.out.print("Yazar: ");
                        kitaplar.basaEkle(baslik, scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Başlık: ");
                        baslik = scanner.nextLine();
                        System.out.print("Yazar: ");
                        kitaplar.sonaEkle(baslik, scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Başlık: ");
                        baslik = scanner.nextLine();
                        System.out.print("Yazar: ");
                        kitaplar.ortayaEkle(baslik, scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("Silinecek kitap: ");
                        kitaplar.basligaGoreSil(scanner.nextLine());
                        break;
                    case 5:
                        kitaplar.ortadanSil();
                        break;
                    case 6:
                        kitaplar.listele();
                        break;
                    case 7:
                        kitaplar.terstenListele();
                        break;
                    case 8:
                        System.out.println("Görüşürüz!");  
                          scanner.close();
                        return;
                    default:
                        System.out.println("Yanlış seçim!");  
                }
        }
    }
}
