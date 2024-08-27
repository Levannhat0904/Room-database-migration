package net.braniumacademy.lesson12_11.factory

import android.content.Intent

interface IntentFactory<T> {
    fun createIntent(actionType: IntentActionType, obj: T?) : Intent
}