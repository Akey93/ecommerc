import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API } from '../constants';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }
  makeRequest(type: string, url: string, body?: any, params?: any): Observable<any> {
    url = API.baseUrl + url;

    let options: any = {};

    if (params) {
      let httpParams = new HttpParams();

      Object.keys(params).forEach((key) => {
        httpParams = httpParams.append(key, params[key])
        console.log(httpParams);
      });

      options = { params: httpParams }
    }
    let token = localStorage.getItem('userToken')


    if (token) {

      let myHeaders = new HttpHeaders();

      if (options.headers) {

        myHeaders = myHeaders.append('Authorization', "Bearer " + token);
        options.headers = options.headers.append('Authorization', "Bearer " + token);
      } else {

        myHeaders = myHeaders.set('Authorization', "Bearer " + token);
        options.headers = myHeaders;
      }
    }

    if (body) {
      options = { ...options };

      if (type.toLocaleLowerCase() == "post") {
        return this.http.post(url, body, options);
      }
      else if (type.toLocaleLowerCase() == "put") {
        return this.http.put(url, body, options);
      }
    }

    if (type.toLocaleLowerCase() == "delete") {
      return this.http.delete(url, options);
    }
    else if (type.toLocaleLowerCase() == "post") {
      return this.http.post(url, body, options)
    }
    return this.http.get(url ,options);


  }
}

