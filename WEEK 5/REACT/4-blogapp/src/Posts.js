import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            hasError: false
        };
    }

    loadPosts() {
        fetch('https://jsonplaceholder.typicode.com/posts')
            .then(response => response.json())
            .then(data => {
                const posts = data.map(
                    item => new Post(item.userId, item.id, item.title, item.body)
                );
                this.setState({ posts: posts });
            })
            .catch(error => {
                this.setState({ hasError: true });
            });
    }

    componentDidMount() {
        this.loadPosts();
    }

    componentDidCatch(error, info) {
        this.setState({ hasError: true });
        alert('Something went wrong while loading posts: ' + error.message);
    }

    render() {
        if (this.state.hasError) {
            return <p>Unable to load posts at this time.</p>;
        }

        return (
            <div>
                {this.state.posts.map(post => (
                    <div key={post.id}>
                        <h2>{post.title}</h2>
                        <p>{post.body}</p>
                    </div>
                ))}
            </div>
        );
    }
}

export default Posts;
