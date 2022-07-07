package com.example.pesoapp.service

import com.example.pesoapp.View.ViewModel.IndiceMasaCorporal
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface OnQueryCompletedCallback {
    fun <T> onSuccess(result: T)
    fun <T> onFailure(result: T)
}

class FirestoreService {

    companion object {
        const val IMC_COLLECTION = "IMC"
        const val TAG = "FirestoreService"
    }

    private val db = Firebase.firestore

    fun getUserData(uid: String, onComplete: OnQueryCompletedCallback) {
        db.collection(IMC_COLLECTION)
            .document(uid)
            .get()
            .addOnSuccessListener {
                onComplete.onSuccess(it)
            }
            .addOnFailureListener {
                onComplete.onFailure(it)
            }
    }

    fun update(uid: String, nuevosDatos: IndiceMasaCorporal, onComplete: OnQueryCompletedCallback) {
        db.collection(IMC_COLLECTION)
            .document(uid)
            .set(nuevosDatos)
            .addOnSuccessListener {
                onComplete.onSuccess(it)
            }
            .addOnFailureListener {
                onComplete.onFailure(it)
            }
    }
}