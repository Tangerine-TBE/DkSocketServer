package cn.com.dihealth.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.com.dihealth.myapplication.socket.SocketCallBack
import cn.com.dihealth.myapplication.socket.SocketServer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SocketServer.instance.start(object:SocketCallBack{
            override fun disconnect() {

            }

            override fun receiver(data: String?) {
            }
        })
    }
}