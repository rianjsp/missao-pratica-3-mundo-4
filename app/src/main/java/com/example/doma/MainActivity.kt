package com.example.doma

import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import java.util.*

class MainActivity : Activity(), TextToSpeech.OnInitListener {

    private lateinit var tts: TextToSpeech
    private val VOICE_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, this)

        findViewById<Button>(R.id.btnReadMessage).setOnClickListener {
            speak("Voce tem uma nova tarefa: verificar os equipamentos da sala 54.")
        }

        findViewById<Button>(R.id.btnVoiceCommand).setOnClickListener {
            startVoiceInput()
        }

        findViewById<Button>(R.id.btnAlert).setOnClickListener {
            sendAlert("Atencao! Risco de queda detectado no setor A.")
        }
    }

    private fun startVoiceInput() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }
        try {
            startActivityForResult(intent, VOICE_REQUEST_CODE)
        } catch (e: Exception) {
            Toast.makeText(this, "Reconhecimento de voz nao suportado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val command = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)?.lowercase()
            handleVoiceCommand(command)
        }
    }

    private fun handleVoiceCommand(command: String?) {
        when {
            command?.contains("alerta") == true -> sendAlert("Verifique o equipamento no setor A.")
            command?.contains("tarefa") == true -> speak("Voce tem uma nova tarefa.")
            else -> speak("Comando nao reconhecido.")
        }
    }

    private fun sendAlert(message: String) {
        speak(message)
        sendNotification("Alerta de Seguranca", message)
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    private fun sendNotification(title: String, content: String) {
        val notification = NotificationCompat.Builder(this, "alert_channel")
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .build()
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).notify(1, notification)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.getDefault()
        } else {
            Toast.makeText(this, "Erro ao inicializar TTS", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}
