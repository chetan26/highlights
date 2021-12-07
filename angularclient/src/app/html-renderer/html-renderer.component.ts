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

  @ViewChild('noteDiv', { static: false }) noteElement: ElementRef | undefined;

  data = '';
  note = '';
  contentId: any;
  selectedText: any;
  selectedTextWithHtml: any;
  previousOffSet: any;
  selectedWordIndex: any;
  selectedHighlight: any;
  selection: any;
  currentHighlightsData: HighlightData[] = [];

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

      this._appService
        .getHighlightsData(this.contentId)
        .subscribe((response1) => {
          this.currentHighlightsData = response1;
          this.currentHighlightsData.forEach((item) => {
            this.highlightTheSelectedText(
              item.trim.from,
              item.text,
              item.context.note
            );
          });
        });
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
    const textWithOutSpace = this.data.replace(/(\r)/gm, '');

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
    } else if (this.data.indexOf(this.selectedTextWithHtml) != -1) {
      this.selectedWordIndex = this.data.indexOf(this.selectedTextWithHtml);
    } else if (textWithOutSpace.indexOf(this.selectedTextWithHtml) != -1) {
      this.selectedWordIndex = textWithOutSpace.indexOf(
        this.selectedTextWithHtml
      );
    } else {
      this.selectedWordIndex = null;
      this.hideTheDiv();
    }
  }

  mouseUp(): void {
    this.setSelected();

    if (this.selection) {
      const sel = window.getSelection();
      if (sel?.rangeCount) {
        var container = document.createElement('div');
        for (var i = 0, len = sel.rangeCount; i < len; ++i) {
          container.appendChild(sel.getRangeAt(i).cloneContents());
        }
        this.selectedTextWithHtml = container.innerHTML?.toString();
      }

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
        this.noteElement?.nativeElement.focus();
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
        text: this.selectedTextWithHtml,
        trim: {
          from: this.selectedWordIndex,
          to: this.selectedWordIndex + this.selectedTextWithHtml.length + '',
        },
        type: 'html',
      };
      this._appService.saveHighLightedText(json).subscribe((response) => {
        this.highlightTheSelectedText(
          this.selectedWordIndex,
          this.selectedTextWithHtml,
          this.note
        );
        this.hideTheDiv();
      });
    }
  }

  highlightTheSelectedText(startIndex?: any, word?: any, comment?: any): void {
    let finalString = '';
    const currentData = this.data;
    if (parseInt(startIndex) > 1) {
      finalString =
        currentData.substring(0, parseInt(startIndex)) +
        '<mark title=" ' +
        comment +
        '" >' +
        currentData.substring(
          parseInt(startIndex),
          parseInt(startIndex) + word.length
        ) +
        '</mark>' +
        currentData.substring(parseInt(startIndex) + word.length);
    } else {
      finalString =
        '<mark title=' +
        comment +
        '>' +
        currentData.substring(parseInt(startIndex), startIndex + word.length) +
        '</mark>' +
        currentData.substring(parseInt(startIndex) + word.length);
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
