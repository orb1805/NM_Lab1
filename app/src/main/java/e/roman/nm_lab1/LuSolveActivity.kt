package e.roman.nm_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.abs
import kotlin.math.log

class LuSolveActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var solveBtn: Button

    private lateinit var xEt: MutableList<MutableList<EditText>>
    private lateinit var bEt: MutableList<EditText>
    private lateinit var lTv: MutableList<MutableList<TextView>>
    private lateinit var uTv: MutableList<MutableList<TextView>>
    private lateinit var invTv: MutableList<MutableList<TextView>>
    private lateinit var multTv: MutableList<MutableList<TextView>>
    private lateinit var xTv: MutableList<TextView>
    private lateinit var detTv: TextView
    private lateinit var lMatrixTv: TextView
    private lateinit var uMatrixTv: TextView
    private lateinit var invMatrixTv: TextView
    private lateinit var multMatrixTv: TextView
    private lateinit var solutionTv: TextView
    private lateinit var determinantTv: TextView

    private lateinit var a: Matrix

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lu_solve)

        solveBtn = findViewById(R.id.btn_solve)
        detTv = findViewById(R.id.tv_det)
        lMatrixTv = findViewById(R.id.tv_l_matrix)
        uMatrixTv = findViewById(R.id.tv_u_matrix)
        invMatrixTv = findViewById(R.id.tv_inv_a)
        multMatrixTv = findViewById(R.id.tv_inv_mult)
        solutionTv = findViewById(R.id.tv_solution)
        determinantTv = findViewById(R.id.tv_determinant)

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

        lTv = mutableListOf()
        lTv.add(mutableListOf())
        lTv.last().add(findViewById(R.id.tv_l11))
        lTv.last().add(findViewById(R.id.tv_l12))
        lTv.last().add(findViewById(R.id.tv_l13))
        lTv.last().add(findViewById(R.id.tv_l14))

        lTv.add(mutableListOf())
        lTv.last().add(findViewById(R.id.tv_l21))
        lTv.last().add(findViewById(R.id.tv_l22))
        lTv.last().add(findViewById(R.id.tv_l23))
        lTv.last().add(findViewById(R.id.tv_l24))

        lTv.add(mutableListOf())
        lTv.last().add(findViewById(R.id.tv_l31))
        lTv.last().add(findViewById(R.id.tv_l32))
        lTv.last().add(findViewById(R.id.tv_l33))
        lTv.last().add(findViewById(R.id.tv_l34))

        lTv.add(mutableListOf())
        lTv.last().add(findViewById(R.id.tv_l41))
        lTv.last().add(findViewById(R.id.tv_l42))
        lTv.last().add(findViewById(R.id.tv_l43))
        lTv.last().add(findViewById(R.id.tv_l44))

        uTv = mutableListOf()
        uTv.add(mutableListOf())
        uTv.last().add(findViewById(R.id.tv_r11))
        uTv.last().add(findViewById(R.id.tv_r12))
        uTv.last().add(findViewById(R.id.tv_r13))
        uTv.last().add(findViewById(R.id.tv_r14))

        uTv.add(mutableListOf())
        uTv.last().add(findViewById(R.id.tv_r21))
        uTv.last().add(findViewById(R.id.tv_r22))
        uTv.last().add(findViewById(R.id.tv_r23))
        uTv.last().add(findViewById(R.id.tv_r24))

        uTv.add(mutableListOf())
        uTv.last().add(findViewById(R.id.tv_r31))
        uTv.last().add(findViewById(R.id.tv_r32))
        uTv.last().add(findViewById(R.id.tv_r33))
        uTv.last().add(findViewById(R.id.tv_r34))

        uTv.add(mutableListOf())
        uTv.last().add(findViewById(R.id.tv_r41))
        uTv.last().add(findViewById(R.id.tv_r42))
        uTv.last().add(findViewById(R.id.tv_r43))
        uTv.last().add(findViewById(R.id.tv_r44))

        invTv = mutableListOf()
        invTv.add(mutableListOf())
        invTv.last().add(findViewById(R.id.tv_inv11))
        invTv.last().add(findViewById(R.id.tv_inv12))
        invTv.last().add(findViewById(R.id.tv_inv13))
        invTv.last().add(findViewById(R.id.tv_inv14))

        invTv.add(mutableListOf())
        invTv.last().add(findViewById(R.id.tv_inv21))
        invTv.last().add(findViewById(R.id.tv_inv22))
        invTv.last().add(findViewById(R.id.tv_inv23))
        invTv.last().add(findViewById(R.id.tv_inv24))

        invTv.add(mutableListOf())
        invTv.last().add(findViewById(R.id.tv_inv31))
        invTv.last().add(findViewById(R.id.tv_inv32))
        invTv.last().add(findViewById(R.id.tv_inv33))
        invTv.last().add(findViewById(R.id.tv_inv34))

        invTv.add(mutableListOf())
        invTv.last().add(findViewById(R.id.tv_inv41))
        invTv.last().add(findViewById(R.id.tv_inv42))
        invTv.last().add(findViewById(R.id.tv_inv43))
        invTv.last().add(findViewById(R.id.tv_inv44))

        multTv = mutableListOf()
        multTv.add(mutableListOf())
        multTv.last().add(findViewById(R.id.tv_mult11))
        multTv.last().add(findViewById(R.id.tv_mult12))
        multTv.last().add(findViewById(R.id.tv_mult13))
        multTv.last().add(findViewById(R.id.tv_mult14))

        multTv.add(mutableListOf())
        multTv.last().add(findViewById(R.id.tv_mult21))
        multTv.last().add(findViewById(R.id.tv_mult22))
        multTv.last().add(findViewById(R.id.tv_mult23))
        multTv.last().add(findViewById(R.id.tv_mult24))

        multTv.add(mutableListOf())
        multTv.last().add(findViewById(R.id.tv_mult31))
        multTv.last().add(findViewById(R.id.tv_mult32))
        multTv.last().add(findViewById(R.id.tv_mult33))
        multTv.last().add(findViewById(R.id.tv_mult34))

        multTv.add(mutableListOf())
        multTv.last().add(findViewById(R.id.tv_mult41))
        multTv.last().add(findViewById(R.id.tv_mult42))
        multTv.last().add(findViewById(R.id.tv_mult43))
        multTv.last().add(findViewById(R.id.tv_mult44))

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
        /*var l = Matrix(
            mutableListOf(
                mutableListOf(1f, 0f, 0f, 0f),
                mutableListOf(0f, 1f, 0f, 0f),
                mutableListOf(0f, 0f, 1f, 0f),
                mutableListOf(0f, 0f, 0f, 1f)
            )
        )*/
        var l = Matrix(4, 1f)
        val u = Matrix(4, 4, 0f)
        var m: Matrix
        a = Matrix(4, 4, 0f)
        for (i in xEt.indices) {
            for (j in xEt.indices) {
                a.data[i][j] = xEt[i][j].text.toString().toFloat()
                u.data[i][j] = xEt[i][j].text.toString().toFloat()
            }
        }
        /*var maxInd = 0
        //var maxElem = abs(u.data[0][0])
        var maxElem = u.data[0][0]
        for (j in 1 until u.data.size)
            if (abs(u.data[j][0]) > maxElem){
                maxElem = abs(u.data[j][0])
                //maxInd = j
                if (j != 0) {
                    u.changeRows(0, j)
                    var temp = b[0]
                    b[0] = b[j]
                    b[j] = temp
                    /*b[i] += b[maxInd]
                    b[maxInd] = b[i] - b[maxInd]
                    b[i] -= b[maxInd]*/
                }
            }*/
        for (i in a.data.indices) {
            m = Matrix(4, 1f)
            for (j in i + 1 until a.data.size) {
                m.data[j][i] = u.data[j][i] / u.data[i][i]
                for (k in a.data.indices) {
                    u.data[j][k] -= m.data[j][i] * u.data[i][k]
                }
            }
            //l.multiply(m.data)
            l = Matrix.multiply(l, m)

        }

        var lStr: String
        var rStr: String
        for (i in a.data.indices) {
            for (j in a.data.indices) {
                lStr = l.data[i][j].toString().take(4)
                rStr = u.data[i][j].toString().take(4)
                while (lStr.length < 4)
                    lStr = " $lStr"
                while (rStr.length < 4)
                    rStr = " $rStr"
                lTv[i][j].text = lStr
                uTv[i][j].text = rStr
            }
        }

        val b = mutableListOf<Float>()
        for (i in bEt.indices)
            b.add(bEt[i].text.toString().toFloat())
        val z = solveZ(b, l)

        val x = solveX(z, u)

        for (i in xTv.indices)
            xTv[i].text = x[i].toString()

        var det = 1f
        for (i in u.data.indices)
            det *= u.data[i][i]
        detTv.text = det.toString()

        val uInv = Matrix(4, 1f)
        val lInv = Matrix(4, 1f)

        var uTmp: MutableList<Float>
        for (i in uInv.data.indices) {
            var eTmp = mutableListOf(0f, 0f, 0f, 0f)
            eTmp[i] = 1f
            uTmp = solveX(eTmp, u)
            for (j in uInv.data.indices)
                uInv.data[j][i] = uTmp[j]
        }

        var lTmp: MutableList<Float>
        for (i in lInv.data.indices) {
            var eTmp = mutableListOf(0f, 0f, 0f, 0f)
            eTmp[i] = 1f
            lTmp = solveZ(eTmp, l)
            for (j in uInv.data.indices)
                lInv.data[j][i] = lTmp[j]
        }

        var aInv = Matrix(
            mutableListOf(
                mutableListOf(1f, 0f, 0f, 0f),
                mutableListOf(0f, 1f, 0f, 0f),
                mutableListOf(0f, 0f, 1f, 0f),
                mutableListOf(0f, 0f, 0f, 1f)
            )
        )
        aInv = Matrix.multiply(aInv, uInv)
        //aInv.multiply(uInv.data)
        aInv = Matrix.multiply(aInv, lInv)
        //aInv.multiply(lInv.data)
        a = Matrix.multiply(a, aInv)
        //a.multiply(aInv.data)

        Log.d("check-LU", aInv.data.toString())
        Log.d("check-LU", a.data.toString())

        var str: String
        for (i in aInv.data.indices)
            for (j in aInv.data.indices) {
                if (abs(aInv.data[i][j]) < 0.001)
                    invTv[i][j].text = " 0.0"
                else {
                    str = aInv.data[i][j].toString().take(4)
                    while (str.length < 4)
                        str = " $str"
                    invTv[i][j].text = str
                }
                if (abs(a.data[i][j]) < 0.001)
                    multTv[i][j].text = " 0.0"
                else {
                    str = a.data[i][j].toString().take(4)
                    while (str.length < 4)
                        str = " $str"
                    multTv[i][j].text = str
                }
            }

        /*Log.d("check-LU", aInv.data.toString())
        Log.d("check-LU", a.data.toString())*/

        tvVisibilityControl(TextView.VISIBLE)
    }

    private fun solveX(z: MutableList<Float>, u: Matrix): MutableList<Float> {
        val x = mutableListOf<Float>()
        for (i in z.indices)
            x.add(0f)
        x[x.lastIndex] = z.last() / u.data.last().last()
        for (i in x.lastIndex - 1 downTo 0) {
            x[i] = z[i]
            for (j in i + 1..x.lastIndex)
                x[i] -= u.data[i][j] * x[j]
            x[i] /= u.data[i][i]
        }
        return x
    }

    private fun solveZ(b: MutableList<Float>, l: Matrix): MutableList<Float> {
        val z = mutableListOf<Float>()
        z.add(b[0])
        for (i in 1 until bEt.size) {
            z.add(b[i])
            for (j in 0 until i)
                z[z.lastIndex] -= l.data[i][j] * z[j]
        }
        return z
    }

    private fun tvVisibilityControl(visibility: Int) {

        detTv.visibility = visibility
        lMatrixTv.visibility = visibility
        uMatrixTv.visibility = visibility
        solutionTv.visibility = visibility
        determinantTv.visibility = visibility
        invMatrixTv.visibility = visibility
        multMatrixTv.visibility = visibility
        for (i in lTv.indices) {
            xTv[i].visibility = visibility
            for (j in lTv.indices) {
                lTv[i][j].visibility = visibility
                uTv[i][j].visibility = visibility
                invTv[i][j].visibility = visibility
                multTv[i][j].visibility = visibility
            }
        }
    }
}