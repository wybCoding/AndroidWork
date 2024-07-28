package com.example.electricanalyse.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.electricanalyse.R
import com.hurryyu.bestchooser.ChooserView

class TopChooseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ChooserView(context, attrs, defStyleAttr) {

    private var viewHolder: ViewHolder? = null

    private class ViewHolder(view: View) {
        val textView: TextView = view.findViewById(R.id.tv_top)
    }
    override fun createView(attrs: AttributeSet?) {
        val view = LayoutInflater.from(context).inflate(R.layout.overview_topnavigation_item, this)
        viewHolder = ViewHolder(view)

        val obtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.TopChooseView)
        //取出字体以及是否被选中状态
        val text = obtainStyledAttributes.getString(R.styleable.TopChooseView_text) ?: ""
        viewHolder?.textView?.text = text
        obtainStyledAttributes.recycle()

    }

    override fun onSelectChange(isSelect: Boolean) {
        if (isSelect) {

            // 选项被选中时改变文字颜色
            viewHolder?.textView?.setTextColor(ContextCompat.getColor(context, R.color.selected_text_color))
        } else {
            // 选项未被选中时恢复默认文字颜色
            viewHolder?.textView?.setTextColor(ContextCompat.getColor(context, R.color.default_text_color))
        }
    }
}