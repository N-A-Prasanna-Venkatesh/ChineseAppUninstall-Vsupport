package com.Vsupport.India;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Alternatives extends AppCompatActivity {
    ArrayList<String> apps_found;
   /* ArrayList<String> apps_found_names;*/
    int count=0;
    private WebView webView;


    BottomNavigationView bottomNavigationView;
    //ArrayList<ExampleItem> exampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alternatives);
        webView=findViewById(R.id.wv);
        webView.setWebViewClient( new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // check something unique to the urls you want to block
                if (url.contains("play.google.com")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));

                    return true; //with return true, the webview wont try rendering the url
                }
                return false; //let other links work normally
            }

        } );
        webView.loadUrl("https://codehard2k20.blogspot.com/p/alternatives-to-apps.html");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Intent intent=getIntent();
        apps_found=(ArrayList<String>) intent.getExtras().get("AppPackagesFound");
        /*apps_found_names=(ArrayList<String>) intent.getExtras().get("AppNames");*/
        //exampleList=(ArrayList<ExampleItem>) intent.getExtras().get("values");
        count=(int) intent.getExtras().get("Count");

        //Codes for bottom navigation bar
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Alternatives);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.AboutUs:

                        Intent intent1 =new Intent(getApplicationContext(),AboutUs.class);
                        //intent1.putExtra("values",exampleList);
                        intent1.putExtra("AppPackagesFound",apps_found);
                        intent1.putExtra("Count",count);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Alternatives:
                        return true;
                    case R.id.ChineseApps:

                        Intent intent =new Intent(getApplicationContext(),List_of_Chinese_Apps.class);
                        //intent.putExtra("values",exampleList);
                        intent.putExtra("AppPackagesFound",apps_found);
                        intent.putExtra("Count",count);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }
    public void onBackPressed() {

        if (webView.canGoBack())
        {
            webView.goBack();

        }
        else
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Do you want to Exit this app ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(Alternatives.this);

                            // finish();
                            // System.exit(0);
                            //MainActivity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("Nope", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }

    }
}
