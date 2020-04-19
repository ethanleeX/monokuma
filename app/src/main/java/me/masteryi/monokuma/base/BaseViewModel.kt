package me.masteryi.monokuma.base

import android.app.Application
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel

/**
 * @author Ethan Lee
 */
abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected fun showToast(msg: String) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes msg: Int) {
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
    }
}