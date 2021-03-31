package e.roman.nm_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FixedPointIterationActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var solveBtn: Button
    private lateinit var xEt: MutableList<MutableList<EditText>>
    private lateinit var bEt: MutableList<EditText>
    private lateinit var xTv: MutableList<TextView>
    private lateinit var solutionTv: TextView
    private lateinit var precisionEt: EditText
    private lateinit var iterationNumberTv: TextView
    private lateinit var iterationSignTv: TextView
    private lateinit var a: Matrix
    private lateinit var b: Matrix
    private lateinit var xPrev: Matrix
    private lateinit var x: Matrix

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixed_point_iteration)

        solveBtn = findViewById(R.id.btn_solve)
        solutionTv = findViewById(R.id.tv_solution)
        precisionEt = findViewById(R.id.et_precision)
        iterationNumberTv = findViewById(R.id.tv_iterations_number)
        iterationSignTv = findViewById(R.id.tv_iterations_sign)

        xEt = mutableListOf()
        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x11))
        xEt.last().add(findViewById(R.id.et_x12))
        xEt.last().add(findViewById(R.id.et_x13))
        xEt.last().add(findViewById(R.id.et_x14))

        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x21))
        xEt.last().add(findViewById(R.id.et_x22))
        xEt.last().add(findViewById(R.id.et_x23))
        xEt.last().add(findViewById(R.id.et_x24))

        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x31))
        xEt.last().add(findViewById(R.id.et_x32))
        xEt.last().add(findViewById(R.id.et_x33))
        xEt.last().add(findViewById(R.id.et_x34))

        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x41))
        xEt.last().add(findViewById(R.id.et_x42))
        xEt.last().add(findViewById(R.id.et_x43))
        xEt.last().add(findViewById(R.id.et_x44))

        bEt = mutableListOf(
            findViewById(R.id.et_b1),
            findViewById(R.id.et_b2),
            findViewById(R.id.et_b3),
            findViewById(R.id.et_b4)
        )

        xTv = mutableListOf(
            findViewById(R.id.tv_x1),
            findViewById(R.id.tv_x2),
            findViewById(R.id.tv_x3),
            findViewById(R.id.tv_x4)
        )

        tvVisibilityControl(TextView.INVISIBLE)

        solveBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        a = Matrix(4, 4, 0f)
        for (i in xEt.indices) {
            for (j in xEt.indices) {
                a.data[i][j] = xEt[i][j].text.toString().toFloat()
            }
        }
        b = Matrix(mutableListOf(
            mutableListOf(bEt[0].text.toString().toFloat()),
            mutableListOf(bEt[1].text.toString().toFloat()),
            mutableListOf(bEt[2].text.toString().toFloat()),
            mutableListOf(bEt[3].text.toString().toFloat())))
        val alpha = Matrix(4, 4, 0f)
        for (i in alpha.data.indices)
            for (j in alpha.data[i].indices)
                alpha.data[i][j] = -a.data[i][j] / (a.data[i][i])
        for (i in alpha.data.indices)
            alpha.data[i][i] = 0f
        //Log.d("tri-check", alpha.data.toString())
        val beta = Matrix(mutableListOf(
            mutableListOf(b.data[0][0] / a.data[0][0]),
            mutableListOf(b.data[1][0] / a.data[1][1]),
            mutableListOf(b.data[2][0] / a.data[2][2]),
            mutableListOf(b.data[3][0] / a.data[3][3])
            ))
        //Log.d("tri-check", beta.data.toString())
        val eps = precisionEt.text.toString().toFloat()
        xPrev = Matrix(4, 1, 1f)
        Log.d("tri-check", xPrev.data.toString())
        x = Matrix.sum(Matrix.multiply(alpha, xPrev), beta)
        Log.d("tri-check", x.data.toString())
        val alphaNorm = alpha.infNorm()
        val coefficient = alphaNorm / (1 - alphaNorm)
        var count  = 0
        while (coefficient * Matrix.difference(x, xPrev).infNorm() > eps) {
            xPrev = x
            x = Matrix.multiply(alpha, x)
            x = Matrix.sum(x, beta)
            count++
        }
        for (i in xTv.indices)
            xTv[i].text = x.data[i][0].toString()
        iterationNumberTv.text = count.toString()
        tvVisibilityControl(TextView.VISIBLE)
    }

    private fun tvVisibilityControl(visibility: Int) {
        iterationNumberTv.visibility = visibility
        iterationSignTv.visibility = visibility
        solutionTv.visibility = visibility
        for (i in xTv.indices) {
            xTv[i].visibility = visibility
        }
    }
}