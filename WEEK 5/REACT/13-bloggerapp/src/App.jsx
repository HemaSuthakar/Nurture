import { useState } from 'react'
import BookDetails from './components/BookDetails'
import BlogDetails from './components/BlogDetails'
import CourseDetails from './components/CourseDetails'

function App() {
  const [activeTab, setActiveTab] = useState('books')

  // Technique 4: switch-case for selecting which component to render
  const renderActiveComponent = () => {
    switch (activeTab) {
      case 'books':
        return <BookDetails />
      case 'blogs':
        return <BlogDetails />
      case 'courses':
        return <CourseDetails />
      default:
        return null
    }
  }

  return (
    <div>
      <div className="header">
        <h1>Blogger App</h1>
      </div>

      <div className="tabs">
        <button
          className={activeTab === 'books' ? 'active' : ''}
          onClick={() => setActiveTab('books')}
        >
          Books
        </button>
        <button
          className={activeTab === 'blogs' ? 'active' : ''}
          onClick={() => setActiveTab('blogs')}
        >
          Blogs
        </button>
        <button
          className={activeTab === 'courses' ? 'active' : ''}
          onClick={() => setActiveTab('courses')}
        >
          Courses
        </button>
      </div>

      <div className="container">{renderActiveComponent()}</div>
    </div>
  )
}

export default App
