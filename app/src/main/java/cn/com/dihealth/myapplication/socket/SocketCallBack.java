package cn.com.dihealth.myapplication.socket;

public interface SocketCallBack {
        void disconnect();
        void receiver(String data);
}
