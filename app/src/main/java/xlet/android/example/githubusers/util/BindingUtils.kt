package xlet.android.example.githubusers.util

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtils {
    @JvmStatic
    @BindingAdapter(
        value = [
            "imageUrl"
        ],
        requireAll = false
    )
    fun setImageUrl(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView)
            .load(imageUrl)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("goneUnless")
    fun View.goneUnless(visible: Boolean) {
        isVisible = visible
    }
}