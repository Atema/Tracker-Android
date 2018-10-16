package nl.expeditiegrensland.tracker.helpers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import nl.expeditiegrensland.tracker.Constants
import nl.expeditiegrensland.tracker.ExpeditieSelectActivity
import nl.expeditiegrensland.tracker.LoginActivity
import nl.expeditiegrensland.tracker.MainActivity

object ActivityHelper {
    private fun openActivity(context: Context, activityClass: Class<*>, bundle: Bundle? = null) {
        val intent = Intent(context, activityClass)
        bundle?.let {
            intent.putExtras(it)
        }
        context.startActivity(intent)
    }

    fun openMain(context: Context): Unit =
            openActivity(context, MainActivity::class.java)

    fun openExpeditieSelect(context: Context, expedities: String? = null) {
        val bundle = expedities?.let{
            val bundle = Bundle()
            bundle.putString(Constants.BUNDLE_KEY_EXPEDITIES, it)
            bundle
        }
        openActivity(context, ExpeditieSelectActivity::class.java, bundle)
    }

    fun openLogin(context: Context): Unit =
            openActivity(context, LoginActivity::class.java)
}