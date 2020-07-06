package com.Vsupport.India;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView tv;
    int ct=0;
    ArrayList<ExampleItem> exampleList=new ArrayList<>();
    ArrayList<String> all_app_names = new ArrayList<>(Arrays.asList("TikTok","Instagram","Shareit","Kwai","UC Browser","Baidu map","Shein","Clash of Kings","DU battery saver","DU battery saver","DU battery saver","DU battery saver","Helo","Likee","YouCam makeup","Mi Community","CM Browers","Virus Cleaner","APUS Browser","ROMWE","Club Factory","Newsdog","Newsdog","Beuty Plus","WeChat","UC News","QQ Mail","Weibo","Xender","QQ Music","QQ Newsfeed","Bigo Live","Bigo Live","selfieCity","Mail Master","Parallel Space","Parallel Space","Parallel Space","Parallel Space","Parallel Space","Parallel Space","Parallel Space","Parallel Space","Mi Video Call – Xiaomi","WeSync","ES File Explorer","Viva Video – QU Video Inc","Meitu","Vigo Video","New Video Status","DU Recorder","Vault- Hide","Cache Cleaner DU App studio","DU Cleaner","DU Browser","Hago Play With New Friends","Cam Scanner","Clean Master – Cheetah Mobile","Wonder Camera","Photo Wonder","QQ Player","We Meet","Sweet Selfie","Baidu","Vmate","QQ International","QQ Security Center","QQ Launcher","U Video","V fly Status Video","Mobile Legends","DU Privacy"));

    ArrayList<String> packages = new ArrayList<>(Arrays.asList("com.estrongs.android.pop","com.ss.android.ugc.trill","com.zhiliaoapp.musically","com.lenovo.anyshare.gps","com.kwai.video","com.UCMobile.intl","com.baidu.BaiduMap","com.zzkko","com.hcg.cok.gp","com.limsky.ramcleaner","com.DUsavebattery.faster.charge","savebattery.doctor.batterysaver.fastcharger.cleaner","savebattery.optimizer.batterysaver.fastcharger.cleaner","app.buzz.share","video.like","com.cyberlink.youcammakeup","com.mi.global.bbs","com.ksmobile.cb","phone.antivirus.virus.cleaner.junk.clean.speed.booster.master","com.apusapps.browser","com.romwe","club.fromfactory","com.newsdog","com.newsdog.cnn","com.commsource.beautyplus","com.tencent.mm","com.uc.iflow","com.tencent.androidqqmail","com.weico.international","cn.xender","com.tencent.qqmusic","com.tencent.mtt.intl","sg.bigo.live","sg.bigo.live.lite","com.meitu.wheecam","com.netease.mail","com.lbe.parallel.intl","com.parallel.space.lite","com.lbe.parallel.intl.arm64","com.parallel.space.pro","com.parallel.space.pro.arm64","com.parallel.space.lite.arm64","com.lbe.parallel.intl.arm32","com.parallel.space.lite.arm32","com.wMivideocallandchat_9258593","com.jpro.wesync","com.file.manager.filebrowser","com.quvideo.xiaoying","com.mt.mtxx.mtxx","com.ss.android.ugc.boom","com.video.status.app.for.whatsapp","com.duapps.recorder","com.netqin.ps","com.duapps.cleaner","com.duapps.cleaner","com.baidu.browser.inter","com.yy.hiyo","com.intsig.camscanner","com.cleanmaster.mguard","com.baidu.baiducamera","cnjingling.motu.photowonder","com.tencent.research.drop","com.videochat.livechat.wemeet","com.meitu.wheecam","com.baidu.searchbox","com.uc.vmate","com.tencent.mobileqq","com.tencent.token","com.tencent.qqlauncher","com.kwai.global.video.social.kwaigo","com.ai.bfly","com.mobile.legends","com.szipcs.duprivacylock"));
    ArrayList<String> apps_found = new ArrayList<>();
    ArrayList<String> apps_found_names = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);
        progressBar= findViewById(R.id.progressBar);
        for (int i = 0; i <packages.size() ; i++) {
            boolean isAppInstalled = appInstalledOrNot(packages.get(i));

            if(isAppInstalled) {
                //This intent will help you to launch if the package is already installed
                apps_found.add(packages.get(i));
                ct++;

                // apps_found_names.add(all_app_names.get(i));

            }
            else{
                //exampleList.add(exampleItem);
                //exampleList.add(new ExampleItem(mImages[i],all_app_names.get(i),packages.get(i)));
                //exampleList.add(new ExampleItem(R.drawable.aajtak,"whatsapp","helloworld"));
            }

        }





            progressBar.setVisibility(View.INVISIBLE);
            //Custom toast message starts here...
        LayoutInflater inflater= getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.toast_root));

        TextView tv=layout.findViewById(R.id.toast_text_view);
        ImageView iv=layout.findViewById(R.id.toast_image_view);

        tv.setText("There are totally " + ct + " Chinese Apps");
        iv.setImageResource(R.drawable.ic_smile);

        Toast toast=new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        //Custom toast message ends here
            Intent intent = new Intent(getApplicationContext(), List_of_Chinese_Apps.class);
            intent.putExtra("AppPackagesFound", apps_found);
            /*intent.putExtra("AppNames",apps_found_names);*/
            //intent.putExtra("values",exampleList);
            intent.putExtra("Count", ct);
            startActivity(intent);



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
    public void onBackPressed() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Do you want to Exit this app ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.finishAffinity(MainActivity.this);

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
