package me.masteryi.monokuma.base

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import me.masteryi.monokuma.R

/**
 * base activity
 *
 * @author Ethan Lee
 */
abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: T
    protected var toolbar: Toolbar? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        initViewModel()
        initToolbar()
        initView()
        initData()
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        setupActionBar()
    }

    protected open fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    protected open fun initViewModel() {}

    protected abstract fun initView()

    protected open fun initData() {}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    protected fun showToast(@StringRes msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}