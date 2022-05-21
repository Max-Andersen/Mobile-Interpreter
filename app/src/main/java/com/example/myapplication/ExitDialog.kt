import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R


class ExitDialog constructor(
    context: Context,
    burgerOpen: ImageButton,
    activity: MainActivity
) {

    var ad: android.app.AlertDialog.Builder? = null

    init {
        val layoutInflater = LayoutInflater.from(context)
        val promptView: View = layoutInflater.inflate(R.layout.exit_layout, null)
        ad = android.app.AlertDialog.Builder(context)
        ad!!.setTitle("Выйти? :(")
        ad!!.setView(promptView)
        ad!!.setIcon(R.drawable.cyber)
        ad!!.setPositiveButton("Вернуться") { _, _ ->
            Toast.makeText(context, "Вы сделали правильный выбор", Toast.LENGTH_LONG).show()
        }
        ad!!.setNegativeButton("Выйти из приложения") { _, _ ->
            burgerOpen.visibility = View.VISIBLE
            activity.finish()
        }
        ad!!.setCancelable(true)
        ad!!.setOnCancelListener {
            Toast.makeText(
                context,
                "Вы ничего не выбрали",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun onClick() {
        ad?.show()
    }
}