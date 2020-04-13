package me.masteryi.monokuma.main

import android.os.Bundle
import me.masteryi.monokuma.R
import me.masteryi.monokuma.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}