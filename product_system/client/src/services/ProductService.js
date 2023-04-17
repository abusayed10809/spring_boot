import axios from "axios";

const PRODUCT_API_BASE_URL = "http://localhost:8090/api/v1/";

class ProductService {
  saveProduct(product) {
    return axios.post(PRODUCT_API_BASE_URL + "add-product", product);
  }

  getProducts() {
    return axios.get(PRODUCT_API_BASE_URL + "products");
  }

  deleteProduct(id) {
    return axios.delete(PRODUCT_API_BASE_URL + "product/" + id);
  }

  getProductById(id) {
    return axios.get(PRODUCT_API_BASE_URL + "product/" + id);
  }

  updateProduct(product, id) {
    return axios.put(PRODUCT_API_BASE_URL + "product/" + id, product);
  }
}

export default new ProductService();
