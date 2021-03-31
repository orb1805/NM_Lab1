package e.roman.nm_lab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var luSolveBtn: Button
    private lateinit var trigiagonalSolveBtn: Button
    private lateinit var mpiSolveBtn: Button
    private lateinit var seildelSolveBtn: Button
    private lateinit var jacobiSolveBtn: Button
    private lateinit var qrSolveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        luSolveBtn = findViewById(R.id.btn_lu)
        trigiagonalSolveBtn = findViewById(R.id.btn_tridiagonal)
        mpiSolveBtn = findViewById(R.id.btn_mfi)
        seildelSolveBtn = findViewById(R.id.btn_seidel)
        jacobiSolveBtn = findViewById(R.id.btn_jacobi)
        qrSolveBtn = findViewById(R.id.btn_qr)
        luSolveBtn.setOnClickListener{ startActivity(Intent(this, LuSolveActivity::class.java)) }
        trigiagonalSolveBtn.setOnClickListener{ startActivity(Intent(this, TrigiagonalSolveActivity::class.java)) }
        mpiSolveBtn.setOnClickListener{ startActivity(Intent(this, FixedPointIterationActivity::class.java)) }
        seildelSolveBtn.setOnClickListener{ startActivity(Intent(this, SeidelSolveActivity::class.java)) }
        jacobiSolveBtn.setOnClickListener { startActivity(Intent(this, JacobiSolveActivity::class.java)) }
        qrSolveBtn.setOnClickListener{ startActivity(Intent(this, QRSolveActivity::class.java)) }
    }
}