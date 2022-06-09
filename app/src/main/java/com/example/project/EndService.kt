package com.example.project

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings

class EndService : Service() {
    private lateinit var player:MediaPlayer

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI)
        //player.setLooping(true)

        // starting the process
        player.start()
        // returns the status of the program
        return START_STICKY
    }
    // execution of the service will stop on calling this method
    override fun onDestroy() {
        super.onDestroy()
        // stopping the process
        player.stop()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}