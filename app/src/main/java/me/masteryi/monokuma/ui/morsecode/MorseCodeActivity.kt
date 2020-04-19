package me.masteryi.monokuma.ui.morsecode

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.media.AudioManager
import android.media.ToneGenerator
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import me.masteryi.monokuma.R
import me.masteryi.monokuma.base.BaseActivity
import me.masteryi.monokuma.databinding.ActivityMorseCodeBinding
import me.masteryi.monokuma.utils.MonokumaUtil

/**
 * @author Ethan Lee
 */
class MorseCodeActivity : BaseActivity<ActivityMorseCodeBinding>() {
    private lateinit var viewModel: MorseCodeViewModel
    private val toneGenerator: ToneGenerator by lazy {
        ToneGenerator(AudioManager.STREAM_ALARM, 100)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_morse_code
    }

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MorseCodeViewModel::class.java)
        viewModel.convertMode.observe(this, Observer {
            if (it == MorseCodeViewModel.MODE_CODE_TO_TEXT) {
                binding.modeFrom.setText(R.string.morse_code_code)
                binding.modeTo.setText(R.string.morse_code_text)
                binding.input.setHint(R.string.morse_code_hint_code)
            } else {
                binding.modeFrom.setText(R.string.morse_code_text)
                binding.modeTo.setText(R.string.morse_code_code)
                binding.input.setHint(R.string.morse_code_hint_text)
            }
        })

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    override fun initView() {
        binding.convert.setOnClickListener {
            viewModel.changeMode()

            val animatorSet = AnimatorSet()
            animatorSet.playTogether(
                ObjectAnimator.ofFloat(it, "rotation", 0.0F, 180.0F),
                ObjectAnimator.ofFloat(it, "scaleX", 1.0F, 0.5F, 1.0F),
                ObjectAnimator.ofFloat(it, "scaleY", 1.0F, 0.5F, 1.0F)
            )
            animatorSet.start()
        }

        binding.input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.convert(s.toString())
            }
        })

        binding.clear.setOnClickListener {
            binding.input.setText("")
        }

        binding.copy.setOnClickListener {
            MonokumaUtil.copyText(applicationContext, binding.output.text)
            showToast(getString(R.string.copy_success))
        }

        binding.play.setOnClickListener {
            if (viewModel.convertMode.value == MorseCodeViewModel.MODE_TEXT_TO_CODE) {
                toneGenerator.stopTone()
                // TODO: 重构
                lifecycleScope.launch {
                    flow {
                        viewModel.output.value?.forEach {
                            when (it) {
                                viewModel.dit -> {
                                    emit(it)
                                    delay(viewModel.ditDuration.toLong() + viewModel.duration.toLong())
                                }
                                viewModel.dah -> {
                                    emit(it)
                                    delay(viewModel.dahDuration.toLong() + viewModel.duration.toLong())
                                }
                                viewModel.separator -> {
                                    emit(it)
                                    delay(viewModel.separatorDuration.toLong())
                                }
                                viewModel.space -> {
                                    emit(it)
                                    delay(viewModel.spaceDuration.toLong())
                                }
                            }
                        }
                    }.collect {
                        when (it) {
                            viewModel.dit -> {
                                toneGenerator.startTone(
                                    ToneGenerator.TONE_CDMA_DIAL_TONE_LITE,
                                    viewModel.ditDuration
                                )
                            }
                            viewModel.dah -> {
                                toneGenerator.startTone(
                                    ToneGenerator.TONE_CDMA_DIAL_TONE_LITE,
                                    viewModel.dahDuration
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}