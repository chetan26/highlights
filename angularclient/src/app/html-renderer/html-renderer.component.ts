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
  @ViewChild('highlightContent', { static: false }) highlightContentElement:
    | ElementRef
    | undefined;

  @ViewChild('buttonToCopy', { static: false }) buttonToCopyElement:
    | ElementRef
    | undefined;
  @ViewChild('content', { static: false }) contentElement:
    | ElementRef
    | undefined;

  data = '';
  myTextarea = '';
  id: any;
  selectedText: any;
  selectedHighlight: any;
  selection: any;

  constructor(
    private _appService: AppService,
    private _activatedRoute: ActivatedRoute,
    private _renderer: Renderer2
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

  mouseDown(): void {
    this.hideTheDiv();
    this.selectedText = '';
    this.myTextarea = '';
  }

  hideTheDiv(): void {
    this._renderer.setProperty(
      this.highlightContentElement?.nativeElement,
      'hidden',
      true
    );
  }

  editHighlight(selectedHighlight: any): void {
    this.selectedHighlight = selectedHighlight;
    this._renderer.setStyle(
      this.highlightContentElement?.nativeElement,
      'position',
      'fixed'
    );
    this._renderer.setStyle(
      this.highlightContentElement?.nativeElement,
      'left',
      '50%'
    );
    this._renderer.setStyle(
      this.highlightContentElement?.nativeElement,
      'top',
      '50%'
    );
    this._renderer.setProperty(
      this.highlightContentElement?.nativeElement,
      'hidden',
      false
    );
  }

  mouseUp(): void {
    this.setSelected();
    const item = this.selection?.getRangeAt(0).getClientRects();

    this.selectedText = this.selection.toString();

    const element = this.contentElement?.nativeElement;

    this._renderer.setStyle(
      this.highlightContentElement?.nativeElement,
      'position',
      'absolute'
    );
    this._renderer.setStyle(
      this.highlightContentElement?.nativeElement,
      'left',
      item[0].left + 'px'
    );
    this._renderer.setStyle(
      this.highlightContentElement?.nativeElement,
      'top',
      item[0].top + 35 + 'px'
    );

    if (this.selectedText) {
      this._renderer.setProperty(
        this.highlightContentElement?.nativeElement,
        'hidden',
        false
      );
    }
  }

  highlightTheText(): void {
    console.log('highlightTheText');
  }

  setSelected(): any {
    if (window.getSelection) {
      this.selection = window.getSelection();
    } else if (document.getSelection) {
      this.selection = document.getSelection();
    }
  }
}
