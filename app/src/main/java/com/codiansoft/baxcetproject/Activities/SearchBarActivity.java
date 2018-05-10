package com.codiansoft.baxcetproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.codiansoft.baxcetproject.R;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;

import java.util.ArrayList;

public class SearchBarActivity extends AppCompatActivity {

    com.quinny898.library.persistentsearch.SearchBox search;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        activity=this;

        search=(SearchBox)findViewById(R.id.searchbox);

        for(int x = 0; x < 10; x++){
            SearchResult option = new SearchResult("Result " + Integer.toString(x), getResources().getDrawable(R.drawable.cash));
            search.addSearchable(option);
        }
        search.setLogoText("My App");
        search.setMenuListener(new SearchBox.MenuListener(){

            @Override
            public void onMenuClick() {
                //Hamburger has been clicked
                Toast.makeText(activity, "Menu click", Toast.LENGTH_LONG).show();
            }

        });
        search.setSearchListener(new SearchBox.SearchListener(){

            @Override
            public void onSearchOpened() {
                //Use this to tint the screen
            }

            @Override
            public void onSearchClosed() {
                //Use this to un-tint the screen
            }

            @Override
            public void onSearchTermChanged(String s) {

            }
//
//            @Override
//            public void onSearchTermChanged() {
//                //React to the search term changing
//                //Called after it has updated results
//            }

            @Override
            public void onSearch(String searchTerm) {
                Toast.makeText(activity, searchTerm +" Searched", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onResultClick(SearchResult result){
                //React to a result being clicked
            }


            @Override
            public void onSearchCleared() {

            }

        });
        search.enableVoiceRecognition(this);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( requestCode == SearchBox.VOICE_RECOGNITION_CODE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data
                    .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            search.populateEditText(String.valueOf(matches));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
