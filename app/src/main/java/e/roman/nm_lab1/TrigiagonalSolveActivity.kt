package e.roman.nm_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class TrigiagonalSolveActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var solveBtn: Button

    private lateinit var xEt: MutableList<MutableList<EditText>>
    private lateinit var bEt: MutableList<EditText>
    private lateinit var xTv: MutableList<TextView>
    private lateinit var solutionTv: TextView
    private lateinit var a: MutableList<MutableList<Float>>
    private lateinit var x: MutableList<Float>
    private lateinit var p: MutableList<Float>
    private lateinit var q: MutableList<Float>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trigiagonal_solve)

        solveBtn = findViewById(R.id.btn_solve)
        solutionTv = findViewById(R.id.tv_solution)

        xEt = mutableListOf()
        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x11))
        xEt.last().add(findViewById(R.id.et_x12))

        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x21))
        xEt.last().add(findViewById(R.id.et_x22))
        xEt.last().add(findViewById(R.id.et_x23))

        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x32))
        xEt.last().add(findViewById(R.id.et_x33))
        xEt.last().add(findViewById(R.id.et_x34))

        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x43))
        xEt.last().add(findViewById(R.id.et_x44))
        xEt.last().add(findViewById(R.id.et_x45))

        xEt.add(mutableListOf())
        xEt.last().add(findViewById(R.id.et_x54))
        xEt.last().add(findViewById(R.id.et_x55))

        bEt = mutableListOf(
                findViewById(R.id.et_b1),
                findViewById(R.id.et_b2),
                findViewById(R.id.et_b3),
                findViewById(R.id.et_b4),
                findViewById(R.id.et_b5)
        )

        xTv = mutableListOf(
                findViewById(R.id.tv_x1),
                findViewById(R.id.tv_x2),
                findViewById(R.id.tv_x3),
                findViewById(R.id.tv_x4),
                findViewById(R.id.tv_x5)
        )

        tvVisibilityControl(TextView.INVISIBLE)

        solveBtn.setOnClickListener(this)
    }

    private fun tvVisibilityControl(visibility: Int) {
        solutionTv.visibility = visibility
        for (i in xTv.indices)
            xTv[i].visibility = visibility
    }

    override fun onClick(p0: View?) {
        a = mutableListOf(
                mutableListOf(0f, xEt[0][0].text.toString().toFloat(), xEt[0][1].text.toString().toFloat(), bEt[0].text.toString().toFloat()),
                mutableListOf(xEt[1][0].text.toString().toFloat(), xEt[1][1].text.toString().toFloat(), xEt[1][2].text.toString().toFloat(), bEt[1].text.toString().toFloat()),
                mutableListOf(xEt[2][0].text.toString().toFloat(), xEt[2][1].text.toString().toFloat(), xEt[2][2].text.toString().toFloat(), bEt[2].text.toString().toFloat()),
                mutableListOf(xEt[3][0].text.toString().toFloat(), xEt[3][1].text.toString().toFloat(), xEt[3][2].text.toString().toFloat(), bEt[3].text.toString().toFloat()),
                mutableListOf(xEt[4][0].text.toString().toFloat(), xEt[4][1].text.toString().toFloat(), 0f, bEt[4].text.toString().toFloat())
                )
        x = mutableListOf()
        p = mutableListOf()
        q = mutableListOf()
        p.add(-a[0][2] / a[0][1])
        q.add(a[0][3] / a[0][1])
        for (i in 1 .. xTv.lastIndex){
            p.add(-a[i][2] / (a[i][1] + a[i][0] * p[i - 1]))
            q.add((a[i][3] - a[i][0] * q[i - 1]) / (a[i][1] + a[i][0] * p[i - 1]))
        }
        //x.add((a.last()[3] - a.last()[0] * q.last() - a.last()[2]) / (a.last()[1] + a.last()[0] * p.last()))
        x.add(q.last())
        for (i in xTv.lastIndex - 1 downTo 0)
            x.add(p[i] * x.last() + q[i])
        var str: String
        for (i in xTv.indices) {
            str = x[xTv.lastIndex - i].toString().take(4)
            while (str.length < 4)
                str = " $str"
            xTv[i].text = str
        }
        tvVisibilityControl(TextView.VISIBLE)
    }
}