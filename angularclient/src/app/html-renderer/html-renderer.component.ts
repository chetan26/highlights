import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-html-renderer',
  templateUrl: './html-renderer.component.html',
  styleUrls: ['./html-renderer.component.scss'],
})
export class HtmlRendererComponent implements OnInit {
  data = '';

  constructor(private _appService: AppService) {}

  ngOnInit(): void {
    this._appService.getHtmlData().subscribe((response) => {
      this.data = response.data;
    });
  }
}
