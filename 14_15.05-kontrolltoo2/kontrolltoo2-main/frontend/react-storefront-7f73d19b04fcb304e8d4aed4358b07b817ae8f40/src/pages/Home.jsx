import React, { useEffect, useState } from 'react'
import { Button } from '@/components/ui/button'
import { toast } from "sonner"
import { Toaster } from "@/components/ui/sonner"
import { ArrowDown, ArrowUp, Check, ShoppingBag } from "lucide-react"
import { Link } from 'react-router-dom'

function Home() {
  const [categories, setCategories] = useState([])
  const [products, setProducts] = useState([]);
  const [totalElements, setTotalElements] = useState(0)
  const [totalPages, setTotalPages] = useState(0)
  const [page, setPage] = useState(0)
  const [sort, setSort] = useState("id,asc")
  const [size, setSize] = useState(2)

  useEffect(() => {
    fetch("http://localhost:8080/categories")
      .then((response) => response.json())
      .then((json) => setCategories(json))
  }, [])

  useEffect(() => {
    fetch(`http://localhost:8080/products?page=${page}&size=${size}&sort=${sort}`)
      .then(res => res.json())
      .then(json => {
        setProducts(json.content)
        setTotalElements(json.totalElements)
        setTotalPages(json.totalPages)
      })
  }, [page, sort, size]);

  const sortAZ = () => {
    setPage(0)
    setSort("title,asc")
  }

  const sortZA = () => {
    setPage(0)
    setSort("title,desc")
  }

  const sortPriceIncreasing = () => {
    setPage(0)
    setSort("price,asc")
  }

  const sortPriceDecreasing = () => {
    setPage(0)
    setSort("price,desc")
  }

  const addToCart = (product) => {
    const cartLS = JSON.parse(localStorage.getItem("cart")) || [];
    cartLS.push(product);
    localStorage.setItem("cart", JSON.stringify(cartLS));
  }

  return (
    <div className="flex flex-col gap-6 pt-4">
      <h1 className="text-xl font-semibold">React Storefront</h1>

      <div className="flex flex-wrap gap-2">
        <Button onClick={sortAZ} variant="outline">A-Z</Button>
        <Button onClick={sortZA} variant="outline">Z-A</Button>
        <Button onClick={sortPriceIncreasing} variant="outline">Price <ArrowUp /></Button>
        <Button onClick={sortPriceDecreasing} variant="outline">Price <ArrowDown> </ArrowDown></Button>
      </div>

      <div className="flex items-center gap-2">
        <label htmlFor="category-filter">Choose category</label>
        <select onChange={(e) => setCategories(e.target.value)}>
          {categories.map(category =>
            <option key={category.id}>{category.name}</option>
          )}
        </select>
      </div>

      <div className="flex items-center gap-2">
        <label>Choose size</label>
        <select onChange={(e) => { setSize(e.target.value); setPage(0) }}>
          <option>2</option>
          <option>3</option>
        </select>
      </div>

      <div>{totalElements} items currently in stock.</div>

      {products.map((product, index) =>
        <div key={product.id} className="grid w-full grid-cols-[2rem_100px_minmax(0,1fr)_auto] items-center gap-4 py-8">
          <div className="text-right">{index + 1}.</div>
          <img className="w-[100px] h-[100px] object-cover" src={product.image} alt={product.description} />
          <div className="min-w-0">
            <div>{product.title}</div>
            <div>{product.price}€</div>
          </div>
          <div className="justify-self-end flex gap-2">
            <Button asChild variant="outline">
              <Link to={`/product/${product.id}`}>
                View product
              </Link>
            </Button>
            <Button size="icon"
              onClick={() => {
                addToCart(product)
                toast("Product has been added to the cart.", {
                  icon: <Check className="h-4 w-4" />,
                })
              }}
            >
              <ShoppingBag />
            </Button>
          </div>
        </div>
      )}

      <div className="flex gap-2">
        <Button variant="outline" onClick={() => setPage(p => p - 1)} disabled={page === 0}>
          Eelmine
        </Button>
        <span className="self-center">Lehekülg {page + 1} / {totalPages}</span>
        <Button variant="outline" onClick={() => setPage(p => p + 1)} disabled={page + 1 >= totalPages}>
          Järgmine
        </Button>
      </div>

      <Toaster position="top-center" />
    </div>
  )
}

export default Home