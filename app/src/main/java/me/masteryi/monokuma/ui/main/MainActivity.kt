package me.masteryi.monokuma.ui.main

import androidx.viewpager2.widget.ViewPager2
import me.masteryi.monokuma.R
import me.masteryi.monokuma.base.BaseActivity
import me.masteryi.monokuma.databinding.ActivityMainBinding

/**
 * main activity
 *
 * @author Ethan Lee
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        binding.viewPager.adapter = MainAdapter(this)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNavigation.menu.getItem(position).isChecked = true
            }
        })

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            val position = when (it.itemId) {
                R.id.home -> 0
                R.id.category -> 1
                else -> 1
            }
            binding.viewPager.currentItem = position
            true
        }
    }

    override fun setupActionBar() {}
}