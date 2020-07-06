package com.Vsupport.India;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AboutUs extends AppCompatActivity {

    ArrayList<String> apps_found;
   /* ArrayList<String> apps_found_names;*/
   private WebView webView;
    int count=0;

    BottomNavigationView bottomNavigationView;
    //ArrayList<ExampleItem> exampleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        webView=findViewById(R.id.wv);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://codehard2k20.blogspot.com/p/about-creators.html");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
       // exampleList.add(new ExampleItem(R.drawable.aajtak,"whatsapp","helloworld"));
        Intent intent=getIntent();
        apps_found=(ArrayList<String>) intent.getExtras().get("AppPackagesFound");
       /* apps_found_names=(ArrayList<String>) intent.getExtras().get("AppNames");*/
      // exampleList=(ArrayList<ExampleItem>) intent.getExtras().get("values");
        count=(int) intent.getExtras().get("Count");

        //Codes for bottom navigation bar
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.AboutUs);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.AboutUs:
                        return true;
                    case R.id.Alternatives:

                        Intent intent1 =new Intent(getApplicationContext(),Alternatives.class);
                        //intent1.putExtra("values",exampleList);
                        intent1.putExtra("AppPackagesFound",apps_found);
                        intent1.putExtra("Count",count);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
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
                            ActivityCompat.finishAffinity(AboutUs.this);

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
