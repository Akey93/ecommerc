export interface Page<Product>{
    content: Product[];
  totalElements: number;
}
export interface Product{
    nameProduct:string,
    codeProduct:string,
    price:number,
    quantity:number,
    type:string,
    url:string,
    descrizione:string,
}
export interface User{
    firstName:string,
    surname:string,
    email:string,
    money:number,
    indirizzo:string,
}
export interface ProductInCart{
    product:Product,
    quantity:number,
}
export interface DtoProduct{
    codeProduct:string,
    quantity:number,
}
export interface LoginRequest{
    email:string,
    password:string,
}

export interface PageDTO{
    nPage:number,
    dPage:number,
}


