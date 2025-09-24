import { Category } from "./Category";

export interface Product {
  id?: number;
  productName: string;
  price: number;
  categoryId: number;
  category?: Category;
}
