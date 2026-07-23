import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App.jsx';

// Note: <App /> above is JSX syntactic sugar for:
// React.createElement(App, null)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(<App />);
