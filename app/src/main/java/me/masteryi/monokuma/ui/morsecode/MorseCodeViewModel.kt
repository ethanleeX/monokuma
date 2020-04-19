package me.masteryi.monokuma.ui.morsecode

import android.app.Application
import androidx.lifecycle.MutableLiveData
import me.masteryi.monokuma.base.BaseViewModel

/**
 * @author Ethan Lee
 */
class MorseCodeViewModel(application: Application) : BaseViewModel(application) {
    companion object {
        const val MODE_CODE_TO_TEXT = 0
        const val MODE_TEXT_TO_CODE = 1
        private const val DEFAULT_DIT_DURATION = 100 // 默认 dit 间隔 t
    }

    val convertMode = MutableLiveData(MODE_TEXT_TO_CODE)
    val output = MutableLiveData<String>()

    // TODO: 支持自定义 dit dah separator space
    var dit = '.'
    var dah = '-'
    var separator = ' ' // 字符间间隔
    var space = '/' // 单词间间隔

    // TODO: 支持自动以间隔
    var duration = DEFAULT_DIT_DURATION
    var ditDuration = DEFAULT_DIT_DURATION
    var dahDuration = 3 * DEFAULT_DIT_DURATION
    var separatorDuration = 3 * DEFAULT_DIT_DURATION
    var spaceDuration = 7 * DEFAULT_DIT_DURATION

    fun changeMode() {
        if (convertMode.value == MODE_CODE_TO_TEXT) {
            convertMode.value = MODE_TEXT_TO_CODE
        } else {
            convertMode.value = MODE_CODE_TO_TEXT
        }
    }

    fun convert(input: String) {
        if (convertMode.value == MODE_CODE_TO_TEXT) {
            try {
                output.value = MorseCodeUtil.morseCode2Text(input)
            } catch (e: IllegalArgumentException) {
                showToast("输入的摩斯码不合法")
            }
        } else {
            output.value = MorseCodeUtil.text2MorseCode(input)
        }
    }
}