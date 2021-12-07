import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { AppService } from '../app.service';
import { ContentDetails, HighlightData } from '../html-data';
import { takeUntil } from 'rxjs/operators';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  data: ContentDetails[] = [];
  viewContent: HighlightData[] = [];
  historyContent: HighlightData[] = [];

  componentDestroyed$: Subject<boolean> = new Subject();

  constructor(private _appService: AppService) {}

  ngOnInit(): void {
    this._appService
      .getNextHighlight()
      .pipe(takeUntil(this.componentDestroyed$))
      .subscribe((response) => {
        this.viewContent.push(response);
        console.log(response);
      });
    this._appService
      .availableContents()
      .pipe(takeUntil(this.componentDestroyed$))
      .subscribe((response) => {
        this.data = response;
      });

    this._appService
      .getUserHighlights()
      .pipe(takeUntil(this.componentDestroyed$))
      .subscribe((response) => {
        this.historyContent = response;
      });
  }

  ngOnDestroy() {
    this.componentDestroyed$.next(true);
    this.componentDestroyed$.complete();
  }

  getContentUrl(data: ContentDetails): string {
    return '/' + data.type + '/' + data.id;
  }

  getContentUrlC(data: HighlightData) {
    return '/' + data.type + '/' + data.contentId;
  }

  getContentThumbnail(data: ContentDetails): string {
    //return '/assets/contents/' + data.id + '/' + data.imgUrl;
    //TODO: Ask Pankaj for help changing this
    return 'http://localhost:8080/assets/' + data.id + '/' + data.imgUrl;
  }

  getHighlightImage(data: HighlightData): string {
    //return '/assets/contents/' + data.id + '/' + data.imgUrl;
    //TODO: Ask Pankaj for help changing this
    return 'http://localhost:8080/assets/' + data.contentImgUrl;
  }

  getVideo(data: HighlightData): string {
    //return '/assets/contents/' + data.id + '/' + data.imgUrl;
    //TODO: Ask Pankaj for help changing this
    return 'http://localhost:8080/assets/' + data.contentLaunchUrl;
  }
}
