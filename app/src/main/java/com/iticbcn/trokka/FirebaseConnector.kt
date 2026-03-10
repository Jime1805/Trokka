package com.iticbcn.trokka

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import java.lang.Exception

object FirebaseConnector {
    val db: FirebaseFirestore by lazy { Firebase.firestore }

    var dataInfo = UsageInfo()

    suspend fun cargarInfo(idDispositiu: String): Result<UsageInfo> {
        return try {
            val doc = db.collection("Device").document(idDispositiu).get().await()

            val valorbbdd = doc.toObject<UsageInfo>()
            if (valorbbdd != null) {
                dataInfo = valorbbdd
                Result.success(valorbbdd)
            } else {
                Result.failure(Exception("Informació no trobada. Documentid ${idDispositiu}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun guardarInfo(idDispositiu: String, usageInfo: UsageInfo): Result<Unit> {
        return try {
            db.collection("Device").document(idDispositiu).set(usageInfo).await()
            dataInfo = usageInfo
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun addHores(hores: Int) {
        dataInfo.horasUso += hores
    }

    fun addVisitasMapa() {
        dataInfo.countVisitasMapa++
    }

    fun addCrearObjeto() {
        dataInfo.countCrearObjeto++
    }

    fun addEntradasApp() {
        dataInfo.countEntradesApp++
    }

    fun addFavoritos() {
        dataInfo.countFavoritos++
    }
}