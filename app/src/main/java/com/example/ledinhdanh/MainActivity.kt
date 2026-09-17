package com.example.ledinhdanh
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ledinhdanh.databinding.ActivityMainBinding
import com.example.ledinhdanh.model.Student
import com.example.ledinhdanh.utils.toAcademicRanking
import com.example.ledinhdanh.utils.toast
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var currentStudent = Student(
        id = "2415053122308",
        name = "Lê Đình Danh",
        className = "DD2026",
        email = "danh@ute.udn.vn",
        gpa = 3.8
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindStudentData(currentStudent)
        binding.btnUpdateGpa.setOnClickListener { updateGpa() }
    }

    private fun updateGpa() {
        val newGpa = binding.edtNewGpa.text
            ?.toString()
            ?.trim()
            ?.toDoubleOrNull()

        if (newGpa == null || newGpa !in 0.0..4.0) {
            binding.inputGpa.error = getString(R.string.invalid_gpa_error)
            toast(getString(R.string.invalid_gpa_message))
            return
        }

        binding.inputGpa.error = null
        currentStudent = currentStudent.copy(gpa = newGpa)
        bindStudentData(currentStudent)
        toast(getString(R.string.update_success_message))
    }

    private fun bindStudentData(student: Student) {
        with(binding) {
            tvName.text = student.name
            tvStudentId.text = getString(
                R.string.student_id_class,
                student.id,
                student.className
            )
            tvEmail.text = student.email
            tvGpaBadge.text = getString(
                R.string.gpa_badge,
                student.gpa.toString(),
                student.gpa.toAcademicRanking()
            )
            edtNewGpa.setText(student.gpa.toString())
        }
    }
}
