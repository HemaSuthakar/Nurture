import { books } from '../data/content'

// Technique 1: if / else via element variable
function BookDetails() {
  let content

  if (books.length === 0) {
    content = <p className="empty-msg">No books available.</p>
  } else {
    content = books.map((book) => (
      <div className="card" key={book.id}>
        <h3>{book.title}</h3>
        <p>by {book.author}</p>
      </div>
    ))
  }

  return (
    <div>
      <h2>📚 Book Details</h2>
      {content}
    </div>
  )
}

export default BookDetails
