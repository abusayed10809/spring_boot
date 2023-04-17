import { React, useState } from "react";
import ProductService from "../services/ProductService";
import { useNavigate } from "react-router-dom";

const AddProduct = () => {
  const navigate = useNavigate();

  const [product, setProduct] = useState({
    id: "",
    name: "",
    price: 0.0,
    quantity: 0,
    imageUrl: "",
  });

  const handleChange = (e) => {
    const value = e.target.value;
    setProduct({ ...product, [e.target.name]: value });
  };

  const saveProduct = (e) => {
    e.preventDefault();
    ProductService.saveProduct(product)
      .then((response) => {
        console.log(response);
        navigate("/productList");
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setProduct({
      id: "",
      name: "",
      price: 0.0,
      quantity: 0,
      imageUrl: "",
    });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Product</h1>
        </div>

        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Product Name
          </label>
          <input
            type="text"
            name="name"
            value={product.name}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"
          ></input>
        </div>

        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Price
          </label>
          <input
            type="number"
            name="price"
            value={product.price}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"
          ></input>
        </div>

        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Quantity
          </label>
          <input
            type="number"
            name="quantity"
            value={product.quantity}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"
          ></input>
        </div>

        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Image Url
          </label>
          <input
            type="text"
            name="imageUrl"
            value={product.imageUrl}
            onChange={(e) => handleChange(e)}
            className="h-10 w-96 border mt-2 px-2 py-2"
          ></input>
        </div>

        <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
          <button
            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6"
            onClick={saveProduct}
          >
            Save
          </button>

          <button
            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6"
            onClick={reset}
          >
            Clear
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddProduct;
