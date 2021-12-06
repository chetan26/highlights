import {
  Component,
  ElementRef,
  OnInit,
  Renderer2,
  ViewChild,
} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from '../app.service';
import { HighlightData } from '../html-data';

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
  selectedEndWordIndex: any;
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
    this._appService.getHtmlData().subscribe((response) => {
      this.data = response.data;
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
    if (
      this.selectedText ==
      this.data.substring(
        this.data.indexOf(this.selectedText),
        this.data.indexOf(this.selectedText) + this.selectedText.length
      )
    ) {
      this.selectedWordIndex = this.data.indexOf(this.selectedText);
      this.selectedEndWordIndex =
        this.data.indexOf(this.selectedText) + this.selectedText.length;
    } else {
      this.selectedWordIndex = this.selection.anchorOffset;
    }

    console.log(this.selection.anchorOffset);
    console.log(this.data.indexOf(this.selectedText));
  }

  mouseUp(): void {
    this.setSelected();
    if (this.selection) {
      const item = this.selection?.getRangeAt(0).cloneRange().getClientRects();

      this.selectedText = this.selection.toString();

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
  }

  saveHighlightText(): void {
    this.findTheIndexOfWord();
    const json: HighlightData = {
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

    this.highlightTheSelectedText();
    this.hideTheDiv();
    // this._appService.saveHighLightedText(json).subscribe((response) => {

    // });

    // this._router.navigate(['.'], {
    //   relativeTo: this._activatedRoute,
    // });
    this._appService.changeResponseData(this.data);
    //window.location.reload();
    let currentUrl = this._router.url;
    this._router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
      this._router.navigate([currentUrl]);
    });

    console.log('highlightTheText');
  }

  highlightTheSelectedText(): void {
    let finalString = '';
    const currentData = this.data;
    if (this.selectedWordIndex > 1) {
      finalString =
        currentData.substring(0, this.selectedWordIndex) +
        '<mark>' +
        currentData.substring(
          this.selectedWordIndex,
          this.selectedWordIndex + this.selectedText.length
        ) +
        '</mark>' +
        currentData.substring(
          this.selectedWordIndex + this.selectedText.length
        );
    } else {
      finalString =
        '<mark>' +
        currentData.substring(
          this.selectedWordIndex,
          this.selectedWordIndex + this.selectedText.length
        ) +
        '</mark>' +
        currentData.substring(
          this.selectedWordIndex + this.selectedText.length
        );
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
