import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { ContentDetails, HighlightData } from '../html-data';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  data: ContentDetails[] = [];
  viewContent!: HighlightData;
  historyContent: HighlightData[] = [];

  constructor(private _appService: AppService) {
    this._appService.getNextHighlight().subscribe((response) => {
      this.viewContent = response;
      console.log(response);
    });
  }

  ngOnInit(): void {
    this._appService.availableContents().subscribe((response) => {
      this.data = response;
    });

    this._appService.getUserHighlights().subscribe((response) => {
      this.historyContent = response;
    });
  }

  getContentUrl(data: ContentDetails): string {
    return '/' + data.type + '/' + data.id;
  }

  getContentThumbnail(data: ContentDetails): string {
    //return '/assets/contents/' + data.id + '/' + data.imgUrl;
    //TODO: Ask Pankaj for help changing this
    return 'http://localhost:8080/assets/' + data.id + '/' + data.imgUrl;
  }
}

