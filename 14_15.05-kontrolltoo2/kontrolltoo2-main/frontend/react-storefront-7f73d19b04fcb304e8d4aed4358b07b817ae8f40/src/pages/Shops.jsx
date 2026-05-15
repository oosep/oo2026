import React, { useEffect, useState } from 'react'

function Shops() {
  const [shops, setShops] = useState([])

  useEffect(() => {
    fetch("http://localhost:8080/shops")
      .then(res => res.json())
      .then(json => setShops(json))
  }, [])

  return (
    <div className="flex flex-col gap-6 pt-4">
      <h1 className="text-xl font-semibold">Our shops</h1>
      <table className="border-collapse border border-gray-300 w-full">
        <thead>
          <tr>
            <th className="border border-gray-300 p-2">ID</th>
            <th className="border border-gray-300 p-2">Type</th>
            <th className="border border-gray-300 p-2">Description</th>
          </tr>
        </thead>
        <tbody>
          {shops.map(shop => (
            <tr key={shop.typeID}>
              <td className="border border-gray-300 p-2">{shop.typeID}</td>
              <td className="border border-gray-300 p-2">{shop.type}</td>
              <td className="border border-gray-300 p-2">{shop.description}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}

export default Shops