import {
  Component,
  ElementRef,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from '../app.service';

@Component({
  selector: 'app-html-renderer',
  templateUrl: './html-renderer.component.html',
  styleUrls: ['./html-renderer.component.scss'],
})
export class HtmlRendererComponent implements OnInit {
  @ViewChild('content', { static: false }) contentElement:
    | ElementRef
    | undefined;

  @ViewChild('highlightContent', { static: false }) highlightContentElement:
    | ElementRef
    | undefined;

  data = '';
  note = '';
  contentId: any;
  selectedText: any;
  previousOffSet: any;
  selectedWordIndex: any;
  selectedHighlight: any;
  selection: any;

  constructor(
    private _appService: AppService,
    private _router: Router,
    private _activatedRoute: ActivatedRoute,
    private _renderer: Renderer2
  ) {
    this._activatedRoute.paramMap.subscribe((pathParams) => {
      this.contentId = pathParams.get('id');
    });
  }

  ngOnInit(): void {
    this._appService.getHtmlData(this.contentId).subscribe((response) => {
      this.data = response;
    });
  }

  mouseDown(): void {
    this.hideTheDiv();
    this.selectedText = '';
    this.note = '';
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

  findTheIndexOfWord(): void {
    const removeAllThings = this.data
      .replace(/(\n|\r)/gm, '..')
      .replace(/ +(?= )/g, '');

    if (
      this.selectedText ==
      this.data.substring(
        this.data.indexOf(this.selectedText),
        this.data.indexOf(this.selectedText) + this.selectedText.length
      )
    ) {
      this.selectedWordIndex = this.data.indexOf(this.selectedText);
    } else if (this.data.indexOf(this.selectedText) != -1) {
      this.selectedWordIndex = this.data.indexOf(this.selectedText);
    } else if (removeAllThings.indexOf(this.selectedText) != -1) {
      this.selectedWordIndex = removeAllThings.indexOf(this.selectedText);
    } else {
      this.selectedWordIndex = null;
    }
  }

  mouseUp(): void {
    this.setSelected();
    if (this.selection) {
      const item = this.selection?.getRangeAt(0).cloneRange().getClientRects();

      this.selectedText = this.selection.toString();

      this._renderer.setStyle(
        this.highlightContentElement?.nativeElement,
        'position',
        'fixed'
      );
      this._renderer.setStyle(
        this.highlightContentElement?.nativeElement,
        'top',
        '50%'
      );
      this._renderer.setStyle(
        this.highlightContentElement?.nativeElement,
        'left',
        '50%'
      );

      if (this.selectedText) {
        this._renderer.setProperty(
          this.highlightContentElement?.nativeElement,
          'hidden',
          false
        );
      }
    }
  }

  saveHighlightText(): void {
    this.findTheIndexOfWord();
    if (this.selectedWordIndex) {
      const json = {
        contentId: this.contentId,
        context: {
          note: this.note,
        },
        location: {
          ancestorId: 'TODO',
          offset: 0,
        },
        source: 'TODO',
        text: this.selectedText,
        trim: {
          from: this.selectedWordIndex,
          to: this.selectedWordIndex + this.selectedText.length - 1 + '',
        },
        type: 'html',
      };
      this._appService.saveHighLightedText(json).subscribe((response) => {
        this.highlightTheSelectedText(
          this.selectedWordIndex,
          this.selectedText
        );
        this.hideTheDiv();
      });
    }

    // });

    // this._router.navigate(['.'], {
    //   relativeTo: this._activatedRoute,
    // });
    //this._appService.changeResponseData(this.data);
    //window.location.reload();
    // let currentUrl = this._router.url;
    // this._router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
    //   this._router.navigate([currentUrl]);
    // });

    console.log('highlightTheText');
  }

  highlightTheSelectedText(startIndex?: any, word?: any): void {
    let finalString = '';
    const currentData = this.data;
    if (startIndex > 1) {
      finalString =
        currentData.substring(0, startIndex) +
        '<mark>' +
        currentData.substring(startIndex, startIndex + word.length) +
        '</mark>' +
        currentData.substring(startIndex + word.length);
    } else {
      finalString =
        '<mark>' +
        currentData.substring(startIndex, startIndex + word.length) +
        '</mark>' +
        currentData.substring(startIndex + word.length);
    }
    this.data = finalString;
  }

  setSelected(): any {
    if (window.getSelection) {
      this.selection = window.getSelection();
    } else if (document.getSelection) {
      this.selection = document.getSelection();
    }
    if (!this.selection.toString()) {
      this.selection = null;
    }
  }
}
