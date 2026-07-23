import React from 'react';

class Cart extends React.Component {
  static defaultProps = {
    Itemname: 'Unnamed Item',
    Price: 0,
  };

  render() {
    return (
      <tr>
        <td>{this.props.Itemname}</td>
        <td>&#8377;{this.props.Price}</td>
      </tr>
    );
  }
}

export default Cart;
