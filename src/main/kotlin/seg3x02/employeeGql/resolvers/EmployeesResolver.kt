package seg3x02.employeeGql.resolvers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import seg3x02.employeeGql.entity.Employee
import seg3x02.employeeGql.repository.EmployeesRepository

@Component
class EmployeesResolver @Autowired constructor(
    private val employeeRepository: EmployeesRepository
) {

    fun getEmployeeById(id: String): Employee? {
        return employeeRepository.findById(id).orElse(null)
    }

    fun listEmployees(): List<Employee> {
        return employeeRepository.findAll()
    }

    fun addEmployee(name: String, dateOfBirth: String, city: String, salary: Float, gender: String?, email: String?): Employee {
        val employee = Employee(name, dateOfBirth, city, salary, gender, email)
        return employeeRepository.save(employee)
    }

    fun updateEmployee(id: String, name: String?, dateOfBirth: String?, city: String?, salary: Float?, gender: String?, email: String?): Employee? {
        val existingEmployee = employeeRepository.findById(id).orElse(null) ?: return null
        val updatedEmployee = existingEmployee.copy(
            name = name ?: existingEmployee.name,
            dateOfBirth = dateOfBirth ?: existingEmployee.dateOfBirth,
            city = city ?: existingEmployee.city,
            salary = salary ?: existingEmployee.salary,
            gender = gender ?: existingEmployee.gender,
            email = email ?: existingEmployee.email
        )
        return employeeRepository.save(updatedEmployee)
    }

    fun deleteEmployee(id: String): Boolean {
        return if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}
