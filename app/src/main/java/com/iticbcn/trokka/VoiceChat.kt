package com.iticbcn.trokka

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log

object VoiceChat {
    private var recognizer: SpeechRecognizer ?= null
    private lateinit var recognizerIntent: Intent
    private var callback: ((String) -> Unit) ?= null

    fun initObjects(context: Context){
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ca-ES") // o "es-ES"
        }
        if (recognizer == null){
            recognizer = SpeechRecognizer.createSpeechRecognizer(context)

        }
    }

    fun initiListeners(onResult: (String?) -> Unit){
        this.callback =  onResult
        recognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onResults(results: Bundle?) {
                val spokenText: String = results
                    ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.get(0)
                    ?.lowercase().toString()
                callback?.invoke(spokenText)
            }

            override fun onError(error: Int) {
                Log.d("VoiceChat", error.toString())
                when (error) {
                    SpeechRecognizer.ERROR_NO_MATCH -> {
                        // ERROR 7: Ha escuchado ruido pero no palabras.
                        Log.d("VoiceChat", "No he entendido lo que has dicho.")

                        // Opcional: Hacer que vuelva a escuchar automáticamente
                        // recognizer?.startListening(recognizerIntent)
                    }
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> {
                        // ERROR 6: Silencio total durante unos segundos.
                        Log.d("VoiceChat", "No has dicho nada.")
                    }
                    else -> {
                        Log.e("VoiceChat", "Error de voz número: $error")
                    }
                }
            }

            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        recognizer?.startListening(recognizerIntent)
    }
}