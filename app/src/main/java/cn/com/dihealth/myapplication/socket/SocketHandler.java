package cn.com.dihealth.myapplication.socket;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

public class SocketHandler extends Handler {
    public static final int DISCONNECT = 0;
    public static final int RECEIVER = 2;
    private final SocketCallBack mSocketCallBack;
    public SocketHandler(SocketCallBack socketCallBack){
        super(Looper.getMainLooper());
        this.mSocketCallBack = socketCallBack;
    }
    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what){
            case DISCONNECT:
                mSocketCallBack.disconnect();
                break;
            case RECEIVER:
                mSocketCallBack.receiver((String) msg.obj);
                break;
            default:
                break;
        }
    }
}
