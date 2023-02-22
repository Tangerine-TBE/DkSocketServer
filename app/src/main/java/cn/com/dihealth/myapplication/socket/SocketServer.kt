package cn.com.dihealth.myapplication.socket

import cn.com.dihealth.myapplication.Constant
import com.dangkang.cbrn.socket.SocketDevice
import java.net.ServerSocket

/**
 * handler 画面更新
 * 单例
 * 回调
 * 构造
 * 策略
 * 心跳
 * 超时*/
class SocketServer private constructor() {
    private var serverSocket: ServerSocket? = null
    private var connect = true
    private var close = true
    private val devicesCache: ArrayList<AbstractDevice> = ArrayList()

    companion object {
        val instance: SocketServer by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SocketServer()
        }
    }

    /*初始化设置*/
    /*打开socket端口*/
    fun start(socketCallBack: SocketCallBack) {
        close = true
        serverSocket = ServerSocket(Constant.SOCKET_SERVER_PORT)
        val socketHandler = SocketHandler(socketCallBack)
        Thread {
            try {
                while (close) {
                    val socketDevice = serverSocket!!.accept()
                    val device = SocketDevice(socketDevice,socketHandler)
                    devicesCache.add(device)
                    Thread{
                        while (connect && close) {
                            connect = device.read()
                            if (!connect) {
                                devicesCache.remove(device)
                            }
                        }
                        connect = true
                    }.start()

                }

            } catch (e: Exception) {
            }

        }.start()
    }

    fun destroyServer() {
        close = false
        if (serverSocket != null) {
            serverSocket?.close()
        }
        for (i in 0 until devicesCache.size) {
            devicesCache[i].close()
        }
        devicesCache.clear()
    }

    fun devicesCache(): ArrayList<AbstractDevice> {
        if (!close) {
//            L.e("Socket端口已关闭")
        }
        return devicesCache
    }

    fun isStart(): Boolean {
        return close
    }
}