package me.masteryi.monokuma.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Ethan Lee
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}