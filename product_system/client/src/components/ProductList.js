import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ProductService from "../services/ProductService";
import Product from "./Product";

const ProductList = () => {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);
  const [products, setProducts] = useState(null);

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await ProductService.getProducts();
      setProducts(response.data);
    } catch (error) {
      console.log(error);
    }
    setLoading(false);
  };

  useEffect(() => {
    fetchData();
  }, []);

  const deleteProduct = (e, id) => {
    e.preventDefault();
    ProductService.deleteProduct(id).then((res) => {
      if (products) {
        setProducts((prevElement) => {
          return prevElement.filter((product) => product.id !== id);
        });
      }
    });
  };

  return (
    <div className="container mx-auto my-8">
      <div className="h-12">
        <button
          onClick={() => navigate("/addProduct")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold"
        >
          Add Product
        </button>
      </div>

      <div className="flex shadow border-b">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Name
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Price
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Quantity
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Image URL
              </th>
              <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Actions
              </th>
            </tr>
          </thead>

          {!loading && (
            <tbody className="bg-white">
              {products.map((product) => (
                <Product
                  product={product}
                  deleteProduct={deleteProduct}
                  key={product.id}
                ></Product>
              ))}
            </tbody>
          )}
        </table>
      </div>
    </div>
  );
};

export default ProductList;
