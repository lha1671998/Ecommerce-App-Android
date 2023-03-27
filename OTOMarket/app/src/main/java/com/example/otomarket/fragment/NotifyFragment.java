package com.example.otomarket.fragment;

import static android.content.Context.NOTIFICATION_SERVICE;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.otomarket.R;
import com.example.otomarket.activity.ChannelID_Notifycation;

import java.util.Date;


public class NotifyFragment extends Fragment {
    View mViewNF;
    Toolbar mToolbarNF;
    TextView mTitle;
    static Context context = getApplicationContext();

    AppCompatActivity appCompatActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewNF = inflater.inflate(R.layout.fragment_notify, container, false);

        mTitle = mViewNF.findViewById(R.id.toolbar_title);

        mToolbarNF = mViewNF.findViewById(R.id.toolbarNF);
        appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(mToolbarNF);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
       
        return mViewNF;
    }

//    void Pushnotification() {
//
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.suppercar);
//
//        Notification notification = new NotificationCompat.Builder(context, ChannelID_Notifycation.CHANNEL_ID)
//                .setContentTitle("Thông Báo")
//                .setContentText("Bạn đã mua hàng thành công")
//                .setSmallIcon(R.drawable.notification)
//                .setLargeIcon(bitmap)
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .build();
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(NotifyFragment.context);
//        if (notificationManager != null) {
//            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            notificationManager.notify(getNotificationID(), notification);
//        }
//    }
//    private int getNotificationID(){
//        return (int) new Date().getTime();
//    }
}