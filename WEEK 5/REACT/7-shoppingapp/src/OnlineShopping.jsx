import React from 'react';
import Cart from './Cart.jsx';

class OnlineShopping extends React.Component {
  constructor(props) {
    super(props);
    this.cartItems = [
      { Itemname: 'Wireless Mouse', Price: 599 },
      { Itemname: 'Bluetooth Headphones', Price: 1999 },
      { Itemname: 'Laptop Stand', Price: 899 },
      { Itemname: 'USB-C Hub', Price: 1299 },
      { Itemname: 'Mechanical Keyboard', Price: 3499 },
    ];
  }

  render() {
    return (
      <div style={{ fontFamily: 'Arial, sans-serif', padding: '20px' }}>
        <h1>Online Shopping Cart</h1>
        <table border="1" cellPadding="10" style={{ borderCollapse: 'collapse' }}>
          <thead>
            <tr>
              <th>Item Name</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            {this.cartItems.map((item, index) => (
              <Cart key={index} Itemname={item.Itemname} Price={item.Price} />
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}

export default OnlineShopping;
