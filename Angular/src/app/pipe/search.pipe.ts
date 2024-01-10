import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../dTypes';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(listaProdotti:Product[], searchName:String):Product[] {
    const prodotti: Product[] = [];

    return listaProdotti.filter(el=>el.nameProduct.toLowerCase().includes(searchName.toLowerCase()))
    
      /* for (const prodotto of listaProdotti) {
        if (prodotto.nameProduct.toLowerCase().includes(searchName.toLowerCase())) {
          prodotti.push(prodotto);
        }
      }
    return prodotti;*/
  }
 
}
