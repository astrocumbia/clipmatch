package cc.hubble.clipmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import cc.hubble.clipmatch.data.RandomUsersRepositoryImp
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val x  = RandomUsersRepositoryImp().getUsers()
            Log.e("TAAG", x.toString())
        }
    }
}