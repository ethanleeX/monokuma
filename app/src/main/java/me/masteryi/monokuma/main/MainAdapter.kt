package me.masteryi.monokuma.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.masteryi.monokuma.main.home.HomeFragment

/**
 * main adapter
 *
 * @author Ethan Lee
 */
class MainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        // TODO:
        return HomeFragment.newInstance()
    }
}