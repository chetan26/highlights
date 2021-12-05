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
}
