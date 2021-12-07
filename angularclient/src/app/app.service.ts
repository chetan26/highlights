import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ContentDetails, HighlightData } from './html-data';

const baseUrl = 'http://localhost:8080';

@Injectable({
  providedIn: 'root',
})
export class AppService {
  dataObservable: any;

  constructor(private http: HttpClient) {}

  authenticate(credentials: any, callback: any) {
    this.http.post(baseUrl + '/login', credentials).subscribe((response) => {
      return callback && callback();
    });
  }

  getHtmlData(contentId: string): Observable<any> {
    return this.http.get(baseUrl + '/highlights/html/' + contentId, {
      responseType: 'text',
    });
  }

  getHighlightsData(contentId: string): Observable<HighlightData[]> {
    return this.http.get<HighlightData[]>(
      baseUrl + '/highlights/content/' + contentId + '/highlights'
    );
  }

  getNextHighlight(): Observable<HighlightData> {
    return this.http.get<HighlightData>(baseUrl + '/highlights/highlight');
  }

  availableContents(): Observable<ContentDetails[]> {
    return this.http.get<ContentDetails[]>(
      baseUrl + '/highlights/available-contents'
    );
  }

  getViewContent(): Observable<ContentDetails[]> {
    return this.http.get<ContentDetails[]>(
      baseUrl + '/highlights/viewed-contents'
    );
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
