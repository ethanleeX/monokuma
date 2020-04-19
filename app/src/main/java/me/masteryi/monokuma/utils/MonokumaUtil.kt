package me.masteryi.monokuma.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

/**
 * @author Ethan Lee
 */
class MonokumaUtil {
    companion object {
        fun copyText(context: Context, content: CharSequence) {
            val cm: ClipboardManager =
                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("monokuma", content)
            cm.setPrimaryClip(clipData)
        }
    }
}