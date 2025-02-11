package com.protesq.protesqpy;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int TOPIC_DETAIL_REQUEST = 1;
    private RecyclerView recyclerView;
    private TopicAdapter topicAdapter;
    private List<Topic> topics;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        initializeTopics();
        
        topicAdapter = new TopicAdapter(topics, this);
        recyclerView.setAdapter(topicAdapter);

        searchView = findViewById(R.id.searchView);
        setupSearchView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TOPIC_DETAIL_REQUEST && resultCode == RESULT_OK) {
            if (data != null && data.getBooleanExtra("topicCompleted", false)) {
                // Adapter'ı yenile
                topicAdapter.notifyDataSetChanged();
            }
        }
    }

    private void initializeTopics() {
        topics = new ArrayList<>();
        topics.add(new Topic("Python'a Giriş", "Python programlama diline genel bakış", true));
        topics.add(new Topic("Değişkenler ve Veri Tipleri", "Temel veri tipleri ve değişken kullanımı", false));
        topics.add(new Topic("Operatörler", "Aritmetik, karşılaştırma ve mantıksal operatörler", false));
        topics.add(new Topic("Döngüler", "for ve while döngüleri", false));
        topics.add(new Topic("Koşullu İfadeler", "if, elif, else yapıları ve karar mekanizmaları", false));
        topics.add(new Topic("Listeler", "Liste yapısı ve metodları", false));
        topics.add(new Topic("Sözlükler", "Anahtar-değer çiftleri ile veri organizasyonu", false));
        topics.add(new Topic("Demetler (Tuples)", "Değiştirilemez veri koleksiyonları", false));
        topics.add(new Topic("Kümeler (Sets)", "Benzersiz elemanlar koleksiyonu", false));
        topics.add(new Topic("Fonksiyonlar", "Fonksiyon tanımlama ve kullanımı", false));
        topics.add(new Topic("Hata Yönetimi", "Try-except blokları ile hata yakalama", false));
        topics.add(new Topic("Modüller", "Python modülleri ve kütüphaneleri", false));
        topics.add(new Topic("Dosya İşlemleri", "Dosya okuma ve yazma işlemleri", false));
        topics.add(new Topic("Input/Output", "Kullanıcı girişi ve çıktı işlemleri", false));
        topics.add(new Topic("Mini Proje", "Öğrenilen konuları içeren uygulama", false));
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterTopics(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterTopics(newText);
                return true;
            }
        });
    }

    private void filterTopics(String query) {
        List<Topic> filteredList = new ArrayList<>();
        for (Topic topic : topics) {
            if (topic.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                topic.getDescription().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(topic);
            }
        }
        topicAdapter.updateList(filteredList);
    }
}