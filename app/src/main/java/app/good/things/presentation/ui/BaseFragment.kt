package app.good.things.presentation.ui

import android.widget.Toast
import androidx.fragment.app.Fragment
import app.good.things.R

/**
 * Base Fragment
 */
internal abstract class BaseFragment: Fragment() {

    fun showInvalidCode() =
        Toast.makeText(context, R.string.invalid_code, Toast.LENGTH_SHORT).show()

    fun showError() =
        Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
}