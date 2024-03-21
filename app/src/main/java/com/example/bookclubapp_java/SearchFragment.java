package com.example.bookclubapp_java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    SearchView searchView;
    CustomAdapter customAdapter;
    RecyclerView BookRecycler;
    List<Book> bookList;
    ArrayList<Book> books;
    DBHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = view.findViewById(R.id.bookListSearchView);
        BookRecycler = view.findViewById(R.id.allBookRecycler);

        customAdapter = new CustomAdapter(getActivity(), books);
        customAdapter.setSearchFragment(this);

        //Initialise DBHelper.
        myDB = new DBHelper(requireContext());

        //Retrieves all the books with an empty search string.
        retrieveAllBooks("");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter the list based on the new text from the user.
                filterList(newText);
                return true;
            }
        });
        return view;
    }

    private void filterList(String newText) {
        retrieveAllBooks(newText);

    }

    void retrieveAllBooks(String searchText){
        DBHelper myDB = new DBHelper(requireContext());
        ArrayList<Book> bookList = myDB.librarySearch(searchText);

        CustomAdapter customAdapter = new CustomAdapter(getActivity(), bookList);
        BookRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        BookRecycler.setAdapter(customAdapter);
    }
}