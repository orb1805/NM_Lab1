package e.roman.nm_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import kotlin.math.abs
import kotlin.math.sign
import kotlin.math.sqrt

class QRSolveActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var aEt: MutableList<MutableList<EditText>>
    private lateinit var precisionEt: EditText
    private lateinit var solveBtn: Button
    private lateinit var iterationSignTv: TextView
    private lateinit var iterationNumberTv: TextView
    private lateinit var eigenvaluesSignTv: TextView
    private lateinit var eigenvaluesTv: MutableList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r_solve)


        aEt = mutableListOf(
            mutableListOf(findViewById(R.id.et_a11), findViewById(R.id.et_a12), findViewById(R.id.et_a13)),
            mutableListOf(findViewById(R.id.et_a21), findViewById(R.id.et_a22), findViewById(R.id.et_a23)),
            mutableListOf(findViewById(R.id.et_a31), findViewById(R.id.et_a32), findViewById(R.id.et_a33)))
        precisionEt = findViewById(R.id.et_precision)
        solveBtn = findViewById(R.id.btn_solve)
        solveBtn.setOnClickListener(this)
        iterationSignTv = findViewById(R.id.tv_iterations_sign)
        iterationNumberTv = findViewById(R.id.tv_iterations_number)
        eigenvaluesSignTv = findViewById(R.id.tv_eigenvalues)
        eigenvaluesTv = mutableListOf(
            findViewById(R.id.tv_ev1),
            findViewById(R.id.tv_ev2),
            findViewById(R.id.tv_ev3)
        )

        tvVisibilityControl(TextView.INVISIBLE)
    }

    override fun onClick(p0: View?) {
        var count = 0
        var a = Matrix(3, 3, 0f)
        var aPrev = Matrix(3, 3, 0f)
        for (i in aEt.indices)
            for (j in aEt[i].indices)
                a.data[i][j] = aEt[i][j].text.toString().toFloat()
        var q: Matrix
        var v: Matrix
        var tmp: Float
        var h: Matrix
        val eps = precisionEt.text.toString().toFloat()
        while (a.triangleNorm() > eps / 1000000000000000000) {
            q = Matrix(3, 1f)
            for (i in 0 until a.data.lastIndex) {
                v = Matrix(3, 1, 0f)
                v.data[i][0] = a.data[i][i]
                tmp = 0f
                for (j in i..a.data.lastIndex)
                    tmp += a.data[j][i] * a.data[j][i]
                v.data[i][0] += sign(a.data[i][i]) * sqrt(tmp)
                for (j in i + 1..v.data.lastIndex)
                    v.data[j][0] = a.data[j][i]
                h = Matrix.difference(Matrix(3, 1f), Matrix.multiply(1 / Matrix.multiply(Matrix.transpose(v), v).data[0][0], Matrix.multiply(2f, Matrix.multiply(v, Matrix.transpose(v)))))
                q = Matrix.multiply(q, h)
                a = Matrix.multiply(h, a)
            }
            a = Matrix.multiply(a, q)
            count++
        }
        iterationNumberTv.text = count.toString()
        Log.d("qr-check", a.data.toString())
        for (i in eigenvaluesTv.indices)
            eigenvaluesTv[i].text = a.data[i][i].toString()
        if (abs(a.data[1][0]) > 0.01) {
            //eigenvaluesTv[0].text = (a.data[0][0] / 2).toString() + " + i(" + (sqrt(a.data[0][0] * a.data[0][0] - 4 * (a.data[0][0] * a.data[1][1] - a.data[0][1] * a.data[1][0])) / 2).toString() + ")"
            //eigenvaluesTv[1].text = (a.data[0][0] / 2).toString() + " - i(" + (sqrt(a.data[0][0] * a.data[0][0] - 4 * (a.data[0][0] * a.data[1][1] - a.data[0][1] * a.data[1][0])) / 2).toString() + ")"
            eigenvaluesTv[0].text = ((a.data[1][1] + a.data[0][0]) / 2).toString() + " + i(" + (sqrt(-a.data[0][0] * a.data[0][0] + 4 * (a.data[0][0] * a.data[1][1] - a.data[0][1] * a.data[1][0])) / 2).toString() + ")"
            eigenvaluesTv[1].text = ((a.data[1][1] + a.data[0][0]) / 2).toString() + " - i(" + (sqrt(-a.data[0][0] * a.data[0][0] + 4 * (a.data[0][0] * a.data[1][1] - a.data[0][1] * a.data[1][0])) / 2).toString() + ")"
        }

        if (abs(a.data[2][1]) > 0.01) {
            eigenvaluesTv[1].text = ((a.data[1][1] + a.data[2][2]) / 2).toString() + " + i(" + (sqrt(-a.data[1][1] * a.data[1][1] + 4 * (a.data[1][1] * a.data[2][2] - a.data[1][2] * a.data[2][1])) / 2).toString() + ")"
            eigenvaluesTv[2].text = ((a.data[1][1] + a.data[2][2]) / 2).toString() + " - i(" + (sqrt(-a.data[1][1] * a.data[1][1] + 4 * (a.data[1][1] * a.data[2][2] - a.data[1][2] * a.data[2][1])) / 2).toString() + ")"
        }

        tvVisibilityControl(TextView.VISIBLE)
    }

    private fun tvVisibilityControl(visibility: Int) {
        iterationNumberTv.visibility = visibility
        iterationSignTv.visibility = visibility
        eigenvaluesSignTv.visibility = visibility
        for (i in eigenvaluesTv.indices)
            eigenvaluesTv[i].visibility = visibility
    }
}