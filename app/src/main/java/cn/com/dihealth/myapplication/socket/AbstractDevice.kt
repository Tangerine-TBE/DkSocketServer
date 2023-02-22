package cn.com.dihealth.myapplication.socket

interface AbstractDevice {
    fun write()
    fun read(): Boolean
    fun close()
}