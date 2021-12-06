import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  responseData = `Awesome" began as a cabaret act thrown together by seven experienced fringe
  theater actors. Although they continued to perform in theatrical venues, their
  identity as a band and cabaret act has eclipsed their status as actors.[3]
  Around October 2003, several future members of "Awesome" played together in a
  They Might Be Giants tribute to raise money for Seattle's Open Circle
  Theater.[4] Their very first performance under the name "Awesome" (with just
  Ackermann, Mosher, Nixon, and Osebold) was in Seattle at Annex Theater's
  monthly cabaret "Spin the Bottle" On February 6, 2004,[2] and their first
  full-septet performance as "Awesome" was in the Jewelbox theater at Belltown bar the Rendezvous on
  June 30 2004.[4] Their first major production was Delaware (first a multi-media
  stage production and later an album).[3] Gigs as a band have included
  performing on bills with Harvey Danger,[4][5] A. C. Newman,[4] U.S.E.,[4] The
  Presidents of the United States of America,[5] and The Long Winters.[5]`;

  constructor(private http: HttpClient) {}

  authenticate(credentials: any, callback: any) {
    //of('asd')    this.http.post(baseUrl + '/login', credentials)
    this.http.post(baseUrl + '/login', credentials).subscribe((response) => {
      return callback && callback();
    });
  }

  getHtmlData(): Observable<any> {
    //return this.http.get<HtmlData>(baseUrl + '/html')
    return of({
      data: this.responseData,
    });
  }

  changeResponseData(data: any) {
    this.responseData = data;
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
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<any>(
      baseUrl + '/highlights/content/highlight',
      data,
      { headers: headers }
    );
  }
}
