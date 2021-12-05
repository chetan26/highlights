import {
  Component,
  ElementRef,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppService } from '../app.service';

@Component({
  selector: 'app-html-renderer',
  templateUrl: './html-renderer.component.html',
  styleUrls: ['./html-renderer.component.scss'],
})
export class HtmlRendererComponent implements OnInit {
  // @ViewChild('content', { read: ElementRef }) contentElement: ElementRef;
  data = '';
  id: any;

  constructor(
    private _appService: AppService,
    private _activatedRoute: ActivatedRoute,
    renderer: Renderer2
  ) {
    this._activatedRoute.paramMap.subscribe((pathParams) => {
      this.id = pathParams.get('id');
    });
  }

  ngOnInit(): void {
    this._appService.getHtmlData().subscribe((response) => {
      this.data = response.data;
    });
    // this.contentElement
  }
}
