import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../dTypes';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(value:Product[], searchName:String):Product[] {
    const prodotti: Product[] = [];
      for (const prodotto of value) {
        if (prodotto.nameProduct.toLowerCase().includes(searchName.toLowerCase())) {
          prodotti.push(prodotto);
        }
      }
    
    return prodotti;
  }

}
