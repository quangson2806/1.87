package com.edu.usth.finalappmobile;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import book.Book;
import category.Category;
import category.CategoryAdapter;

public class ImageFragment extends AppCompatActivity {
    private RecyclerView rcvcategory;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle saveInStanceState){
        super.onCreate(saveInStanceState);
        setContentView(R.layout.fragment_image);

        rcvcategory =findViewById(R.id.rcv_category);
        categoryAdapter =new CategoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rcvcategory.setLayoutManager(linearLayoutManager);

        categoryAdapter.setData(getListCategory());
        rcvcategory.setAdapter(categoryAdapter);

    }
private List<Category> getListCategory(){
    List<Category> listCategory = new ArrayList<>();
    List<Book> listBook =  new ArrayList<>();
    listBook.add(new Book(R.drawable.anh1,"Book 1"));
    listBook.add(new Book(R.drawable.anh2,"Book 2"));
    listBook.add(new Book(R.drawable.anh4,"Book 3"));

    listBook.add(new Book(R.drawable.anh1,"Book 1"));
    listBook.add(new Book(R.drawable.anh2,"Book 2"));
    listBook.add(new Book(R.drawable.anh3,"Book 3"));

    listCategory.add(new Category("Category 1",listBook));
    listCategory.add(new Category("Category 2",listBook));
    listCategory.add(new Category("Category 3",listBook));
    return listCategory;
}
}

