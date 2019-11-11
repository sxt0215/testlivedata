package io.github.iamyours.wandroid.binds

import android.app.Activity
import android.text.Html
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import cn.bingoogolapple.bgabanner.BGABanner
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import io.github.iamyours.wandroid.extension.displayWithUrl
import io.github.iamyours.wandroid.extension.hideKeyboard


@BindingAdapter(
    value = ["refreshing", "moreLoading", "hasMore"],
    requireAll = false
)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    refreshing: Boolean,
    moreLoading: Boolean,
    hasMore: Boolean

) {
    if (!refreshing) smartLayout.finishRefresh()
    if (!moreLoading) smartLayout.finishLoadMore()
    smartLayout.setNoMoreData(!hasMore)
}

@BindingAdapter(
    value = ["autoRefresh"]
)
fun bindSmartRefreshLayout(
    smartLayout: SmartRefreshLayout,
    autoRefresh: Boolean
) {
    if (autoRefresh) smartLayout.autoRefresh()
}

@BindingAdapter(
    value = ["onRefreshListener", "onLoadMoreListener"],
    requireAll = false
)
fun bindListener(
    smartLayout: SmartRefreshLayout,
    refreshListener: OnRefreshListener?,
    loadMoreListener: OnLoadMoreListener?
) {
    smartLayout.setOnRefreshListener(refreshListener)
    smartLayout.setOnLoadMoreListener(loadMoreListener)
}

@BindingAdapter(value = ["searchAction"])
fun bindSearch(et: EditText, callback: () -> Unit) {
    et.setOnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            callback()
            et.hideKeyboard()
        }
        true
    }
}

@BindingAdapter(value = ["gone"])
fun bindGone(v: View, gone: Boolean) {
    v.visibility = if (gone) View.GONE else View.VISIBLE
}

@BindingAdapter(value = ["invisible"])
fun bindInvisible(v: View, invisible: Boolean) {
    v.visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter(value = ["data"])
fun bindBanner(banner: BGABanner, data: List<Any>?) {
    data?.run {
        banner.setData(this, null)
    }
}

@BindingAdapter(value = ["url", "radius"])
fun bindImage(iv: ImageView, url: String?, radius: Int?) {
    if (url != null) {
        iv.displayWithUrl(url, (radius ?: 1).toFloat())
    }
}

@BindingAdapter(value = ["imageId"])
fun bindImage(iv: ImageView, id: Int?) {
    if (id != null)
        iv.setImageResource(id)
}


@BindingAdapter(value = ["select"])
fun bindSelect(v: View, select: Boolean) {
    v.isSelected = select
}

@BindingAdapter(value = ["back"])
fun bindBackAction(v: View, select: Boolean) {
    if (select) {
        v.setOnClickListener {
            (v.context as Activity).finish()
        }
    }
}


@BindingAdapter(value = ["html"])
fun bindHtml(tv: TextView, text: String) {
    tv.text = Html.fromHtml(text)
}



