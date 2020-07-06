package com.Vsupport.India;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class List_of_Chinese_Apps extends AppCompatActivity {
    private int STORAGE_PERMISSION_CODE = 1;

    /*private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;*/
    ImageView iv;


    ArrayList<String> apps_found;
    /*ArrayList<String> apps_found_names;*/
    TextView tv;
    Button btn;
    Button btn2;
    int count=6;
    Button btn1;
    BottomNavigationView bottomNavigationView;
    ArrayList<ExampleItem> exampleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of__chinese__apps);
            tv=findViewById(R.id.LoadingText);
            iv=findViewById(R.id.imageViewForResult);
            btn1=findViewById(R.id.refresh);
            btn2= findViewById(R.id.Share);
            btn=findViewById(R.id.button);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (ContextCompat.checkSelfPermission(List_of_Chinese_Apps.this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        try{
                            String whatsAppMessage = "Plz share the image and direct others to remove all these Chinese application from their devices\n"+"Download link : https://codehard2k20.blogspot.com/p/v-support.html \n"+"We support the Nation Install\n V-Support \n#Boycott_Chinese_Products .\n";
                            BitmapDrawable bitmapDrawable=(BitmapDrawable) iv.getDrawable();
                            Bitmap bitmap= bitmapDrawable.getBitmap();
                            String bitmpath= MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"I have patriotism",null);
                            Uri uri=Uri.parse(bitmpath);

                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("image/*");
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            shareIntent.putExtra(Intent.EXTRA_TEXT, whatsAppMessage);
                            //shareIntent.setPackage("com.whatsapp");
                            startActivity(Intent.createChooser(shareIntent, "Share using.."));

                        }catch(Exception e){
                            Toast.makeText(List_of_Chinese_Apps.this, "Please Allow the app permission request", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        requestStoragePermission();
                    }
                }
            });


        //Codes for bottom navigation bar
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.ChineseApps);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.AboutUs:
                        Intent intent =new Intent(getApplicationContext(),AboutUs.class);
                        intent.putExtra("AppPackagesFound",apps_found);
                        //intent.putExtra("values",exampleList);
                        intent.putExtra("Count",count);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Alternatives:
                        Intent intent1 =new Intent(getApplicationContext(),Alternatives.class);
                        /*intent1.putExtra("values",exampleList);*/
                        intent1.putExtra("AppPackagesFound",apps_found);
                        intent1.putExtra("Count",count);
                        startActivity(intent1);
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.ChineseApps:
                        btn.setVisibility(View.INVISIBLE);
                        return true;
                }
                return false;
            }
        });
        // Bottom Navigation View Ends Here

        Intent intent=getIntent();
        //exampleList=(ArrayList<ExampleItem>) intent.getExtras().get("values");


        apps_found=(ArrayList<String>) intent.getExtras().get("AppPackagesFound");
       /* apps_found_names=(ArrayList<String>) intent.getExtras().get("AppNames");*/
        count=(int) intent.getExtras().get("Count");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    tv.setText("Loading...");
                for (int i = 0; i <apps_found.size() ; i++) {

                    delete_app(apps_found.get(i));
                }
                tv.setText("");
                for (int i = 0; i <apps_found.size() ; i++) {
                    boolean isAppInstalled = appInstalledOrNot(apps_found.get(i));

                    if(isAppInstalled) {
                        //This intent will help you to launch if the package is already installed



                        // apps_found_names.add(all_app_names.get(i));

                    }
                    else{
                        count--;
                        //exampleList.add(exampleItem);
                        //exampleList.add(new ExampleItem(mImages[i],all_app_names.get(i),packages.get(i)));
                        //exampleList.add(new ExampleItem(R.drawable.aajtak,"whatsapp","helloworld"));
                    }

                }

            }
        });


        if(count==0){
            iv.setVisibility(View.VISIBLE);
            btn.setVisibility(View.INVISIBLE);
            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.VISIBLE);
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Loading...");
                refreshing(count);
            }
        });


/*        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);*/


    }

    void delete_app(String s){
        Intent intent=new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:"+s));
        startActivity(intent);
    }

    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit this app ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.finishAffinity(List_of_Chinese_Apps.this);

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
    void refreshing(int cnt){
        if (cnt==0){

            iv.setVisibility(View.VISIBLE);
            btn.setVisibility(View.INVISIBLE);
            btn1.setVisibility(View.INVISIBLE);
            btn2.setVisibility(View.VISIBLE);
            tv.setText("");
        }
        else{//Custom toast message starts here...
            LayoutInflater inflater= getLayoutInflater();
            View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_root));

            TextView tv=layout.findViewById(R.id.toast_text_view);
            ImageView iv=layout.findViewById(R.id.toast_image_view);

            tv.setText("There are totally " + cnt + " Chinese Apps left.");
            iv.setImageResource(R.drawable.ic_smile);

            Toast toast=new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            //Custom toast message ends here
            tv.setText("");


        }

    }
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(List_of_Chinese_Apps.this,
                                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
}
