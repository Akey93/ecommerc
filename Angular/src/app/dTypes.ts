export interface Product{
    nameProduct:string,
    codeProduct:string,
    price:number,
    quantity:number,
    type:string,
    url:String,
    descrizione:String,
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


