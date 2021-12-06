import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { of } from 'rxjs';
import { ContentDetails, HighlightData, HtmlData } from './html-data';

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

  availableContents(): Observable<ContentDetails[]> {
    const result = [
      {
        id: 'content1',
        title: 'How to be politically correct',
        type: 'html',
        imgUrl: '/highlights/content1/politics.jpg',
        duration: '10m',
        highlights: 23,
      },
      {
        id: 'content2',
        title: 'How to play Soccer',
        type: 'pdf',
        imgUrl: '/highlights/content2/soccer.png',
        duration: '30m',
        highlights: 0,
      },
      {
        id: 'content3',
        title: 'Astronomy',
        type: 'video',
        imgUrl: '/highlights/assets/placeholder.jpg',
        duration: '1h',
        highlights: 4,
      },
    ];
    //return this.http.get<ContentDetails[]>(baseUrl + '/highlights/available-contents')
    return of(result);
  }

  getViewContent(): Observable<ContentDetails[]> {
    const result = [
      {
        id: 'content1',
        title: 'How to be politically correct',
        type: 'html',
        imgUrl: '/highlights/content1/politics.jpg',
        duration: '10m',
        highlights: 23,
      },
      {
        id: 'content2',
        title: 'How to play Soccer',
        type: 'pdf',
        imgUrl: '/highlights/content2/soccer.png',
        duration: '30m',
        highlights: 0,
      },
      {
        id: 'content3',
        title: 'Astronomy',
        type: 'video',
        imgUrl: '/highlights/assets/placeholder.jpg',
        duration: '1h',
        highlights: 4,
      },
    ];
    //return this.http.get<ContentDetails[]>(baseUrl + '/highlights/viewed-contents')
    return of(result);
  }

  saveHighLightedText(data: HighlightData): Observable<any> {
    return this.http.post<any>(baseUrl + '/text', data);
  }
}
