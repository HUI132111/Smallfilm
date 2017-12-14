package com.bwie.aizhonghui.mvpfmodule.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by DANGEROUS_HUI on 2017/11/14.
 */

public class NetUtils {
    public static void NetworkUtils(Context context, Network  network){
        ConnectivityManager conmanager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo =conmanager.getActiveNetworkInfo();
        if (activeNetworkInfo==null) {
            network.UNnetwork();
        }else{
            if (activeNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE) {
                network.Mobilenetwork();
            }else if (activeNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI) {
                network.Wifinetwork();

            }else{
                network.UNnetwork();
            }
        }



    }
    public interface Network{
        void Mobilenetwork();
        void Wifinetwork();
        void UNnetwork();
    }
}
