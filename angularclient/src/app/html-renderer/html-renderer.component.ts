import {
  Component,
  ElementRef,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-html-renderer',
  templateUrl: './html-renderer.component.html',
  styleUrls: ['./html-renderer.component.scss'],
})
export class HtmlRendererComponent implements OnInit {
  // @ViewChild('content', { read: ElementRef }) contentElement: ElementRef;
  data = '';

  constructor(private _appService: AppService, renderer: Renderer2) {}

  ngOnInit(): void {
    this._appService.getHtmlData().subscribe((response) => {
      this.data = response.data;
    });
    // this.contentElement
  }
}
