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
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

class JacobiSolveActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var aEt: MutableList<MutableList<EditText>>
    private lateinit var precisionEt: EditText
    private lateinit var solveBtn: Button
    private lateinit var iterationSignTv: TextView
    private lateinit var iterationNumberTv: TextView
    private lateinit var eigenvaluesSignTv: TextView
    private lateinit var eigenvaluesTv: MutableList<TextView>
    private lateinit var eigenvectorsSignTv: TextView
    private lateinit var eigenvector1Tv: MutableList<TextView>
    private lateinit var eigenvector2Tv: MutableList<TextView>
    private lateinit var eigenvector3Tv: MutableList<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jacobi_solve)

        aEt = mutableListOf(
            mutableListOf(findViewById(R.id.et_a11), findViewById(R.id.et_a12), findViewById(R.id.et_a13)),
            mutableListOf(findViewById(R.id.et_a21), findViewById(R.id.et_a22), findViewById(R.id.et_a23)),
            mutableListOf(findViewById(R.id.et_a31), findViewById(R.id.et_a32), findViewById(R.id.et_a33)))
        var flag = true
        aEt[0][1].addTextChangedListener {
            if (flag) {
                flag = false
                aEt[1][0].text = aEt[0][1].text
                flag = true
            }
        }
        aEt[0][2].addTextChangedListener {
            if (flag) {
                flag = false
                aEt[2][0].text = aEt[0][2].text
                flag = true
            }
        }
        aEt[1][2].addTextChangedListener {
            if (flag) {
                flag = false
                aEt[2][1].text = aEt[1][2].text
                flag = true
            }
        }
        aEt[1][0].addTextChangedListener {
            if (flag) {
                flag = false
                aEt[0][1].text = aEt[1][0].text
                flag = true
            }
        }
        aEt[2][0].addTextChangedListener {
            if (flag) {
                flag = false
                aEt[0][2].text = aEt[2][0].text
                flag = true
            }
        }
        aEt[2][1].addTextChangedListener {
            if (flag) {
                flag = false
                aEt[1][2].text = aEt[2][1].text
                flag = true
            }
        }
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
        eigenvectorsSignTv = findViewById(R.id.tv_eigenvectors)
        eigenvector1Tv = mutableListOf(
            findViewById(R.id.tv_ev11),
            findViewById(R.id.tv_ev12),
            findViewById(R.id.tv_ev13)
            )
        eigenvector2Tv = mutableListOf(
            findViewById(R.id.tv_ev21),
            findViewById(R.id.tv_ev22),
            findViewById(R.id.tv_ev23)
        )
        eigenvector3Tv = mutableListOf(
            findViewById(R.id.tv_ev31),
            findViewById(R.id.tv_ev32),
            findViewById(R.id.tv_ev33)
        )

        tvVisibilityControl(TextView.INVISIBLE)
    }

    override fun onClick(p0: View?) {
        var count = 0
        var a = Matrix(3, 3, 0f)
        for (i in aEt.indices)
            for (j in aEt[i].indices)
                a.data[i][j] = aEt[i][j].text.toString().toFloat()
        var finalU = Matrix(3, 1f)
        val eps = precisionEt.text.toString().toFloat()
        var maxElement: Float
        var l: Int
        var k: Int
        var phi: Float
        var u: Matrix
        while (a.symmetricalNorm() > eps) {
            maxElement = 0f
            l = 0
            k = 0
            for (i in a.data.indices)
                for (j in i + 1 .. a.data[i].lastIndex)
                    if (abs(a.data[i][j]) > maxElement) {
                        maxElement = abs(a.data[i][j])
                        l = i
                        k = j
                    }
            u = Matrix(3, 1f)
            phi = atan(2 * a.data[l][k] / (a.data[l][l] - a.data[k][k])) / 2
            u.data[l][l] = cos(phi)
            u.data[k][k] = cos(phi)
            u.data[l][k] = -sin(phi)
            u.data[k][l] = sin(phi)
            a = Matrix.multiply(Matrix.transpose(u), a)
            a = Matrix.multiply(a, u)
            finalU = Matrix.multiply(finalU, u)
            count++
        }
        for (i in a.data.indices)
            eigenvaluesTv[i].text = a.data[i][i].toString()
        iterationNumberTv.text = count.toString()
        for (i in finalU.data.indices)
            eigenvector1Tv[i].text = finalU.data[i][0].toString()
        for (i in finalU.data.indices)
            eigenvector2Tv[i].text = finalU.data[i][1].toString()
        for (i in finalU.data.indices)
            eigenvector3Tv[i].text = finalU.data[i][2].toString()

        tvVisibilityControl(TextView.VISIBLE)
    }

    private fun tvVisibilityControl(visibility: Int) {
        iterationNumberTv.visibility = visibility
        iterationSignTv.visibility = visibility
        eigenvaluesSignTv.visibility = visibility
        for (i in eigenvaluesTv.indices) {
            eigenvaluesTv[i].visibility = visibility
            eigenvector1Tv[i].visibility = visibility
            eigenvector2Tv[i].visibility = visibility
            eigenvector3Tv[i].visibility = visibility
        }
        eigenvectorsSignTv.visibility = visibility
    }
}