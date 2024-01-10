export const API={

    baseUrl: 'http://localhost:8080',

    
    /* User */
    user:'/user',
    add:'/addUser',
    authority:'/verificaAuthority',
    login:'/login',
    getUserM:'/findUserM',
    ricarica:'/ricarica',
    preleva:'/preleva',


    /* Product */
    product:'/product',
    addP:'/addProduct',
    getAllP:'/getAllProduct',
    rimuoviProdotto:'/removeProduct',
    modifyQ:'/setQuantityProduct',
    modify:'/modify',
    getAllPP:'/productPage',



    /* Product in cart */
    cart:'/cart',
    addC:'/addProductToCart',
    getAllC:'/getAllCart',
    buy:'/buyProduct',
    removePC:'/removeProductToCart',
    buyAll:'/buyAllCart',
    modifyQPIC:'/modifyQP',
    getUserProduct:'/getUserProduct',
    caolco:'/calcolo'

    

    


}
export const APP_CONFIG={
}