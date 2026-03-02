//const studentForm = document.getElementById("studentForm");
//const assignForm = document.getElementById("assignForm");
//const loadStudentsBtn = document.getElementById("loadStudents");
//
//const studentMessage = document.getElementById("studentMessage");
//const assignMessage = document.getElementById("assignMessage");
//const studentsList = document.getElementById("studentsList");
//
//const BASE_URL = "http://localhost:8080"; // change if needed
//
//// Add Student
//studentForm.addEventListener("submit", async (e) => {
//    e.preventDefault();
//
//    const student = {
//        name: document.getElementById("name").value,
//        email: document.getElementById("email").value,
//        age: document.getElementById("age").value
//    };
//
//    const response = await fetch(`${BASE_URL}/students`, {
//        method: "POST",
//        headers: {
//            "Content-Type": "application/json"
//        },
//        body: JSON.stringify(student)
//    });
//
//    const data = await response.json();
//    studentMessage.innerText = data.message;
//});
//
//// Assign Course
//assignForm.addEventListener("submit", async (e) => {
//    e.preventDefault();
//
//    const studentId = document.getElementById("studentId").value;
//    const courseId = document.getElementById("courseId").value;
//
//    const response = await fetch(`${BASE_URL}/students/${studentId}/courses/${courseId}`, {
//        method: "POST"
//    });
//
//    const data = await response.json();
//    assignMessage.innerText = data.message;
//});
//
//// Load Students
//loadStudentsBtn.addEventListener("click", async () => {
//
//    const response = await fetch(`${BASE_URL}/students`);
//    const data = await response.json();
//
//    studentsList.innerHTML = "";
//
//    data.data.forEach(student => {
//        const div = document.createElement("div");
//        div.style.marginBottom = "10px";
//        div.innerHTML = `
//            <strong>${student.name}</strong> (ID: ${student.id}) <br>
//            Email: ${student.email} <br>
//            Age: ${student.age} <br>
//            Courses: ${
//                student.courses.map(c => c.title).join(", ")
//            }
//            <hr>
//        `;
//        studentsList.appendChild(div);
//    });
//});