package com.addpayafrica.printerappsdk6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.wisedevice.sdk.IInitDeviceSdkListener
import com.wisedevice.sdk.WiseDeviceSdk
import com.wisepos.smartpos.InitPosSdkListener
import com.wisepos.smartpos.WisePosSdk



class MainActivity : AppCompatActivity(), InitPosSdkListener{

    lateinit var wisePosSdk: WisePosSdk
    lateinit var wiseDeviceSdk: WiseDeviceSdk

    companion object {
        const val  TAG = "sdkdemo"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        wisePosSdk = WisePosSdk.getInstance() //Obtain the WisePsoSdk object.
        wiseDeviceSdk = WiseDeviceSdk.getInstance()

        if(wisePosSdk == null){
            return;

        }

       Log.d( TAG, "initPosSdk: process！")
        wisePosSdk.initPosSdk(this, object : InitPosSdkListener {
            //Initialize the SDK and bind the service
            override fun onInitPosSuccess() {
                Log.d( TAG, "initPosSdk: success!")
            }


            override fun onInitPosFail(i: Int) {
                Log.d( TAG, "initPosSdk: fail!")
            }
        })

        wiseDeviceSdk.initDeviceSdk(this, object : IInitDeviceSdkListener {
            override fun onInitPosSuccess() {
                Log.d( TAG, "initDeviceSdk: success!")
            }

            override fun onInitPosFail(i: Int) {
                Log.d( TAG, "initDeviceSdk: fail!")
            }
        })

        toPrinter()




    }

    private fun toPrinter(){


        val intent = Intent(this, PrinterActivity::class.java)
        startActivity(intent)

    }

    override fun onInitPosSuccess() {
        TODO("Not yet implemented")
    }

    override fun onInitPosFail(errCode: Int) {
        TODO("Not yet implemented")
    }


}