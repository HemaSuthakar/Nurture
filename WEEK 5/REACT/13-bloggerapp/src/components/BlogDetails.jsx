import { blogs } from '../data/content'

// Technique 2: logical && operator
function BlogDetails() {
  return (
    <div>
      <h2>📝 Blog Details</h2>
      {blogs.length === 0 && <p className="empty-msg">No blogs published yet.</p>}
      {blogs.length > 0 &&
        blogs.map((blog) => (
          <div className="card" key={blog.id}>
            <h3>{blog.title}</h3>
            <p>Published on {blog.date}</p>
          </div>
        ))}
    </div>
  )
}

export default BlogDetails
