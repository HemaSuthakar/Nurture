import React from 'react';
import Counter from './Counter.jsx';
import CurrencyConvertor from './CurrencyConvertor.jsx';

function App() {
  return (
    <div style={{ fontFamily: 'Arial, sans-serif', padding: '20px' }}>
      <h1>Event Examples App</h1>
      <Counter />
      <CurrencyConvertor />
    </div>
  );
}

export default App;
