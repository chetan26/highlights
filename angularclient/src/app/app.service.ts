import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { HtmlData } from './html-data';

const baseUrl = 'http://localhost:8080';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  dataObservable: any;
  constructor(private http: HttpClient) {}

  authenticate(credentials: any, callback: any) {
    //of('asd')    this.http.post(baseUrl + '/login', credentials)
    this.http.post(baseUrl + '/login', credentials).subscribe((response) => {
      return callback && callback();
    });
  }

  getHtmlData(): Observable<HtmlData> {
    //return this.http.get<HtmlData>(baseUrl + '/html')
    return of({
      data: 'my first text to test the highlight',
      selectedText: ['text', 'test'],
    });
  }

  saveHighLightedText(text: string): Observable<any> {
    return this.http.post<any>(baseUrl + '/text', text);
  }
}
