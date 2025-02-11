package com.protesq.protesqpy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageButton;

public class TopicDetailActivity extends AppCompatActivity {
    private TextView contentTextView;
    private TextView titleTextView;
    private Button completeButton;
    private ProgressBar progressBar;
    private SharedPreferences prefs;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_detail);

        ImageButton backButton = findViewById(R.id.backButton);
        titleTextView = findViewById(R.id.titleTextView);
        contentTextView = findViewById(R.id.contentTextView);
        completeButton = findViewById(R.id.completeButton);
        progressBar = findViewById(R.id.progressBar);

        backButton.setOnClickListener(v -> onBackPressed());

        prefs = getSharedPreferences("TopicProgress", MODE_PRIVATE);
        
        String title = getIntent().getStringExtra("title");
        position = getIntent().getIntExtra("position", 0);

        titleTextView.setText(title);
        setTopicContent(position);
        
        completeButton.setOnClickListener(v -> completeTopic());
        
        if (prefs.getBoolean("topic_" + position + "_completed", false)) {
            completeButton.setVisibility(View.GONE);
            progressBar.setProgress(100);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void completeTopic() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("topic_" + position + "_completed", true);
        
        if (position < 13) {
            editor.putBoolean("topic_" + (position + 1) + "_unlocked", true);
        }
        editor.apply();

        completeButton.setVisibility(View.GONE);
        progressBar.setProgress(100);
        
        Intent resultIntent = new Intent();
        resultIntent.putExtra("topicCompleted", true);
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    private void setTopicContent(int position) {
        String content;
        switch (position) {
            case 0: // Python'a Giriş
                content = "Python'a Hoş Geldiniz! 🐍\n\n" +
                         "Python Nedir?\n" +
                         "Python, tıpkı günlük konuşma dili gibi kolay anlaşılır bir programlama dilidir. " +
                         "Nasıl ki WhatsApp kullanmayı kısa sürede öğrenebiliyorsak, Python yazmayı da " +
                         "o kadar kolay öğrenebiliriz.\n\n" +
                         
                         "Neden Python Öğrenmeliyim?\n" +
                         "• Instagram, Spotify, Netflix gibi uygulamalar Python kullanıyor\n" +
                         "• Yapay zeka ve veri analizi için en popüler dil\n" +
                         "• İş bulma şansı çok yüksek\n" +
                         "• Başlangıç için en ideal programlama dili\n\n" +
                         
                         "Python'la Neler Yapabilirim?\n" +
                         "1. Web Siteleri (Instagram gibi)\n" +
                         "2. Oyunlar (Minecraft'ın bazı kısımları Python'la yazıldı)\n" +
                         "3. Robotlar (Drone kontrolü)\n" +
                         "4. Mobil Uygulamalar\n\n" +
                         
                         "İlk Python Programımız:\n" +
                         "print('Merhaba Dünya!')\n" +
                         "# Bu kod ekrana 'Merhaba Dünya!' yazar\n\n" +
                         
                         "Gerçek Hayattan Örnek:\n" +
                         "# Kahve otomatı programı\n" +
                         "kahve_secimi = input('Hangi kahveyi istersiniz? ')\n" +
                         "print(f'Bir {kahve_secimi} hazırlanıyor...')\n\n" +
                         
                         "Python'da Yorum Yazma:\n" +
                         "# Bu bir not satırıdır, kod çalışırken görmezden gelinir\n" +
                         "'''\n" +
                         "Buraya uzun açıklamalar\n" +
                         "yazabilirsiniz\n" +
                         "'''\n\n" +
                         
                         "Önemli İpuçları:\n" +
                         "1. Python büyük/küçük harfe duyarlıdır (Print ≠ print)\n" +
                         "2. Girintiler önemlidir (genelde 4 boşluk kullanılır)\n" +
                         "3. Her satır bir işlem yapar\n\n" +
                         
                         "Pratik Yapabileceğiniz Yerler:\n" +
                         "• replit.com (online Python editörü)\n" +
                         "• pythontutor.com (kodunuzu adım adım görebilirsiniz)\n" +
                         "• codecademy.com (interaktif dersler)\n\n" +
                         
                         "Sıradaki derste değişkenler ve veri tiplerini öğreneceğiz. " +
                         "Değişkenleri cüzdanınızdaki bölmeler gibi düşünebilirsiniz, " +
                         "her bölmede farklı şeyler saklayabilirsiniz!";
                break;

            case 1: // Değişkenler ve Veri Tipleri
                content = "Python'da Değişkenler ve Veri Tipleri\n\n" +
                         "Değişken Tanımlama:\n" +
                         "• Değişken isimleri harf veya _ ile başlamalıdır\n" +
                         "• Büyük/küçük harf duyarlıdır\n" +
                         "• Rakamla başlayamaz\n\n" +
                         
                         "Temel Veri Tipleri:\n" +
                         "1. Sayısal Tipler:\n" +
                         "   int (tam sayı):\n" +
                         "   x = 5\n" +
                         "   y = -10\n\n" +
                         
                         "   float (ondalıklı sayı):\n" +
                         "   pi = 3.14\n" +
                         "   fiyat = 29.99\n\n" +
                         
                         "2. Metin (String):\n" +
                         "   isim = 'Ahmet'\n" +
                         "   mesaj = \"Python Öğreniyorum\"\n" +
                         "   uzun_metin = '''Bu bir\n" +
                         "   çok satırlı\n" +
                         "   metindir'''\n\n" +
                         
                         "3. Boolean:\n" +
                         "   aktif = True\n" +
                         "   pasif = False\n\n" +
                         
                         "Tip Dönüşümleri:\n" +
                         "sayi = int('42')      # string'den int'e\n" +
                         "metin = str(42)       # int'den string'e\n" +
                         "ondalik = float('3.14')  # string'den float'a\n\n" +
                         
                         "Değişken İsimlendirme Örnekleri:\n" +
                         "ogrenci_adi = 'Ali'     # snake_case\n" +
                         "ogrenciSayisi = 25      # camelCase\n" +
                         "MAKSIMUM_PUAN = 100     # sabitler için büyük harf\n\n" +
                         
                         "Çoklu Atama:\n" +
                         "x, y, z = 1, 2, 3\n" +
                         "a = b = c = 0  # aynı değeri birden fazla değişkene atama";
                break;

            case 2: // Operatörler
                content = "Python'da Operatörler\n\n" +
                         "1. Aritmetik Operatörler:\n" +
                         "+ : Toplama\n" +
                         "- : Çıkarma\n" +
                         "* : Çarpma\n" +
                         "/ : Bölme (ondalıklı sonuç)\n" +
                         "// : Tam sayı bölmesi\n" +
                         "% : Mod alma\n" +
                         "** : Üs alma\n\n" +
                         
                         "Örnek:\n" +
                         "x = 10\n" +
                         "y = 3\n" +
                         "print(x + y)   # 13\n" +
                         "print(x / y)   # 3.3333...\n" +
                         "print(x // y)  # 3\n" +
                         "print(x % y)   # 1\n" +
                         "print(x ** 2)  # 100\n\n" +
                         
                         "2. Karşılaştırma Operatörleri:\n" +
                         "== : Eşittir\n" +
                         "!= : Eşit değildir\n" +
                         "> : Büyüktür\n" +
                         "< : Küçüktür\n" +
                         ">= : Büyük eşittir\n" +
                         "<= : Küçük eşittir\n\n" +
                         
                         "3. Mantıksal Operatörler:\n" +
                         "and : Ve\n" +
                         "or : Veya\n" +
                         "not : Değil\n\n" +
                         
                         "Örnek:\n" +
                         "yas = 25\n" +
                         "if yas >= 18 and yas <= 30:\n" +
                         "    print('Genç yetişkin')\n\n" +
                         
                         "4. Atama Operatörleri:\n" +
                         "+= : Topla ve ata\n" +
                         "-= : Çıkar ve ata\n" +
                         "*= : Çarp ve ata\n" +
                         "/= : Böl ve ata\n\n" +
                         
                         "Örnek:\n" +
                         "x = 5\n" +
                         "x += 3  # x = x + 3\n" +
                         "print(x)  # 8";
                break;

            case 3: // Döngüler
                content = "Python'da Döngüler\n\n" +
                         "1. For Döngüsü:\n" +
                         "For döngüsü, bir dizi üzerinde gezinmek için kullanılır.\n\n" +
                         
                         "Örnek:\n" +
                         "for i in range(5):\n" +
                         "    print(i)  # 0'dan 4'e kadar yazdırır\n\n" +
                         
                         "Liste üzerinde döngü:\n" +
                         "meyveler = ['elma', 'armut', 'muz']\n" +
                         "for meyve in meyveler:\n" +
                         "    print(meyve)\n\n" +
                         
                         "2. While Döngüsü:\n" +
                         "While döngüsü, bir koşul doğru olduğu sürece çalışır.\n\n" +
                         "Örnek:\n" +
                         "sayac = 0\n" +
                         "while sayac < 5:\n" +
                         "    print(sayac)\n" +
                         "    sayac += 1\n\n" +
                         
                         "3. Break ve Continue:\n" +
                         "break: Döngüyü sonlandırır\n" +
                         "continue: Döngünün sonraki adımına geçer\n\n" +
                         "Örnek:\n" +
                         "for i in range(10):\n" +
                         "    if i == 5:\n" +
                         "        break\n" +
                         "    print(i)\n\n" +
                         
                         "4. İç İçe Döngüler:\n" +
                         "for i in range(3):\n" +
                         "    for j in range(3):\n" +
                         "        print(f'i:{i} j:{j}')\n\n" +
                         
                         "5. Pratik Örnekler:\n" +
                         "# Çarpım tablosu\n" +
                         "for i in range(1, 11):\n" +
                         "    for j in range(1, 11):\n" +
                         "        print(f'{i}x{j}={i*j}')\n\n" +
                         
                         "# Yıldızlarla üçgen\n" +
                         "for i in range(5):\n" +
                         "    print('*' * (i+1))\n\n" +
                         
                         "İpuçları:\n" +
                         "• range(başlangıç, bitiş, adım) şeklinde kullanılabilir\n" +
                         "• enumerate() ile index ve değer birlikte alınabilir\n" +
                         "• zip() ile birden fazla liste üzerinde döngü yapılabilir";
                break;

            case 4: // Koşullu İfadeler
                content = "Python'da Koşullu İfadeler\n\n" +
                         "1. if Yapısı:\n" +
                         "Temel kullanım:\n" +
                         "if kosul:\n" +
                         "    # koşul doğruysa çalışacak kod\n\n" +
                         
                         "Örnek:\n" +
                         "yas = 18\n" +
                         "if yas >= 18:\n" +
                         "    print('Ehliyet alabilirsiniz')\n\n" +
                         
                         "2. if-else Yapısı:\n" +
                         "if kosul:\n" +
                         "    # koşul doğruysa\n" +
                         "else:\n" +
                         "    # koşul yanlışsa\n\n" +
                         
                         "Örnek:\n" +
                         "not_degeri = 65\n" +
                         "if not_degeri >= 50:\n" +
                         "    print('Geçtiniz')\n" +
                         "else:\n" +
                         "    print('Kaldınız')\n\n" +
                         
                         "3. if-elif-else Yapısı:\n" +
                         "if kosul1:\n" +
                         "    # kosul1 doğruysa\n" +
                         "elif kosul2:\n" +
                         "    # kosul2 doğruysa\n" +
                         "else:\n" +
                         "    # hiçbiri doğru değilse\n\n" +
                         
                         "Örnek:\n" +
                         "puan = 85\n" +
                         "if puan >= 90:\n" +
                         "    print('AA')\n" +
                         "elif puan >= 80:\n" +
                         "    print('BA')\n" +
                         "elif puan >= 70:\n" +
                         "    print('BB')\n" +
                         "else:\n" +
                         "    print('FF')\n\n" +
                         
                         "4. İç İçe if Yapıları:\n" +
                         "if kosul1:\n" +
                         "    if kosul2:\n" +
                         "        # her iki koşul da doğruysa\n" +
                         "    else:\n" +
                         "        # sadece kosul1 doğruysa\n\n" +
                         
                         "Örnek:\n" +
                         "kullanici = 'admin'\n" +
                         "sifre = '1234'\n" +
                         "if kullanici == 'admin':\n" +
                         "    if sifre == '1234':\n" +
                         "        print('Giriş başarılı')\n" +
                         "    else:\n" +
                         "        print('Şifre yanlış')\n" +
                         "else:\n" +
                         "    print('Kullanıcı bulunamadı')\n\n" +
                         
                         "5. Mantıksal Operatörlerle Kullanım:\n" +
                         "and: Her iki koşul da doğru olmalı\n" +
                         "or: Koşullardan biri doğru olmalı\n" +
                         "not: Koşulun tersini alır\n\n" +
                         
                         "Örnek:\n" +
                         "yas = 25\n" +
                         "gelir = 5000\n" +
                         "if yas >= 18 and gelir >= 4000:\n" +
                         "    print('Kredi verilebilir')\n\n" +
                         
                         "6. Tek Satırlık if (Ternary Operator):\n" +
                         "sonuc = 'Geçti' if not_degeri >= 50 else 'Kaldı'\n\n" +
                         
                         "7. Karşılaştırma Zincirleri:\n" +
                         "yas = 25\n" +
                         "if 18 <= yas <= 30:\n" +
                         "    print('Genç yetişkin')\n\n" +
                         
                         "8. None Kontrolü:\n" +
                         "deger = None\n" +
                         "if deger is None:\n" +
                         "    print('Değer atanmamış')\n\n" +
                         
                         "9. Liste/String Kontrolü:\n" +
                         "liste = [1, 2, 3]\n" +
                         "if liste:  # liste boş değilse\n" +
                         "    print('Liste dolu')\n\n" +
                         
                         "10. Pratik Örnekler:\n" +
                         "# Hesap makinesi\n" +
                         "sayi1 = 10\n" +
                         "sayi2 = 5\n" +
                         "islem = '+'\n\n" +
                         
                         "if islem == '+':\n" +
                         "    sonuc = sayi1 + sayi2\n" +
                         "elif islem == '-':\n" +
                         "    sonuc = sayi1 - sayi2\n" +
                         "elif islem == '*':\n" +
                         "    sonuc = sayi1 * sayi2\n" +
                         "elif islem == '/':\n" +
                         "    if sayi2 != 0:\n" +
                         "        sonuc = sayi1 / sayi2\n" +
                         "    else:\n" +
                         "        sonuc = 'Sıfıra bölünemez'\n" +
                         "else:\n" +
                         "    sonuc = 'Geçersiz işlem'\n\n" +
                         
                         "İpuçları:\n" +
                         "• Girintilere dikkat edin (genelde 4 boşluk)\n" +
                         "• elif istediğiniz kadar kullanabilirsiniz\n" +
                         "• Karmaşık koşullarda parantez kullanın\n" +
                         "• Boş string, liste ve 0 False olarak değerlendirilir\n" +
                         "• is operatörü == yerine None kontrolünde kullanılır\n\n" +
                         
                         "Yaygın Hatalar:\n" +
                         "• Girintileri karıştırmak\n" +
                         "• = (atama) ile == (eşitlik) karıştırmak\n" +
                         "• elif yerine birden çok if kullanmak\n" +
                         "• Mantıksal operatörleri yanlış kullanmak";
                break;

            case 5: // Listeler
                content = "Python'da Listeler 📝\n\n" +
                         "Liste Nedir?\n" +
                         "Liste, birden fazla şeyi sırayla tutabileceğimiz bir yapıdır. " +
                         "Günlük hayattan örnekler:\n" +
                         "• Alışveriş listesi\n" +
                         "• Sınıf yoklama listesi\n" +
                         "• Telefondaki kişiler listesi\n\n" +
                         
                         "Liste Nasıl Oluşturulur?\n" +
                         "# Boş liste oluşturma\n" +
                         "alisveris_listesi = []\n\n" +
                         
                         "# İçi dolu liste oluşturma\n" +
                         "meyveler = ['elma', 'armut', 'muz']\n" +
                         "notlar = [85, 90, 75, 100]\n" +
                         "karisik = ['Ahmet', 25, True, 3.14]  # farklı türde veriler olabilir\n\n" +
                         
                         "Listeye Eleman Ekleme:\n" +
                         "# append() metodu ile sona ekleme\n" +
                         "alisveris_listesi = ['ekmek', 'süt']\n" +
                         "alisveris_listesi.append('yumurta')\n" +
                         "print(alisveris_listesi)  # ['ekmek', 'süt', 'yumurta']\n\n" +
                         
                         "# insert() ile istediğimiz yere ekleme\n" +
                         "meyveler = ['elma', 'muz']\n" +
                         "meyveler.insert(1, 'portakal')  # 1. sıraya portakal ekle\n" +
                         "print(meyveler)  # ['elma', 'portakal', 'muz']\n\n" +
                         
                         "Listeden Eleman Alma:\n" +
                         "arkadas_listesi = ['Ali', 'Ayşe', 'Mehmet', 'Zeynep']\n" +
                         "print(arkadas_listesi[0])     # İlk arkadaş: Ali\n" +
                         "print(arkadas_listesi[-1])    # Son arkadaş: Zeynep\n" +
                         "print(arkadas_listesi[1:3])   # Ortadaki arkadaşlar: ['Ayşe', 'Mehmet']\n\n" +
                         
                         "Gerçek Hayat Örneği - Sınıf Yoklaması:\n" +
                         "# Sınıf listesi ve yoklama durumu\n" +
                         "sinif = ['Ali', 'Ayşe', 'Mehmet']\n" +
                         "gelenler = []\n\n" +
                         
                         "# Yoklama alma\n" +
                         "for ogrenci in sinif:\n" +
                         "    geldi_mi = input(f'{ogrenci} burada mı? (e/h): ')\n" +
                         "    if geldi_mi == 'e':\n" +
                         "        gelenler.append(ogrenci)\n\n" +
                         
                         "# Yoklama sonucu\n" +
                         "print(f'Toplam {len(gelenler)} öğrenci geldi')\n\n" +
                         
                         "Listede Değişiklik Yapma:\n" +
                         "# Liste elemanını değiştirme\n" +
                         "notlar = [85, 70, 90]\n" +
                         "notlar[1] = 75  # 70 yerine 75 yaz\n\n" +
                         
                         "# Listeden eleman silme\n" +
                         "meyveler = ['elma', 'armut', 'muz']\n" +
                         "meyveler.remove('armut')  # armut'u sil\n" +
                         "print(meyveler)  # ['elma', 'muz']\n\n" +
                         
                         "# Son elemanı silme\n" +
                         "son_meyve = meyveler.pop()\n" +
                         "print(son_meyve)  # muz\n\n" +
                         
                         "Faydalı Liste İşlemleri:\n" +
                         "sayilar = [5, 2, 8, 1, 9]\n" +
                         "sayilar.sort()           # Küçükten büyüğe sırala\n" +
                         "print(min(sayilar))      # En küçük sayı: 1\n" +
                         "print(max(sayilar))      # En büyük sayı: 9\n" +
                         "print(len(sayilar))      # Liste uzunluğu: 5\n" +
                         "print(sum(sayilar))      # Toplam: 25\n\n" +
                         
                         "Pratik Yapabileceğiniz Örnek:\n" +
                         "# To-Do List uygulaması\n" +
                         "yapilacaklar = []\n\n" +
                         
                         "# Yeni görev ekleme\n" +
                         "yeni_gorev = 'Ödev yap'\n" +
                         "yapilacaklar.append(yeni_gorev)\n\n" +
                         
                         "# Görevi tamamlama\n" +
                         "tamamlanan = yapilacaklar.pop(0)\n" +
                         "print(f'Tamamlanan görev: {tamamlanan}')\n\n" +
                         
                         "İpuçları:\n" +
                         "• Liste indeksleri 0'dan başlar\n" +
                         "• Listeler değiştirilebilir (mutable)\n" +
                         "• Listeleri iç içe kullanabilirsiniz\n" +
                         "• Liste metodlarını görmek için dir(list) kullanın\n\n" +
                         
                         "Alıştırma:\n" +
                         "1. Kendi alışveriş listenizi oluşturun\n" +
                         "2. Listeye yeni ürünler ekleyin\n" +
                         "3. Aldığınız ürünleri listeden silin\n" +
                         "4. Kaç ürün kaldığını gösterin";
                break;

            case 6: // Diziler
                content = "Python'da Diziler (Arrays)\n\n" +
                         "NumPy kütüphanesi ile diziler oluşturulur.\n\n" +
                         
                         "1. Dizi Oluşturma:\n" +
                         "import numpy as np\n\n" +
                         "# Basit dizi\n" +
                         "arr = np.array([1, 2, 3, 4, 5])\n\n" +
                         "# Sıfırlardan oluşan dizi\n" +
                         "zeros = np.zeros(5)\n\n" +
                         "# Birlerden oluşan dizi\n" +
                         "ones = np.ones(5)\n\n" +
                         
                         "2. Çok Boyutlu Diziler:\n" +
                         "# 2x3 matris\n" +
                         "matrix = np.array([[1, 2, 3],\n" +
                         "                   [4, 5, 6]])\n\n" +
                         
                         "3. Dizi İşlemleri:\n" +
                         "# Toplama\n" +
                         "arr + 5  # Her elemana 5 ekler\n\n" +
                         "# Çarpma\n" +
                         "arr * 2  # Her elemanı 2 ile çarpar\n\n" +
                         "# Karşılaştırma\n" +
                         "arr > 3  # 3'ten büyük elemanları bulur\n\n" +
                         
                         "4. Dizi Dilimleme:\n" +
                         "arr[1:4]  # 1. indeksten 4. indekse kadar\n" +
                         "arr[::2]  # İkişer atlayarak\n\n" +
                         
                         "5. Dizi Metodları:\n" +
                         "arr.sum()  # Toplam\n" +
                         "arr.mean()  # Ortalama\n" +
                         "arr.max()  # En büyük değer\n" +
                         "arr.min()  # En küçük değer\n\n" +
                         
                         "6. Şekil Değiştirme:\n" +
                         "arr.reshape(2, 3)  # 2x3 matrise dönüştürür\n\n" +
                         
                         "7. Pratik Örnekler:\n" +
                         "# Rastgele sayılardan oluşan dizi\n" +
                         "random_arr = np.random.rand(5)\n\n" +
                         "# Belirli aralıkta sayılar\n" +
                         "range_arr = np.arange(0, 10, 2)\n\n" +
                         
                         "İpuçları:\n" +
                         "• NumPy dizileri liste ve matrislerden daha hızlıdır\n" +
                         "• Bilimsel hesaplamalar için idealdir\n" +
                         "• Veri analizi için pandas ile birlikte kullanılır";
                break;

            case 7: // Demetler (Tuples)
                content = "Python'da Demetler (Tuples)\n\n" +
                         "Demet Oluşturma:\n" +
                         "demet = ()  # boş demet\n" +
                         "sayilar = (1, 2, 3)\n" +
                         "tek_elemanli = (1,)  # virgül önemli!\n\n" +
                         
                         "Demet Özellikleri:\n" +
                         "1. Değiştirilemezlik:\n" +
                         "• Elemanlar değiştirilemez\n" +
                         "• Eleman eklenemez/silinemez\n" +
                         "• Performans avantajı sağlar\n\n" +
                         
                         "2. Elemanlara Erişim:\n" +
                         "demet[0]    # ilk eleman\n" +
                         "demet[-1]   # son eleman\n" +
                         "demet[1:3]  # dilimleme\n\n" +
                         
                         "3. Demet Metodları:\n" +
                         "count(): Eleman sayısını verir\n" +
                         "index(): Elemanın indeksini verir\n\n" +
                         
                         "Örnek:\n" +
                         "koordinat = (40.7128, -74.0060)\n" +
                         "x, y = koordinat  # çoklu atama\n" +
                         "print(f'X: {x}, Y: {y}')\n\n" +
                         
                         "4. Demet Kullanım Alanları:\n" +
                         "• Değişmez veri grupları\n" +
                         "• Fonksiyon dönüş değerleri\n" +
                         "• Sözlük anahtarları\n\n" +
                         
                         "5. Liste ve Demet Karşılaştırması:\n" +
                         "• Demetler daha az bellek kullanır\n" +
                         "• Demetler daha hızlı işlenir\n" +
                         "• Listeler esnektir ama demetler güvenlidir";
                break;

            case 8: // Kümeler (Sets)
                content = "Python'da Kümeler (Sets)\n\n" +
                         "Küme Oluşturma:\n" +
                         "kume = set()  # boş küme\n" +
                         "sayilar = {1, 2, 3, 4, 5}\n" +
                         "meyveler = {'elma', 'armut', 'muz'}\n\n" +
                         
                         "Küme Özellikleri:\n" +
                         "1. Benzersizlik:\n" +
                         "• Her eleman benzersizdir\n" +
                         "• Tekrar eden elemanlar otomatik silinir\n\n" +
                         
                         "2. Sırasızlık:\n" +
                         "• Elemanlar sıralı değildir\n" +
                         "• İndeks ile erişilemez\n\n" +
                         
                         "3. Küme İşlemleri:\n" +
                         "union(): Birleşim\n" +
                         "intersection(): Kesişim\n" +
                         "difference(): Fark\n" +
                         "symmetric_difference(): Simetrik fark\n\n" +
                         
                         "Örnek:\n" +
                         "A = {1, 2, 3, 4}\n" +
                         "B = {3, 4, 5, 6}\n" +
                         "print(A | B)  # Birleşim\n" +
                         "print(A & B)  # Kesişim\n" +
                         "print(A - B)  # Fark\n\n" +
                         
                         "4. Küme Metodları:\n" +
                         "add(): Eleman ekler\n" +
                         "remove(): Eleman siler\n" +
                         "discard(): Güvenli silme\n" +
                         "clear(): Tüm kümeyi temizler\n\n" +
                         
                         "5. Küme Kontrolleri:\n" +
                         "issubset(): Alt küme kontrolü\n" +
                         "issuperset(): Üst küme kontrolü\n" +
                         "isdisjoint(): Ayrık küme kontrolü";
                break;

            case 9: // Fonksiyonlar
                content = "Python'da Fonksiyonlar\n\n" +
                         "Fonksiyon Tanımlama:\n" +
                         "def fonksiyon_adi(parametre1, parametre2):\n" +
                         "    # fonksiyon gövdesi\n" +
                         "    return sonuc\n\n" +
                         
                         "1. Temel Fonksiyon Yapısı:\n" +
                         "def selamla(isim):\n" +
                         "    return f'Merhaba {isim}!'\n\n" +
                         "print(selamla('Ahmet'))\n\n" +
                         
                         "2. Parametre Türleri:\n" +
                         "• Zorunlu parametreler\n" +
                         "• Varsayılan değerli parametreler\n" +
                         "• Anahtar kelimeli parametreler\n" +
                         "• Değişken sayıda parametreler\n\n" +
                         
                         "Örnek:\n" +
                         "def topla(a, b=0, *args, **kwargs):\n" +
                         "    toplam = a + b\n" +
                         "    for sayi in args:\n" +
                         "        toplam += sayi\n" +
                         "    return toplam\n\n" +
                         
                         "3. Lambda Fonksiyonları:\n" +
                         "kare = lambda x: x**2\n" +
                         "print(kare(5))  # 25\n\n" +
                         
                         "4. Fonksiyon Dokümantasyonu:\n" +
                         "def ustel(taban, us):\n" +
                         "    '''Bu fonksiyon üs alma işlemi yapar'''\n" +
                         "    return taban ** us\n\n" +
                         
                         "5. Global ve Yerel Değişkenler:\n" +
                         "x = 10  # global\n" +
                         "def fonksiyon():\n" +
                         "    global x\n" +
                         "    x = 20  # global x'i değiştirir\n\n" +
                         
                         "6. Recursive (Özyinelemeli) Fonksiyonlar:\n" +
                         "def faktoriyel(n):\n" +
                         "    if n <= 1:\n" +
                         "        return 1\n" +
                         "    return n * faktoriyel(n-1)";
                break;

            case 10: // Modüller
                content = "Python'da Modüller\n\n" +
                         "Modül Kullanımı:\n" +
                         "import modul_adi\n" +
                         "from modul_adi import fonksiyon\n\n" +
                         
                         "1. Standart Modüller:\n" +
                         "import math\n" +
                         "print(math.pi)  # 3.141592...\n" +
                         "print(math.sqrt(16))  # 4.0\n\n" +
                         
                         "import random\n" +
                         "print(random.randint(1, 10))\n\n" +
                         
                         "2. Modül İsimlendirme:\n" +
                         "import math as m\n" +
                         "print(m.pi)\n\n" +
                         
                         "3. Seçili İçe Aktarma:\n" +
                         "from math import pi, sqrt\n" +
                         "print(sqrt(25))  # 5.0\n\n" +
                         
                         "4. Tüm İçeriği İçe Aktarma:\n" +
                         "from math import *  # önerilmez\n\n" +
                         
                         "5. Önemli Standart Modüller:\n" +
                         "• os: İşletim sistemi işlemleri\n" +
                         "• sys: Sistem özellikleri\n" +
                         "• datetime: Tarih ve zaman işlemleri\n" +
                         "• json: JSON işlemleri\n" +
                         "• re: Regular expressions\n\n" +
                         
                         "6. Kendi Modülünü Oluşturma:\n" +
                         "# hesap.py\n" +
                         "def topla(a, b):\n" +
                         "    return a + b\n\n" +
                         "# main.py\n" +
                         "import hesap\n" +
                         "print(hesap.topla(5, 3))";
                break;

            case 11: // Dosya İşlemleri
                content = "Python'da Dosya İşlemleri\n\n" +
                         "Dosya Açma Modları:\n" +
                         "'r': Okuma modu\n" +
                         "'w': Yazma modu\n" +
                         "'a': Ekleme modu\n" +
                         "'r+': Okuma ve yazma\n\n" +
                         
                         "1. Dosya Okuma:\n" +
                         "with open('dosya.txt', 'r') as f:\n" +
                         "    icerik = f.read()\n" +
                         "    print(icerik)\n\n" +
                         
                         "2. Satır Satır Okuma:\n" +
                         "with open('dosya.txt', 'r') as f:\n" +
                         "    for satir in f:\n" +
                         "        print(satir)\n\n" +
                         
                         "3. Dosyaya Yazma:\n" +
                         "with open('yeni.txt', 'w') as f:\n" +
                         "    f.write('Merhaba Dünya!')\n\n" +
                         
                         "4. Dosyaya Ekleme:\n" +
                         "with open('dosya.txt', 'a') as f:\n" +
                         "    f.write('\\nYeni satır')\n\n" +
                         
                         "5. Dosya Kontrolleri:\n" +
                         "import os\n" +
                         "os.path.exists('dosya.txt')  # dosya var mı?\n" +
                         "os.remove('dosya.txt')      # dosya silme\n\n" +
                         
                         "6. CSV Dosyaları:\n" +
                         "import csv\n" +
                         "with open('veri.csv', 'r') as f:\n" +
                         "    okuyucu = csv.reader(f)\n" +
                         "    for satir in okuyucu:\n" +
                         "        print(satir)\n\n" +
                         
                         "7. JSON Dosyaları:\n" +
                         "import json\n" +
                         "data = {'ad': 'Ali', 'yas': 25}\n" +
                         "with open('veri.json', 'w') as f:\n" +
                         "    json.dump(data, f)";
                break;

            case 12: // Input/Output
                content = "Python'da Input/Output İşlemleri\n\n" +
                         "1. Kullanıcıdan Veri Alma:\n" +
                         "isim = input('İsminiz: ')\n" +
                         "yas = int(input('Yaşınız: '))\n\n" +
                         
                         "2. Ekrana Yazdırma:\n" +
                         "print('Merhaba Dünya!')\n" +
                         "print('Sayı:', 42)\n\n" +
                         
                         "3. Formatlı Yazdırma:\n" +
                         "ad = 'Ali'\n" +
                         "yas = 25\n" +
                         "print(f'{ad} {yas} yaşında')\n" +
                         "print('{} {} yaşında'.format(ad, yas))\n\n" +
                         
                         "4. Print Parametreleri:\n" +
                         "print('A', 'B', sep='-')  # A-B\n" +
                         "print('X', end='')  # satır sonu yok\n\n" +
                         
                         "5. Özel Karakterler:\n" +
                         "\\n: Yeni satır\n" +
                         "\\t: Tab\n" +
                         "\\': Tek tırnak\n" +
                         "\\\": Çift tırnak\n\n" +
                         
                         "6. Sayı Formatları:\n" +
                         "sayi = 42\n" +
                         "print(f'{sayi:03d}')  # 042\n" +
                         "pi = 3.14159\n" +
                         "print(f'{pi:.2f}')  # 3.14\n\n" +
                         
                         "7. Hizalama:\n" +
                         "text = 'Python'\n" +
                         "print(f'{text:>10}')  # sağa yasla\n" +
                         "print(f'{text:<10}')  # sola yasla\n" +
                         "print(f'{text:^10}')  # ortala";
                break;

            case 13: // Mini Proje
                content = "Python Mini Proje: Basit Not Defteri\n\n" +
                         "Bu projede öğrendiğimiz konuları kullanarak\n" +
                         "basit bir not defteri uygulaması yapacağız.\n\n" +
                         
                         "1. Proje Yapısı:\n" +
                         "# notlar.py\n" +
                         "class NotDefteri:\n" +
                         "    def __init__(self):\n" +
                         "        self.notlar = {}\n\n" +
                         "    def not_ekle(self, baslik, icerik):\n" +
                         "        self.notlar[baslik] = icerik\n\n" +
                         "    def not_sil(self, baslik):\n" +
                         "        if baslik in self.notlar:\n" +
                         "            del self.notlar[baslik]\n\n" +
                         "    def notlari_goster(self):\n" +
                         "        for baslik, icerik in self.notlar.items():\n" +
                         "            print(f'\\n{baslik}:\\n{icerik}')\n\n" +
                         
                         "2. Ana Program:\n" +
                         "def main():\n" +
                         "    defterim = NotDefteri()\n" +
                         "    while True:\n" +
                         "        print('\\n1. Not Ekle')\n" +
                         "        print('2. Notları Göster')\n" +
                         "        print('3. Not Sil')\n" +
                         "        print('4. Çıkış')\n" +
                         "        secim = input('Seçiminiz: ')\n\n" +
                         "        if secim == '1':\n" +
                         "            baslik = input('Başlık: ')\n" +
                         "            icerik = input('İçerik: ')\n" +
                         "            defterim.not_ekle(baslik, icerik)\n" +
                         "        elif secim == '2':\n" +
                         "            defterim.notlari_goster()\n" +
                         "        elif secim == '3':\n" +
                         "            baslik = input('Silinecek not başlığı: ')\n" +
                         "            defterim.not_sil(baslik)\n" +
                         "        elif secim == '4':\n" +
                         "            break\n\n" +
                         
                         "if __name__ == '__main__':\n" +
                         "    main()\n\n" +
                         
                         "Bu proje şunları içerir:\n" +
                         "• Sınıf yapısı\n" +
                         "• Sözlük kullanımı\n" +
                         "• Döngüler ve koşullar\n" +
                         "• Fonksiyonlar\n" +
                         "• Kullanıcı girişi\n" +
                         "• Modül yapısı";
                break;

            default:
                content = "İçerik bulunamadı.";
        }
        contentTextView.setText(content);
    }
} 