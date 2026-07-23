import { courses } from '../data/content'

// Technique 3: ternary operator (renders empty-state, since courses list is intentionally empty)
function CourseDetails() {
  return (
    <div>
      <h2>🎓 Course Details</h2>
      {courses.length > 0 ? (
        courses.map((course) => (
          <div className="card" key={course.id}>
            <h3>{course.title}</h3>
          </div>
        ))
      ) : (
        <p className="empty-msg">No courses added yet. Check back soon!</p>
      )}
    </div>
  )
}

export default CourseDetails
