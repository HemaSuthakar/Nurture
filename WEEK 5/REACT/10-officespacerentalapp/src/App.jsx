import React from 'react';

// A single office object with Name, Rent and Address
const office = {
  name: 'Skyline Business Center',
  rent: 55000,
  address: '12 MG Road, Bengaluru',
};

// A list of office space objects to loop through
const officeSpaces = [
  { name: 'Skyline Business Center', rent: 55000, address: '12 MG Road, Bengaluru' },
  { name: 'Tech Park Tower', rent: 75000, address: '5 Rajiv Gandhi Salai, Chennai' },
  { name: 'Green Valley Offices', rent: 42000, address: '18 Anna Nagar, Chennai' },
  { name: 'Cyber Heights', rent: 68000, address: '9 Hitec City, Hyderabad' },
];

// Rent color rule: red if below 60000, green if above 60000
function rentColor(rent) {
  return rent < 60000 ? 'red' : 'green';
}

function App() {
  return (
    <div style={{ fontFamily: 'Arial, sans-serif', padding: '20px' }}>
      {/* Element created with JSX to display the page heading */}
      <h1>Office Space Rental</h1>

      {/* Attribute example: image of the office space */}
      <img
        src="https://placehold.co/400x200?text=Office+Space"
        alt="Office space"
        width="400"
        height="200"
      />

      <h2>Featured Office</h2>
      <p>Name: {office.name}</p>
      <p>
        Rent: <span style={{ color: rentColor(office.rent) }}>&#8377;{office.rent}</span>
      </p>
      <p>Address: {office.address}</p>

      <h2>All Available Office Spaces</h2>
      <ul>
        {officeSpaces.map((item, index) => (
          <li key={index}>
            <b>{item.name}</b> - Rent:{' '}
            <span style={{ color: rentColor(item.rent) }}>&#8377;{item.rent}</span> - {item.address}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
