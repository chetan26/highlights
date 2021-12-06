import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { ContentDetails } from '../html-data';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  data: ContentDetails[] = [];
  viewContent: ContentDetails[] = [];
  constructor(private _appService: AppService) {}

  ngOnInit(): void {
    this._appService.availableContents().subscribe((response) => {
      this.data = response;
    });
    this._appService.getViewContent().subscribe((response) => {
      this.viewContent = response;
    });
  }

  getContentUrl(data: ContentDetails): string {
    return '/' + data.type + '/' + data.id;
  }

  getContentThumbnail(data: ContentDetails): string {
    //return '/assets/contents/' + data.id + '/' + data.imgUrl;

    //TODO: Ask Pankaj for help changing this
    return 'http://localhost:8080/assets/contents/' + data.id + '/' + data.imgUrl;
  }

  /*getContentFiletype(data: ContentDetails): string {

    //TODO: Ask Pankaj for help changing this localhost hard-coding
    if (data.type === 'video') return '/assets/video.png';
    if (data.type === 'pdf') return '/assets/pdf.png';
    else return '/assets/html.png';
  }
  */
}
